package com.daitu_liang.study.mytest.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.JokeTwoAdapter;
import com.daitu_liang.study.mytest.entity.TypeListEntity;
import com.daitu_liang.study.mytest.http.Jokenet.HttpJokeMethods;
import com.daitu_liang.study.mytest.http.netapi.NetWorkApi;
import com.daitu_liang.study.mytest.http.netapi.ProgressSubscriber;
import com.daitu_liang.study.mytest.http.netapi.SubscriberOnNextListener;
import com.daitu_liang.study.mytest.ui.activity.MainHomeActivity;
import com.daitu_liang.study.mytest.util.Logger;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokeTwoFragment extends Fragment {

    private Logger log = Logger.getLogger("JokeTwoFragment");
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private List<TypeListEntity.DataBean> listData;
    private JokeTwoAdapter mAdapter;
    private String typeKy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_one, null);
        ButterKnife.bind(this, view);
        initData(view);
        return view;
    }

    private void initData(View view) {
        typeKy=(String)getArguments().getSerializable("typeInfo_key");
        log.i("","type-图片-key="+typeKy);
//        typeKy="-103";
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
                  //refresh data here
                getContetList();
            }

            @Override
            public void onLoadMore() {

            }
        });
       getContetList();
        listData = new ArrayList<TypeListEntity.DataBean>();
        mAdapter =new JokeTwoAdapter(getActivity(), R.layout.item_joke_two, listData);
        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.refresh();
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
    }

    private void getContetList() {
        SubscriberOnNextListener<TypeListEntity> getSubscriber = new SubscriberOnNextListener<TypeListEntity>() {
            @Override
            public void onNext(TypeListEntity listInfo) {
                log.i("","listInfo="+listInfo.getTip());
                listData.clear();
                List<TypeListEntity.DataBean> dataGroup = listInfo.getData();
                listData.addAll(dataGroup);
                mAdapter.notifyDataSetChanged();
                log.i("","dataGroup="+dataGroup.size());
                mRecyclerView.refreshComplete();
            }

            @Override
            public void onError(Throwable e) {
                log.i("","异常="+e.getMessage());
            }

            @Override
            public void onCompleted() {
            }
        };
        HttpJokeMethods.getInstance().getJokeTypeListEntity(new ProgressSubscriber<TypeListEntity>(getSubscriber, getActivity()), NetWorkApi.getJokeRcommendUrl+typeKy);
    }
}
