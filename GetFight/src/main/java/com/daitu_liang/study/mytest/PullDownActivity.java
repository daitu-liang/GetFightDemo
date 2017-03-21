package com.daitu_liang.study.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PullDownActivity extends AppCompatActivity {


    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.toolbar_pull)
    Toolbar toolbarPull;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.a)
    Button a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_pull_down);
        ButterKnife.bind(this);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("Design Library");
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
    }

    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refresh.setRefreshing(false);
            }
        }, 3000);



    }


    @OnClick({R.id.btn_back, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn:
                startActivity(new Intent(PullDownActivity.this, SanFunctionActivity.class));

                //       test();
                break;
        }
    }

    private void test() {
        //Arrays.asList没有add remove方法，所以报错UnsupportedOperationException
        String str="d,f,f,g,h,h,j,j";
        List<String> list = Arrays.asList(str.split(","));
//        list.remove("j");

        ArrayList<String> arrList = new ArrayList<>(list);
        arrList.remove("j");

    }
}
