package com.daitu_liang.study.mytest.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.daitu_liang.study.mytest.PullDownActivity;
import com.daitu_liang.study.mytest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FunctionFragment extends Fragment {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.a)
    Button a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apple, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn)
    public void onClick() {
        getActivity().startActivity(new Intent(getActivity(), PullDownActivity.class));
    }
}
