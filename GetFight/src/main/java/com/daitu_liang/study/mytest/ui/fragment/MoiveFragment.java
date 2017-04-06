package com.daitu_liang.study.mytest.ui.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.BooksAdapter;
import com.daitu_liang.study.mytest.baidu.BaiduMainActivity;
import com.daitu_liang.study.mytest.ui.activity.MainHomeActivity;
import com.daitu_liang.study.mytest.util.Logger;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoiveFragment extends Fragment {

    private Logger log = Logger.getLogger("MoiveFragment");
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private ArrayList<String> listData;
    private BooksAdapter mAdapter;
    private int refreshTime,times;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_moive, null);
        ButterKnife.bind(this,view);
        initView(view);
        return view;
    }
    private void initView(View view) {
    mTitle.setText("MoiveFragment");
    toolbar.setTitle("");
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
    mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
//        View header = LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview_header, (ViewGroup)view.findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header);


    mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            refreshTime ++;
            times = 0;
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    listData.clear();
                    for(int i = 0; i < 15 ;i++){
                        listData.add("item_book" + i + "after " + refreshTime + " times of refresh");
                    }
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.refreshComplete();
                }

            }, 1000);            //refresh data here
        }

        @Override
        public void onLoadMore() {
            if(times < 2){
                new Handler().postDelayed(new Runnable(){
                    public void run() {
                        for(int i = 0; i < 15 ;i++){
                            listData.add("item_book" + (1 + listData.size() ) );
                        }
                        mRecyclerView.loadMoreComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for(int i = 0; i < 9 ;i++){
                            listData.add("item_book" + (1 + listData.size() ) );
                        }
                        mRecyclerView.setNoMore(true);
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
            times ++;
        }
    });

    listData = new ArrayList<String>();
    mAdapter =new BooksAdapter(getActivity(), R.layout.item_book, listData);
    mRecyclerView.setAdapter(mAdapter);
    mRecyclerView.refresh();
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState==RecyclerView.SCROLL_STATE_IDLE ){//静止,没有滚动
                ((MainHomeActivity) getActivity()).showBottomNavigationBar();
            }else if(newState==RecyclerView.SCROLL_STATE_DRAGGING){//正在被外部拖拽,一般为用户正在用手指滚动
//                    ((MainHomeActivity) getActivity()).hideBottomNavigationBar();
            }else if(newState==RecyclerView.SCROLL_STATE_SETTLING ){//自动滚动开始
                ((MainHomeActivity) getActivity()).hideBottomNavigationBar();
            }
        }
    });
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                startActivity(BaiduMainActivity.getIntent(getActivity()));
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
}


}
