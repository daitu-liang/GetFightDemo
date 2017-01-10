package com.daitu_liang.study.mytest.datepicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignDialogActivity extends Activity {
    private static final String TAG = "SignDialogActivity";

    private TextView tvCcontinueSign;
    private int continueDays;
    private ImageView signCloseBtn;
    private ArrayList<Integer> list = new ArrayList<Integer>();

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SignDialogActivity.class);

        return intent;
    }

    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_today;
    private MonthDateView monthDateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_dialog);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        list.clear();
        for (int i = 0; i < 5; i++) {
            list.add(i);
            list.add(14);
        }


        monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
        tv_date = (TextView) findViewById(R.id.date_text);
        tv_week = (TextView) findViewById(R.id.week_text);
        tv_today = (TextView) findViewById(R.id.tv_today);
        signCloseBtn = (ImageView) findViewById(R.id.sign_close_btn);
        tvCcontinueSign = (TextView) findViewById(R.id.tv_continue_sign);
        tvCcontinueSign.setText("你已经连续签到" + continueDays + "天");
        monthDateView.setTextView(tv_date, tv_week);
        monthDateView.setDaysHasThingList(list);
        monthDateView.setmDayColor(Color.parseColor("#F5F5F5"));
        monthDateView.setmSelectBGColor(Color.parseColor("#00000000"));
        monthDateView.setmDaySize(16);
//        monthDateView.setDateClick(new MonthDateView.DateClick() {
//
//            @Override
//            public void onClickOnDate() {
//                Toast.makeText(getApplication(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        setOnlistener();
//        signCloseBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    private void setOnlistener() {
        tv_today.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.setTodayToView();
            }
        });
    }


    @OnClick({R.id.sign_close_btn, R.id.sign_do_btn, R.id.ic_sign_activity_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_close_btn:
                finish();
                break;
            case R.id.sign_do_btn:
                break;
            case R.id.ic_sign_activity_btn:
                break;
        }
    }


}
