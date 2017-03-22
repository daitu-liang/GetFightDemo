package com.daitu_liang.study.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daitu_liang.study.mytest.adapter.MainFragmentPagerAdapter;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.http.netapi.HttpMethods;
import com.daitu_liang.study.mytest.http.netapi.ProgressSubscriber;
import com.daitu_liang.study.mytest.http.netapi.SubscriberOnNextListener;
import com.daitu_liang.study.mytest.modle.NiuxInfo;
import com.daitu_liang.study.mytest.svg.MainActivity;
import com.daitu_liang.study.mytest.util.Logger;
import com.daitu_liang.study.mytest.util.PreferencesManager;
import com.daitu_liang.study.mytest.util.otto.BusProvider;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainHomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainHomeActivity";

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MainFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_main);
        ButterKnife.bind(this);
        Log.i("MainHomeActivity","onCreate="+savedInstanceState);

        getData();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("测试demo");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainHomeActivity.this, LoginActivity.class));
            }
        });
        initTabLayout();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //让左侧菜单显示原始设置的颜色
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initTabLayout() {
        mViewPager=(ViewPager)findViewById(R.id.viewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        myFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
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
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
        RefWatcher refWatcher = GetFightApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key","apple");
        Log.i("MainHomeActivity","onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("MainHomeActivity","onRestoreInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainHomeActivity","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainHomeActivity","onStop");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("MainHomeActivity","onNewIntent");
    }

}
