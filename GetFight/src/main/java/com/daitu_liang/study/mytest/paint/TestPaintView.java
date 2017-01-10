package com.daitu_liang.study.mytest.paint;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

/**
 * Created by leixiaoliang on 2016/12/13.
 */
public class TestPaintView extends View {
    private final Paint mGesturePaint = new Paint();
    private final Paint aPaint = new Paint();
    private final Paint bPaint = new Paint();
    private final Path mPath = new Path();
    private float value;
    private Float value1;
    private ValueAnimator valueAnimator1;
    private boolean flag=true;
    // 定义几种初始颜色
    private static final int COLOR_BLUE = 0xff0000ff;
    private static final int COLOR_GREEN = 0xff00ff00;
    private static final int COLOR_RED = 0xffff0000;

    // 定义起始和最终颜色
    private int mStartColor = COLOR_BLUE;
    private int mToColor = COLOR_RED;

    public TestPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public TestPaintView(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        mGesturePaint.setAntiAlias(true);
        mGesturePaint.setStyle(Paint.Style.STROKE);
        mGesturePaint.setStrokeWidth(6);
        mGesturePaint.setColor(Color.RED);

//        mGesturePaint.setColorFilter(new ColorMatrixColorFilter());
//        aPaint.setAntiAlias(true);
//        aPaint.setStyle(Paint.Style.STROKE);
//        aPaint.setStrokeWidth(2);
//        aPaint.setColor(Color.BLUE);
//
//        bPaint.setAntiAlias(true);
//        bPaint.setStyle(Paint.Style.STROKE);
//        bPaint.setStrokeWidth(8);
//        bPaint.setColor(Color.YELLOW);

        //CornerPathEffect(float radius)--参数为圆角半径
//        mGesturePaint.setPathEffect(new CornerPathEffect(10));

        // DashPathEffect(float intervals[], float phase)
        // －－intervals为虚线的ON和OFF数组，offset为绘制时的偏移量
//        mGesturePaint.setPathEffect(new DashPathEffect(new float[]{20,10,5,10},20));

        //DiscretePathEffect(float segmentLength, float deviation)
        //segmentLength指定最大的段长，deviation指定偏离量
//        mGesturePaint.setPathEffect(new DiscretePathEffect(8.0f, 15.0f));

        //athDashPathEffect(Path shape, float advance, float phase,Style style)
        //shape则是指填充图形，advance指每个图形间的间距，phase为绘制时的偏移量，style为该类自由的枚举值
//        Path p = new Path();
//        p.addRect(20, 20, 50,50, Path.Direction.CCW);
//        mGesturePaint.setPathEffect(new PathDashPathEffect(p, 12.0f, 20.0f, PathDashPathEffect.Style.ROTATE)  );

        //ComposePathEffect(PathEffect outerpe, PathEffect innerpe)
        //组合效果
//       mGesturePaint.setPathEffect(new ComposePathEffect(new CornerPathEffect(8),new DiscretePathEffect(8.0f, 15.0f)));

        //SumPathEffect(PathEffect first, PathEffect second)叠加效果
//        mGesturePaint.setPathEffect(new SumPathEffect(new CornerPathEffect(8),new DiscretePathEffect(8.0f, 15.0f)));
//        mGesturePaint.setPathEffect(new PathEffect());
//        for (int i = 1; i <= 15; i++) {
//            // 生成15个点,随机生成它们的坐标,并将它们连成一条Path
//            mPath.lineTo(i * 50, (float) Math.random() * 100);
//        }

        valueAnimator1 = ValueAnimator.ofFloat(0, 800);
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                             @Override
                                             public void onAnimationUpdate(ValueAnimator animation) {
                                                 value1 = (Float) animation.getAnimatedValue();
                                                 invalidate();
                                             }
                                         }

        );
        valueAnimator1.setDuration(1000 * 5);
        valueAnimator1.setRepeatCount(Animation.INFINITE);
        valueAnimator1.setRepeatMode(Animation.RESTART);


        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 400);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (Float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1000 * 5);
        valueAnimator.setRepeatCount(Animation.INFINITE);
        valueAnimator.setRepeatMode(Animation.RESTART);
        valueAnimator.start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                flag=false;
                valueAnimator1.start();


            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });




    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.moveTo(100, 50);//用于移动移动画笔

        if(flag){
            mPath.lineTo(100 + value, 50 + value);//lineTo 用于进行直线绘制。
        }

        if(!flag){
            mPath.lineTo(100,200+value1);
        }

//        if(value>300&&value<600){
//            mPath.lineTo(400-value,350+value);//lineTo 用于进行直线绘制。
//        }
//        if(value>600&&value<900){
//            mPath.lineTo(400+value,350+value);//lineTo 用于进行直线绘制。
//        }
//        mPath.lineTo(200,800);//lineTo 用于进行直线绘制。
//        mPath.lineTo(450,1500);//lineTo 用于进行直线绘制。
//        mPath.lineTo(800,900);//lineTo 用于进行直线绘制。
//        mPath.lineTo(250,1000);//lineTo 用于进行直线绘制。

//        mPath.quadTo(800,500,100,1000);

        //quadTo 用于绘制圆滑曲线，即贝塞尔曲线。
        //  mPath.quadTo(x1, y1, x2, y2) (x1,y1) 为控制点，(x2,y2)为结束点。

//        mPath.cubicTo(505,100,10,900,900,1500);

        //arcTo 用于绘制弧线（实际是截取圆或椭圆的一部分）。
//        mPath.arcTo(new RectF(10, 10, 900, 900), 0, 90);

        canvas.drawPath(mPath, mGesturePaint);

    }
}
