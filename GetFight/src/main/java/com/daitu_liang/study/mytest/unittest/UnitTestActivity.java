package com.daitu_liang.study.mytest.unittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;

public class UnitTestActivity extends AppCompatActivity {

    private EditText et;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_test);
         et =(EditText) findViewById(R.id.test_et);
         tv =(TextView) findViewById(R.id.test_tv);
        addTest();
    }

    private void addTest() {
        String etValue = et.getText().toString().trim();
        tv.setText(etValue+"apple");

    }

}
