package com.daitu_liang.study.mytest.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.MainFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {


    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.home_viewPager)
    ViewPager mViewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MainFragmentPagerAdapter myFragmentPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        initTabLayout(view);
        return view;
    }

    private void initTabLayout(View view) {
        toolbar.setTitle("HomeFragment");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_location_on_white_24dp);
        myFragmentPagerAdapter = new MainFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);
    }
    @Override
    public void onResume() {
        super.onResume();
    }
}
