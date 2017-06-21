package com.daitu_liang.study.mytest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.ARAdpater;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MultiLayoutActivity extends AppCompatActivity {
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, MultiLayoutActivity.class);
        return intent;
    }
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private int refreshTime;
    private int times;
    private ArrayList<String> listData;
    private ARAdpater mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        final GridLayoutManager layoutManager = new GridLayoutManager(this,6);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime++;

                times = 0;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        listData.clear();
                        listData.add("人工智能-文字识别");
                        listData.add("人工智能-语音合成识别");
                        listData.add("热修复-tinkerload");
                        listData.add("热修复-showInfo");
                        for (int i = 0; i < 30; i++) {
                            listData.add("num" + i + "after " + refreshTime + " times of refresh");
                        }
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if (times < 2) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i <30; i++) {
                                listData.add("num" + (1 + listData.size()));
                            }
                            mRecyclerView.loadMoreComplete();
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            for (int i = 0; i < 9; i++) {
                                listData.add("num" + (1 + listData.size()));
                            }
                            mRecyclerView.setNoMore(true);
                            mAdapter.notifyDataSetChanged();
                        }
                    }, 1000);
                }
                times++;
            }
        });


        listData = new ArrayList<String>();
        mAdapter = new ARAdpater(this,listData);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();

    }
}
