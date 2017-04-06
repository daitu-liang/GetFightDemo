package com.daitu_liang.study.mytest.paint;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by leixiaoliang on 2017/4/6.
 * 邮箱：lxliang1101@163.com
 */

public class PathView extends View {
    private final Paint mPaint = new Paint();
    private float value;
    private float value1;
    private int mWidth;
    private int mHeight;

    public PathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }


    private void initPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.RED);

    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);  // 移动坐标系到屏幕中心
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        path.lineTo(100,100);
        RectF oval = new RectF(0,0,300,300);//小圆
        path.addArc(oval,0,value);
// path.arcTo(oval,0,270,true);             // <-- 和上面一句作用等价
        canvas.drawPath(path,mPaint);
//--------------------------------------------------
        RectF oval2 = new RectF(0,0,500,500);//大圆
        //直接添加一个圆弧到path中
        path.addArc(oval2,0,value1);

        //添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
//       path.arcTo(oval,0,value1,true);             // <-- 和上面一句作用等价
        canvas.drawPath(path,mPaint);

        canvas.drawArc(-200,-200,200,200,0,value,false,mPaint);
    }

    public void  startPath(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 270);
        valueAnimator.setDuration(1000*5);
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.setRepeatMode(Animation.RESTART);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (Float) animation.getAnimatedValue();
                value1=-(Float) animation.getAnimatedValue();
                invalidate();
            }
        });
    }
}
