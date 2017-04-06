package com.daitu_liang.study.mytest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.paint.Bezier3;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BezierActivity extends AppCompatActivity {


    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.bezier)
    Bezier3 mBezier;

    boolean mode=false;
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, BezierActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bezier bezier=new Bezier(this);
//        setContentView(bezier);

        setContentView(R.layout.activity_bezier);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.btn)
    public void onClick() {
        if (mode) {
            mBezier.setMode(true);
            mode=false;
        } else {
            mBezier.setMode(false);
            mode=true;
        }
    }
}
