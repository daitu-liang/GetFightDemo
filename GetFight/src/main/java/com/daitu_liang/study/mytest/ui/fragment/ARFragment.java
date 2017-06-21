package com.daitu_liang.study.mytest.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.ARAdpater;
import com.daitu_liang.study.mytest.baidu.ocr.BaiduMainActivity;
import com.daitu_liang.study.mytest.baidu.voice.BaiduVoiceActivity;
import com.daitu_liang.study.mytest.tinker.TinkerActivity;
import com.daitu_liang.study.mytest.ui.activity.MainHomeActivity;
import com.daitu_liang.study.mytest.util.Logger;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ARFragment extends Fragment implements ARAdpater.GridviewOnClickListener {

    private static final String TAG = "ARFragment";
    private Logger log = Logger.getLogger("ARFragment");
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private ArrayList<String> listData;
    private ARAdpater mAdapter;
    private int refreshTime, times;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moive, null);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTitle.setText("ARFragment");
//    toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 6);
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
                            for (int i = 0; i < 30; i++) {
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
//        mAdapter = new ARAdapter(getActivity(), R.layout.item_ar, listData);
        mAdapter = new ARAdpater(getContext(), listData);
        mAdapter.setGridviewOnClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.refresh();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {//静止,没有滚动
                    ((MainHomeActivity) getActivity()).showBottomNavigationBar();
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {//正在被外部拖拽,一般为用户正在用手指滚动
//                    ((MainHomeActivity) getActivity()).hideBottomNavigationBar();
                } else if (newState == RecyclerView.SCROLL_STATE_SETTLING) {//自动滚动开始
                    ((MainHomeActivity) getActivity()).hideBottomNavigationBar();
                }
            }
        });

    }


    @Override
    public void OnItemListClickListener(int position) {
        if (position == 0) {
            startActivity(BaiduMainActivity.getIntent(getActivity()));
        } else if (position == 1) {
            startActivity(BaiduVoiceActivity.getIntent(getActivity()));
        } else if (position == 2) {
            startActivity(TinkerActivity.getIntent(getActivity()));
//                    Log.e("tinker","点击LOAD PATCH");
//                    TinkerInstaller.onReceiveUpgradePatch(getActivity().getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/tinker.zip");
        } else if (position == 3) {

        } else {

        }
    }
}
