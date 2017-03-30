package com.daitu_liang.study.mytest.ui;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.squareup.leakcanary.RefWatcher;

public class AnimationActivity extends AppCompatActivity {

    private Button btn;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mIconLayoutParams;
    private Button btntop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        // 使用WindowManager添加window需要android.permission.SYSTEM_ALERT_WINDOW权限
        mWindowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);
        mIconLayoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
        mIconLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        btn=(Button)findViewById(R.id.btn);
//        test();
        test1();
        btntop=(Button)findViewById(R.id.btntop);
        btntop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimationActivity.this,ScrollToAndScrollByActivity.class));
            }
        });
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            btntop.setElevation(20);
        }
        TextView tv = (TextView) findViewById(R.id.tv);

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            tv.setElevation(10);
        }
    }


    private void test1() {
        AnimatorSet animatorSet=new AnimatorSet();
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(btn,"scaleX",1,0.2f);
        objectAnimator.setDuration(5000);
//        objectAnimator.setRepeatCount(Animation.INFINITE);
//        objectAnimator.setRepeatMode(Animation.REVERSE);
        ObjectAnimator objectAnimatorY=ObjectAnimator.ofFloat(btn,"scaleY",1,0);
        objectAnimatorY.setDuration(5000);

        ObjectAnimator objectAnimatora=ObjectAnimator.ofFloat(btn,"alpha",1,0.2f);
        objectAnimatora.setDuration(5000);
//        objectAnimatorY.setRepeatCount(Animation.INFINITE);
//        objectAnimatorY.setRepeatMode(Animation.REVERSE);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
//        animatorSet.playTogether(objectAnimator,objectAnimatorY);
        animatorSet.play(objectAnimatora).before(objectAnimator);
        animatorSet.start();

    }


    private void test() {
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,90);
        valueAnimator.setDuration(5000);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.setRepeatMode(Animation.REVERSE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animationValu = (float)animation.getAnimatedValue();
                Log.i("info","animationValu="+animationValu);
//                btn.setScaleX(1-animationValu);
//                btn.setScaleY(1+animationValu);
//                ViewHelper.setAlpha(btn,animationValu);
                ViewHelper.setRotationX(btn,animationValu);
                ViewHelper.setRotationY(btn,animationValu);
            }
        });
        valueAnimator.start();
    }
    @Override public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = GetFightApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
