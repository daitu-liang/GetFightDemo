package com.daitu_liang.study.mytest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.squareup.leakcanary.RefWatcher;

public class FloatTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FloatActivity.getIntent(FloatTestActivity.this, 1));
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FloatActivity.getIntent(FloatTestActivity.this, 2));
            }
        });
        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FloatActivity.getIntent(FloatTestActivity.this, 3));
            }
        });
        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FloatActivity.getIntent(FloatTestActivity.this, 4));
            }
        });
        Button btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FloatActivity.getIntent(FloatTestActivity.this, 5));
            }
        });
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FloatActivity.getIntent(FloatTestActivity.this, 6));
            }
        });
        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(IndraftActivity.getIntent(FloatTestActivity.this));
            }
        });

    }
    @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = GetFightApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
