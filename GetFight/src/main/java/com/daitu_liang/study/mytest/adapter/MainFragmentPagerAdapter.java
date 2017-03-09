package com.daitu_liang.study.mytest.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daitu_liang.study.mytest.fragment.FunctionFragment;
import com.daitu_liang.study.mytest.fragment.SnowFragment;


public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"apple", "booke", "foot","flash","ear","others"};
    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
       Fragment f1=null;
        Bundle bundle = new Bundle();
      /*  if(jeweleyInfo!=null){
            bundle.putSerializable("jeweleyInfo_key", jeweleyInfo);
        }

        bundle.putString("key", data.get(position).getGuid());
        f1.setArguments(bundle);*/


        if(position>2){
            f1=new SnowFragment();
        }else {
            f1=new FunctionFragment();
        }
            return f1;
    }

    @Override
    public int getCount() {
        return mTitles.length;

    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {

        return mTitles[position];
    }
}
