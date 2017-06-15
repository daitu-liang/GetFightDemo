package com.daitu_liang.study.mytest.ui.fragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.ARAdapter;
import com.daitu_liang.study.mytest.baidu.ocr.BaiduMainActivity;
import com.daitu_liang.study.mytest.baidu.voice.BaiduVoiceActivity;
import com.daitu_liang.study.mytest.tinker.BaseBuildInfo;
import com.daitu_liang.study.mytest.tinker.BuildInfo;
import com.daitu_liang.study.mytest.ui.activity.MainHomeActivity;
import com.daitu_liang.study.mytest.util.Logger;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ARFragment extends Fragment {

    private Logger log = Logger.getLogger("ARFragment");
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.m_title)
    TextView mTitle;
    @BindView(R.id.recyclerview)
    XRecyclerView mRecyclerView;
    private ArrayList<String> listData;
    private ARAdapter mAdapter;
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
    mTitle.setText("ARFragment-我终于热修复成功了");
    toolbar.setTitle("");
    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(layoutManager);
    mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
    mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
    mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            refreshTime ++;
            times = 0;
            new Handler().postDelayed(new Runnable(){
                public void run() {
                    listData.clear();
                    listData.add("人工智能-文字识别");
                    listData.add("人工智能-语音合成识别");
                    listData.add("热修复-tinkerload");
                    listData.add("热修复-showInfo");
                    for(int i = 0; i < 15 ;i++){
                        listData.add("num" + i + "after " + refreshTime + " times of refresh");
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
                            listData.add("num" + (1 + listData.size() ) );
                        }
                        mRecyclerView.loadMoreComplete();
                        mAdapter.notifyDataSetChanged();
                    }
                }, 1000);
            } else {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        for(int i = 0; i < 9 ;i++){
                            listData.add("num" + (1 + listData.size() ) );
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
    mAdapter =new ARAdapter(getActivity(), R.layout.item_ar, listData);
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

                if (position == 1) {
                    startActivity(BaiduMainActivity.getIntent(getActivity()));
                } else if (position == 2) {
                    startActivity(BaiduVoiceActivity.getIntent(getActivity()));
                } else if(position == 3){

                    Log.e("tinker","点击LOAD PATCH");
                    TinkerInstaller.onReceiveUpgradePatch(getActivity().getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/abc.apk");
                }else if(position == 4){
                    showInfo(getActivity());
                }else {

                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
}

    public boolean showInfo(Context context) {
        // add more Build Info
        final StringBuilder sb = new StringBuilder();
        Tinker tinker = Tinker.with(getActivity().getApplicationContext());
        if (tinker.isTinkerLoaded()) {
            sb.append(String.format("[patch is loaded] \n"));
            sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
            sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));

            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName(ShareConstants.TINKER_ID)));
            sb.append(String.format("[packageConfig patchMessage] %s \n", tinker.getTinkerLoadResultIfPresent().getPackageConfigByName("patchMessage")));
            sb.append(String.format("[TINKER_ID Rom Space] %d k \n", tinker.getTinkerRomSpace()));

        } else {
            sb.append(String.format("[patch is not loaded] \n"));
            sb.append(String.format("[buildConfig TINKER_ID] %s \n", BuildInfo.TINKER_ID));
            sb.append(String.format("[buildConfig BASE_TINKER_ID] %s \n", BaseBuildInfo.BASE_TINKER_ID));

            sb.append(String.format("[buildConfig MESSSAGE] %s \n", BuildInfo.MESSAGE));
            sb.append(String.format("[TINKER_ID] %s \n", ShareTinkerInternals.getManifestTinkerID(getActivity().getApplicationContext())));
        }
        sb.append(String.format("[BaseBuildInfo Message] %s \n", BaseBuildInfo.TEST_MESSAGE));

        final TextView v = new TextView(context);
        v.setText(sb);
        v.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        v.setTextColor(0xFF000000);
        v.setTypeface(Typeface.MONOSPACE);
        final int padding = 16;
        v.setPadding(padding, padding, padding, padding);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setView(v);
        final AlertDialog alert = builder.create();
        alert.show();
        return true;
    }
}
