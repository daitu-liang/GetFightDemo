package com.daitu_liang.study.mytest.ui.fragment;


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
import com.daitu_liang.study.mytest.entity.ContentTypeEntity;
import com.daitu_liang.study.mytest.http.Jokenet.HttpJokeMethods;
import com.daitu_liang.study.mytest.http.netapi.NetWorkApi;
import com.daitu_liang.study.mytest.http.netapi.ProgressSubscriber;
import com.daitu_liang.study.mytest.http.netapi.SubscriberOnNextListener;
import com.daitu_liang.study.mytest.ui.activity.MainHomeActivity;

import java.util.ArrayList;
import java.util.List;

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
        ((MainHomeActivity) getActivity()).initDrawerLayout(toolbar);
        getContetType();
//        toolbar.setNavigationIcon(R.drawable.ic_location_on_white_24dp);
        myFragmentPagerAdapter = new MainFragmentPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        mTabLayout.setupWithViewPager(mViewPager);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                BusProvider.getInstance().post(new ShowEvent("s"));
//                ((MainHomeActivity)getActivity()).isShow();
//            }
//        });
    }

    private void getContetType() {
        SubscriberOnNextListener<List<ContentTypeEntity>> getSubscriber = new SubscriberOnNextListener<List<ContentTypeEntity>>() {
            @Override
            public void onNext(List<ContentTypeEntity> listType) {
                if(listType!=null){
                    List<ContentTypeEntity> newList=new ArrayList<>();


                    for (ContentTypeEntity contentTypeEntity :listType){
                        if("推荐".equals(contentTypeEntity.getName())||"推荐".equals(contentTypeEntity.getName())
                                ||"视频".equals(contentTypeEntity.getName())||"段友秀".equals(contentTypeEntity.getName())
                                ||"图片".equals(contentTypeEntity.getName())||"段子".equals(contentTypeEntity.getName())
                                ||"精华".equals(contentTypeEntity.getName())){
                            newList.add(contentTypeEntity);
                        }
                    }

                    myFragmentPagerAdapter.setListType(newList);
                    myFragmentPagerAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onCompleted() {
            }
        };
        HttpJokeMethods.getInstance().getJokeContentType(new ProgressSubscriber<List<ContentTypeEntity>>(getSubscriber, getActivity()), NetWorkApi.getJokeContentTypeUrl);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
