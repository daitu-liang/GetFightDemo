package com.daitu_liang.study.mytest.ui.fragment;


import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.JokeThreeAdapter;
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
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static android.content.Context.SENSOR_SERVICE;

public class JokeThreeFragment extends Fragment {

    private Logger log = Logger.getLogger("JokeThreeFragment");
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private List<TypeListEntity.DataBean> listData;
    private JokeThreeAdapter mAdapter;
    private String typeKy;
    private SensorManager sensorManager;
    JCVideoPlayer.JCAutoFullscreenListener sensorEventListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_joke_one, null);
        ButterKnife.bind(this, view);
        initData(view);
        return view;
    }

    private void initData(View view) {
        typeKy = (String) getArguments().getSerializable("typeInfo_key");
        String typeInfo_name = (String) getArguments().getSerializable("typeInfo_name");
        log.i("","type-视频-key="+typeKy+" ----3-----typeInfo_name="+typeInfo_name);
//        typeKy="-104";
        listData = new ArrayList<TypeListEntity.DataBean>();
        if("-301".equals(typeKy)){
            GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
            layoutManager.setOrientation(layoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new JokeThreeAdapter(getActivity(), R.layout.item_joke_three, listData,12);
        }else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new JokeThreeAdapter(getActivity(), R.layout.item_joke_three, listData,0);
        }

        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
//        View header = LayoutInflater.from(getActivity()).inflate(R.layout.recyclerview_header, (ViewGroup)view.findViewById(android.R.id.content),false);
//        mRecyclerView.addHeaderView(header);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //refresh data here
                if(listData!=null){
                    listData.clear();
                }
                getContetList();
            }

            @Override
            public void onLoadMore() {

            }
        });
        getContetList();

//        mRecyclerView.refresh();
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
        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JCVideoPlayer.JCAutoFullscreenListener();

    }

    private void getContetList() {
        SubscriberOnNextListener<TypeListEntity> getSubscriber = new SubscriberOnNextListener<TypeListEntity>() {
            @Override
            public void onNext(TypeListEntity listInfo) {
                initAdpaterView(listInfo);
            }
            @Override
            public void onError(Throwable e) {
                log.i("", "异常=" + e.getMessage());

            }

            @Override
            public void onCompleted() {
            }
        };
        HttpJokeMethods.getInstance().getJokeTypeListEntity(new ProgressSubscriber<TypeListEntity>(getSubscriber, getActivity()), NetWorkApi.getJokeRcommendUrl + typeKy);
    }
    private void initAdpaterView(TypeListEntity listInfo) {
        if(listInfo==null) return;
        List<TypeListEntity.DataBean> dataGroup = listInfo.getData();
        listData.addAll(dataGroup);
        log.i("", "视频dataGroup=" + dataGroup.size());
        mRecyclerView.refreshComplete();
        mAdapter.notifyDataSetChanged();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(listData!=null){
            listData.clear();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JCVideoPlayer.releaseAllVideos();
    }
}
