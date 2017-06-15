package com.daitu_liang.study.mytest.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.TabTopFragmentPagerAdapter;
import com.daitu_liang.study.mytest.app.GetFightApplicationTinker;
import com.daitu_liang.study.mytest.ui.fragment.LightFragment;
import com.daitu_liang.study.mytest.ui.fragment.MusicFragment;
import com.daitu_liang.study.mytest.ui.fragment.RainFragment;
import com.daitu_liang.study.mytest.ui.fragment.SnowFragment;
import com.daitu_liang.study.mytest.ui.fragment.StarFragment;
import com.daitu_liang.study.mytest.widget.TabViewLayout;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;


public class AnimateEffectActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private TabViewLayout mTabLayout;
    private ParticlePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anint_effect);
        mViewPager = (ViewPager) findViewById(R.id.fragment_containers);
        mTabLayout = (TabViewLayout) findViewById(R.id.tab_layout);

        pagerAdapter = new ParticlePagerAdapter(getSupportFragmentManager(), getFragments());
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setViewPager(mViewPager);


    }


    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(new StarFragment());
        list.add(new MusicFragment());
        list.add(new RainFragment());
        list.add(new SnowFragment());
        list.add(new LightFragment());
        return list;
    }

    class ParticlePagerAdapter extends TabTopFragmentPagerAdapter {

        private ArrayList<Fragment> list;

        public ParticlePagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position).getClass().getSimpleName().replace("Fragment", "");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = GetFightApplicationTinker.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
