package com.daitu_liang.study.mytest.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daitu_liang.study.mytest.R;

import butterknife.ButterKnife;

public class MoiveFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moive, null);
        ButterKnife.bind(this,view);
        initTabLayout(view);
        return view;
    }

    private void initTabLayout(View view) {
      /*  myFragmentPagerAdapter = new MainFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);*/
    }
}
