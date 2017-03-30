package com.daitu_liang.study.mytest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollToAndScrollByActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    ImageView btn;
    @BindView(R.id.scrolltv)
    TextView scrolltv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn, R.id.scrolltv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                scrolltv.scrollTo(-100,-100);
                break;
            case R.id.scrolltv:
                btn.scrollBy(-50,-10);
                break;
        }
    }
}
