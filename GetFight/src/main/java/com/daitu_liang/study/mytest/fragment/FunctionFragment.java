package com.daitu_liang.study.mytest.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.FunAdapter;
import com.daitu_liang.study.mytest.datepicker.SignDialogActivity;
import com.daitu_liang.study.mytest.html.HtmlToActivity;
import com.daitu_liang.study.mytest.imageview.ImageViewActivity;
import com.daitu_liang.study.mytest.svg.MainActivity;
import com.daitu_liang.study.mytest.ui.AnimateEffectActivity;
import com.daitu_liang.study.mytest.ui.AnimationActivity;
import com.daitu_liang.study.mytest.ui.FloatTestActivity;
import com.daitu_liang.study.mytest.ui.HttpFunctionActivity;
import com.daitu_liang.study.mytest.ui.LoginActivity;
import com.daitu_liang.study.mytest.ui.MainHomeBottomActivity;
import com.daitu_liang.study.mytest.ui.PaintActivity;
import com.daitu_liang.study.mytest.ui.PieViewActivity;
import com.daitu_liang.study.mytest.ui.PorterDuffXfermodeActivity;
import com.daitu_liang.study.mytest.ui.PullDownActivity;
import com.daitu_liang.study.mytest.ui.WaveDemoActivity;
import com.daitu_liang.study.mytest.widget.DividerItemDecoration;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FunctionFragment extends Fragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<String> mDatas = new ArrayList<String>();
    private FunAdapter funAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apple, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        initListData();
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
      /*mAdapter=new CommonAdapter<String>(getActivity(),R.layout.item_main_one,mDatas) {
            @Override
            protected void convert(ViewHolder holder, String info, int position) {
                holder.setText(R.id.item_tv, mDatas.get(position)+"测试"+info);
            }
        };*/

        funAdapter = new FunAdapter(getActivity(), R.layout.item_main_one, mDatas);
        recyclerview.setAdapter(funAdapter);
    }

    private void initListData() {
        mDatas.add("-CollapsingToolbarLayout");
        mDatas.add("-Html5ToActivity交互");
        mDatas.add("-AnimatedSvgView");
        mDatas.add("-Shader渐变效果");
        mDatas.add("-PorterDuffXfermodeView波浪效果");
        mDatas.add("-WaveDemo波浪效果");

        mDatas.add("-Float效果");
        mDatas.add("-Elevation阴影+属性动画");

        mDatas.add("-网络Retroft-RxJava");
        mDatas.add("-下雨打雷闪电音乐符闪星效果");
        mDatas.add("- Glide");
        mDatas.add("- calendar");
        mDatas.add("-BottomNavigationBar");
        mDatas.add("- PieView");
        mDatas.add("- Md-LoginActivity");
        mDatas.add("- nothing");
        mDatas.add("- nothing");
        mDatas.add("- nothing");
        mDatas.add("- nothing");
        mDatas.add("- nothing");
        mDatas.add("- nothing");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        funAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                if (position == 0) {
                    getActivity().startActivity(new Intent(getActivity(), PullDownActivity.class));
                } else if (position == 1) {
                    getActivity().startActivity(new Intent(getActivity(), HtmlToActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(getActivity(), MainActivity.class));
                } else if (position == 3) {
                    startActivity(new Intent(getActivity(), PaintActivity.class));
                } else if (position == 4) {
                    startActivity(new Intent(getActivity(), PorterDuffXfermodeActivity.class));
                } else if (position == 5) {
                    startActivity(new Intent(getActivity(), WaveDemoActivity.class));
                } else if (position == 6) {
                    startActivity(new Intent(getActivity(), FloatTestActivity.class));
                } else if (position == 7) {
                    startActivity(new Intent(getActivity(), AnimationActivity.class));
                } else if (position == 8) {
                    startActivity(new Intent(getActivity(), HttpFunctionActivity.class));
                } else if (position == 9) {
                    startActivity(new Intent(getActivity(), AnimateEffectActivity.class));
                } else if (position == 10) {
                    startActivity(new Intent(getActivity(), ImageViewActivity.class));
                } else if (position == 11) {
                    startActivity(new Intent(getActivity(), SignDialogActivity.class));
                } else if (position == 12) {
                    startActivity(new Intent(getActivity(), MainHomeBottomActivity.class));
                } else if (position == 13) {
                    startActivity(new Intent(getActivity(), PieViewActivity.class));
                }else if (position == 14) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else {
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDatas != null) {
            mDatas.clear();
            mDatas = null;
        }
    }
}
