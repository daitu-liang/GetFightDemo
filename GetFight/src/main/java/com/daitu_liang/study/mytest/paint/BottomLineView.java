package com.daitu_liang.study.mytest.paint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

/**
 * Created by leixiaoliang on 2016/12/27.
 */
public class BottomLineView extends View {
    private static final String TAG ="BottomLineView" ;
    private Resources mResources;
    private int mTotalWidth;
    private int mTotalHeight;
    private int mRunLowSpeed;
    private int mRunMidSpeed;
    private int mRunFastSpeed;
    private Paint mPaint;
    // 定义几种初始颜色
    private static final int COLOR_BLUE = 0xFFFFFC40;
    private static final int COLOR_GREEN = 0xff00ff00;
    private static final int COLOR_RED = 0xffff0000;
    // 定义起始和最终颜色
    private int mStartColor = COLOR_BLUE;
    private int mToColor = COLOR_RED;
    private int valueRun;
    private int valueRunx;

    public BottomLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        initData(context);
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 防抖动
        mPaint.setDither(true);
        // 开启图像过滤
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(6);
//        mPaint.setColor(Color.RED);


    }
    //定义三种不同快慢速度
    private void initData(Context context) {
        mResources = getResources();
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        mTotalWidth = dm.widthPixels;
        mTotalHeight = dm.heightPixels;
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "--1--mTotalHeight=" + mTotalHeight);
        //设置三个不同大小的速度值
        mRunLowSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.4f,
                mResources.getDisplayMetrics());

        mRunMidSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.75f,
                mResources.getDisplayMetrics());

        mRunFastSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f,
                mResources.getDisplayMetrics());
        LinearGradient linearGradient =new LinearGradient(0, 0, mTotalWidth/2, mTotalHeight/3, mStartColor, mToColor, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.i(TAG,"mTotalHeight="+mTotalHeight+"----mTotalWidth="+mTotalWidth+" -----" +
                "---valueRun="+valueRun+"----mTotalHeight-valueRun="+(mTotalHeight-valueRun));
       canvas.drawLine(0,mTotalHeight,mTotalWidth/2+valueRunx,mTotalHeight-valueRun,mPaint);
//        canvas.drawLine(0,0,800,1200,mPaint);
        canvas.drawLine(mTotalWidth/2,mTotalHeight,mTotalWidth/4-valueRunx,mTotalHeight-valueRun,mPaint);
        canvas.drawLine(mTotalWidth*3/4,mTotalHeight,mTotalWidth/2-valueRunx,mTotalHeight-valueRun,mPaint);
    }

    public void starRun(){
        ValueAnimator valueAnimator=ValueAnimator.ofInt(0,1000);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.setRepeatMode(Animation.RESTART);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                valueRun=1000;//(int)animation.getAnimatedValue();
                valueRunx=200;//(int)animation.getAnimatedValue();

//                postInvalidate();
            }
        });
        valueAnimator.start();


//        ValueAnimator valueAnimatorX=ValueAnimator.ofInt(0,1000);
//        valueAnimatorX.setDuration(3000);
//        valueAnimatorX.setRepeatCount(Animation.INFINITE);
//        valueAnimatorX.setRepeatMode(Animation.RESTART);
//        valueAnimatorX.setInterpolator(new AccelerateDecelerateInterpolator());
//        valueAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                valueRunx=(int)animation.getAnimatedValue();
//                postInvalidate();
//            }
//        });
//        valueAnimatorX.start();
    }
}
