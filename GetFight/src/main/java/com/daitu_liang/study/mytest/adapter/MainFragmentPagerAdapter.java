package com.daitu_liang.study.mytest.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daitu_liang.study.mytest.entity.ContentTypeEntity;
import com.daitu_liang.study.mytest.ui.fragment.JokeOneFragment;

import java.util.List;


public class MainFragmentPagerAdapter extends FragmentPagerAdapter {


    private List<ContentTypeEntity> listType;

    public List<ContentTypeEntity> getListType() {
        return listType;
    }

    public void setListType(List<ContentTypeEntity> listType) {
        this.listType = listType;
    }

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        /*this.listType = listType;*/
    }

    @Override
    public Fragment getItem(int position) {
        if (listType != null && listType.size() > 0 && listType.get(position) != null) {
            ContentTypeEntity typeInfo = listType.get(position);
            Fragment f1 = null;
            if (position == 0) {
                f1 = new JokeOneFragment();
            } else if (position == 1) {
                f1 = new JokeOneFragment();
            } else {
                f1 = new JokeOneFragment();
            }
            Bundle bundle = new Bundle();
            bundle.putSerializable("typeInfo_key", typeInfo.getList_id());
            f1.setArguments(bundle);
            return f1;
        }
        return null;
    }

    @Override
    public int getCount() {
        if (listType != null) {
            return listType.size();
        } else {
            return 0;
        }
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        if (listType != null && listType.size() > 0) {
            return listType.get(position).getName();
        } else {
            return "";
        }
    }
}
