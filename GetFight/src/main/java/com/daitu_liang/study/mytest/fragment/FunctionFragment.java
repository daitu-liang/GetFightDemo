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

import com.daitu_liang.study.mytest.PullDownActivity;
import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.FunAdapter;
import com.daitu_liang.study.mytest.html.HtmlToActivity;
import com.daitu_liang.study.mytest.widget.DividerItemDecoration;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FunctionFragment extends Fragment {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<String> mDatas;
    private CommonAdapter mAdapter;
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
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        funAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                if (position == 1) {
                    getActivity().startActivity(new Intent(getActivity(), HtmlToActivity.class));
                } else {
                    getActivity().startActivity(new Intent(getActivity(), PullDownActivity.class));
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }
}
