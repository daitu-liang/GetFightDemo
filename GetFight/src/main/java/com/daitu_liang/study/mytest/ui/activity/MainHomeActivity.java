package com.daitu_liang.study.mytest.ui.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.entity.BaiduTokenEntity;
import com.daitu_liang.study.mytest.entity.NiuxInfo;
import com.daitu_liang.study.mytest.http.netapi.HttpMethods;
import com.daitu_liang.study.mytest.http.netapi.NetWorkApi;
import com.daitu_liang.study.mytest.http.netapi.ProgressSubscriber;
import com.daitu_liang.study.mytest.http.netapi.SubscriberOnNextListener;
import com.daitu_liang.study.mytest.svg.MainActivity;
import com.daitu_liang.study.mytest.ui.fragment.BookFragment;
import com.daitu_liang.study.mytest.ui.fragment.FunctionFragment;
import com.daitu_liang.study.mytest.ui.fragment.HomeFragment;
import com.daitu_liang.study.mytest.ui.fragment.MoiveFragment;
import com.daitu_liang.study.mytest.util.Logger;
import com.daitu_liang.study.mytest.util.PreferencesManager;
import com.daitu_liang.study.mytest.util.otto.BusProvider;
import com.daitu_liang.study.mytest.widget.bottomnavigation.BadgeItem;
import com.daitu_liang.study.mytest.widget.bottomnavigation.BottomNavigationBar;
import com.daitu_liang.study.mytest.widget.bottomnavigation.BottomNavigationItem;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationBar.OnTabSelectedListener {

    private static final String TAG = "MainHomeActivity";
    int lastSelectedPosition = 0;

    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    private BottomNavigationBar bottomNavigationBar;
    private HomeFragment mHomeFragment;
    private BadgeItem numberBadgeItem;
    private BookFragment mBookFragment;
    private MoiveFragment mMoiveFragment;
    private FunctionFragment mFunctionFragment;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);   //订阅事件
        Log.i("MainHomeActivity", "onCreate=" + savedInstanceState);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("测试demo");
//        setSupportActionBar(toolbar);
        getData();
        initBottomNavigationBar();
        initFragment(savedInstanceState);
        //为了生成，工具栏左上角的动态图标，要使用下面的方法
//        initDrawerLayout();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //让左侧菜单显示原始设置的颜色
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        getBaiduToken();
    }



    public  void initDrawerLayout(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }
    public  void showBottomNavigationBar() {
        bottomNavigationBar.show();
    }
    public  void hideBottomNavigationBar() {
        bottomNavigationBar.hide();
    }
    private void initBottomNavigationBar() {
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setAutoHideEnabled(true);
        bottomNavigationBar.setTabSelectedListener(this);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.blue)
                .setText("5")
                .setHideOnSelect(false);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Home").setActiveColorResource(R.color.orange).setBadgeItem(numberBadgeItem))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "Books").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "Movies & TV").setActiveColorResource(R.color.brown))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "function").setActiveColorResource(R.color.blue))
                .setFirstSelectedPosition(lastSelectedPosition)
                .initialise();
    }

    /**
     * 设置默认的
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        //判断activity是否重建，如果不是，则不需要重新建立fragment.
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (mHomeFragment == null) {
                mHomeFragment = new HomeFragment();
            }
            if (mBookFragment == null) {
                mBookFragment = new BookFragment();
            }
            if (mMoiveFragment == null) {
                mMoiveFragment = new MoiveFragment();
            }
            if (mFunctionFragment == null) {
                mFunctionFragment = new FunctionFragment();
            }
            currentFragment = mHomeFragment;

            if (currentFragment.isAdded()) {
                ft.show(currentFragment);
            } else {
                ft.replace(R.id.frame_layout, currentFragment);
            }
            ft.commit();

        }
       /* FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mHomeFragment = new HomeFragment();
        transaction.replace(R.id.frame_layout, mHomeFragment);
        transaction.commit();*/
    }



    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
//        FragmentManager fm = this.getSupportFragmentManager();
//        //开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
        Fragment switchTo = null;
        switch (position) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                }
                switchTo = mHomeFragment;
                break;
            case 1:
                if (mBookFragment == null) {
                    mBookFragment = new BookFragment();
                }
                switchTo = mBookFragment;
                break;
            case 2:
                if (mMoiveFragment == null) {
                    mMoiveFragment = new MoiveFragment();
                }
                switchTo = mMoiveFragment;
                break;
            case 3:
                if (mFunctionFragment == null) {
                    mFunctionFragment = new FunctionFragment();
                }
                switchTo = mFunctionFragment;
                break;
            default:
                break;
        }
        if (switchTo != null) {
            switchContent(currentFragment, switchTo);
        }
    }

    /**
     * 切换fragment
     *
     * @param from 当前显示的fragment
     * @param to   切换的目标fragment
     */
    public void switchContent(Fragment from, Fragment to) {
        if (from != to) {
            currentFragment = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) {
                transaction.hide(from).add(R.id.frame_layout, to).commit();
            } else {
                transaction.hide(from).show(to).commit();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        Log.d(TAG, "onTabUnselected() called with: " + "position = [" + position + "]");
    }

    @Override
    public void onTabReselected(int position) {

    }
    public void isShow() {
        Log.i("MainHomeActivity", "Subscribe-ShowEvent");
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_github:
                String url = "https://github.com/daitu-liang";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item_book clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(MainHomeActivity.this, MainActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(MainHomeActivity.this, PaintActivity.class));
        } else if (id == R.id.nav_slideshow) {
            startActivity(new Intent(MainHomeActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(MainHomeActivity.this, PorterDuffXfermodeActivity.class));
        } else if (id == R.id.nav_share) {
            startActivity(new Intent(MainHomeActivity.this, MatrixActivity.class));
        } else if (id == R.id.nav_send) {
            startActivity(new Intent(MainHomeActivity.this, WaveDemoActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getData() {
        SubscriberOnNextListener<NiuxInfo> getSubscriber = new SubscriberOnNextListener<NiuxInfo>() {
            @Override
            public void onNext(NiuxInfo s) {
                PreferencesManager pre = GetFightApplication.getPreferenceManager();
                pre.setSaveNunix(s.getNunix());
                Logger.getLogger("").i(TAG, "nunix--Times=" + s.getNunix());
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onCompleted() {
            }
        };
        HttpMethods.getInstance().getNunix(new ProgressSubscriber<NiuxInfo>(getSubscriber, this), "https://webapi.hsuperior.com/sys/getnunix");
    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainHomeActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainHomeActivity", "onStop");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("MainHomeActivity", "onNewIntent");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key", "apple");
        Log.i("MainHomeActivity", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("MainHomeActivity", "onRestoreInstanceState");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        RefWatcher refWatcher = GetFightApplication.getRefWatcher(this);
        refWatcher.watch(this);

    }
    private void getBaiduToken() {
        SubscriberOnNextListener<BaiduTokenEntity> getSubscriber = new SubscriberOnNextListener<BaiduTokenEntity>() {
            @Override
            public void onNext(BaiduTokenEntity s) {
                PreferencesManager pre = GetFightApplication.getPreferenceManager();
//                pre.setSaveBaiduToken(s.getNunix());

            }
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onCompleted() {
            }
        };
        HttpMethods.getInstance().getBaiduTokenEntity(new ProgressSubscriber<BaiduTokenEntity>(getSubscriber, this), NetWorkApi.getBaiDu);


    }
}
