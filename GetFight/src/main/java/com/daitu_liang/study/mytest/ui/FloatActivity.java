package com.daitu_liang.study.mytest.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.paint.FloatView;
import com.squareup.leakcanary.RefWatcher;

public class FloatActivity extends AppCompatActivity {



    public static Intent getIntent(Context context, int typeInfo) {
        Intent intent=new Intent(context, FloatActivity.class);
        intent.putExtra("type_key",typeInfo);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float);
        int typeKey = getIntent().getIntExtra("type_key", 0);
        FloatView startBtn = (FloatView) findViewById(R.id.float_btn);
        startBtn.setFloatType(FloatView.FREE_POINT);
        if(typeKey==1){
            startBtn.setFloatType(FloatView.DEFAULT_TYPE);
        }else if(typeKey==2){
            startBtn.setFloatType(FloatView.FREE_POINT);
        }else if(typeKey==3){
            startBtn.setFloatType(FloatView.TOP);
        }else if(typeKey==4){
            startBtn.setFloatType(FloatView.BOTTOM);
        }else if(typeKey==5){
            startBtn.setFloatType(FloatView.LEFT);
        }else if(typeKey==6){
            startBtn.setFloatType(FloatView.RIGHT);
        }
        startBtn.startAnimationFloat();
    }
    @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = GetFightApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
