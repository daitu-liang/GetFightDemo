package com.daitu_liang.study.mytest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.http.HttpTestActivity;
import com.daitu_liang.study.mytest.http.RetroftActivity;
import com.daitu_liang.study.mytest.imageview.ImageViewActivity;
import com.daitu_liang.study.mytest.entity.MessageEvent;
import com.daitu_liang.study.mytest.util.otto.BusProvider;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HttpFunctionActivity extends AppCompatActivity {
    @BindView(R.id.content_tv)
    TextView contentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_function);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        BusProvider.getInstance().register(this);////注册事件
    }

    @Subscribe
    public void dealEvent(MessageEvent event) {
        contentTv.setText(event.getMsg());
    }

    @OnClick({   R.id.btn7, R.id.btn8,
            R.id.btn9, R.id.btn10})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn7:
                startActivity(new Intent(HttpFunctionActivity.this, RetroftActivity.class));
                break;
            case R.id.btn8:
                startActivity(new Intent(HttpFunctionActivity.this, HttpTestActivity.class));
                break;
            case R.id.btn9:
                startActivity(new Intent(HttpFunctionActivity.this, ImageViewActivity.class));
                break;
            case R.id.btn10:
                startActivity(new Intent(HttpFunctionActivity.this, MatrixActivity.class));
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);

    }
}
