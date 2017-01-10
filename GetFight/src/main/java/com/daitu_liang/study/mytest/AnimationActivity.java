package com.daitu_liang.study.mytest;

import android.animation.ValueAnimator;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;

public class AnimationActivity extends AppCompatActivity {

    private Button btn;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mIconLayoutParams;


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

}
