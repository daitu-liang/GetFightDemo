package com.daitu_liang.study.mytest.paint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.effect.item.StarInfoIm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leixiaoliang on 2016/12/21.
 */
public class IndraftView extends View {

    private static final String TAG = "IndraftView";
    private static final float[][] STAR_LOCATION = new float[][]{
            {0.8f, 0.2f}, {0.28f, 0.35f}, {0.5f, 0.05f},
            {0.85f, 0.45f}, {0.5f, 0.8f}, {0.15f, 0.8f},
            {0.2f, 0.3f}, {0.77f, 0.4f}, {0.75f, 0.5f},
            {0.8f, 0.55f}, {0.9f, 0.6f}, {0.1f, 0.7f},
            {0.3f, 0.1f}, {0.7f, 0.8f}, {0.25f, 0.6f}
    };
    // y = Asin(wx+b)+h
    private static final float STRETCH_FACTOR_A = 20;
    private static final int OFFSET_Y = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int TOP = 3;
    private static final int BOTTOM = 4;
    private static final int FREE_POINT=5;
    private int mFloatTransLowSpeed;
    private int mFloatTransMidSpeed;
    private int mFloatTransFastSpeed;
    private Resources mResources;
    private Bitmap mBackBitmap;
    private int mBackWidth;
    private int mBackHeight;
    private Bitmap mStarOne, mStarTwo, mStarThree;
    private int mStarOneWidth;
    private int mStarOneHeight;
    private int mStarTwoWidth;
    private int mStarTwoHeight;
    private int mStarThreeWidth;
    private int mStarThreeHeight;
    private int mTotalWidth, mTotalHeight;
    private int ANIMATION_TIME=1000;
    private int mCenterY;
    private Rect mSrcRect;
    private Rect mDestRect;
    private int mStarCount = 15;
    private Paint paint;

    List<StarInfoIm> mStarInfos = new ArrayList<>();
    private Rect mStarOneSrcRect;
    private Rect mStarThreeSrcRect;
    private Rect mStarTwoSrcRect;
    private float mCycleFactorW;
    private AnimationThread thread;
    private int mCenterX;
    private Context context;

    public IndraftView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initData(context);
        initBitmapInfo();
        initPaint();


    }

    public IndraftView(Context context) {
        super(context);


    }

    public void startAnimationFloat(){
//         thread=new AnimationThread() ;
//        thread.start();
    }
    public void stopAnimationFloat(){

//        thread.destroy();
    }
    class AnimationThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    // 为了保证效果的同时，尽可能将cpu空出来，供其他部分使用
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                }
                postInvalidate();
            }
        }
    }
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 防抖动
        paint.setDither(true);
        // 开启图像过滤
        paint.setFilterBitmap(true);
    }


    //定义三种不同快慢的漂浮速度
    private void initData(Context context) {
        this.context=context;
        mResources = getResources();
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();

        mTotalWidth = dm.widthPixels;
        mTotalHeight = dm.heightPixels;
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "--1--mTotalHeight=" + mTotalHeight);
        mFloatTransLowSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f,
                mResources.getDisplayMetrics());
        mFloatTransMidSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.75f,
                mResources.getDisplayMetrics());
        mFloatTransFastSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f,
                mResources.getDisplayMetrics());
    }

    /**
     * 获取星球大小
     */
    private float getStarSize(float start, float end) {
        float nextFloat = (float) Math.random();
        if (start < nextFloat && nextFloat < end) {
            return nextFloat;
        } else {
            // 如果不处于想要的数据段，则再随机一次，因为不断递归有风险
            return (float) Math.random();
        }

    }

    /**
     * init bitmap info
     */
    private void initBitmapInfo() {

        mBackBitmap = ((BitmapDrawable) mResources.getDrawable(R.mipmap.back)).getBitmap();
        mBackWidth = mBackBitmap.getWidth();
        mBackHeight = mBackBitmap.getHeight();

        mStarOne = ((BitmapDrawable) mResources.getDrawable(R.drawable.snow1)).getBitmap();
        mStarOneWidth = mStarOne.getWidth();
        mStarOneHeight = mStarOne.getHeight();

        mStarTwo = ((BitmapDrawable) mResources.getDrawable(R.drawable.snow2)).getBitmap();
        mStarTwoWidth = mStarTwo.getWidth();
        mStarTwoHeight = mStarTwo.getHeight();

        mStarThree = ((BitmapDrawable) mResources.getDrawable(R.drawable.snow3)).getBitmap();
        mStarThreeWidth = mStarThree.getWidth();
        mStarThreeHeight = mStarThree.getHeight();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        mCenterX = mTotalWidth / 2;
        mCenterY = mTotalHeight / 2;
        mSrcRect = new Rect();
        mDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);

        mStarOneSrcRect = new Rect(0, 0, mStarOneWidth, mStarOneHeight);
        mStarTwoSrcRect = new Rect(0, 0, mStarTwoWidth, mStarTwoHeight);
        mStarThreeSrcRect = new Rect(0, 0, mStarThreeWidth, mStarThreeHeight);
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "---2-mTotalHeight=" + mTotalHeight);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mTotalWidth = MeasureSpec.getSize(widthMeasureSpec);
        mTotalHeight = MeasureSpec.getSize(heightMeasureSpec);
        mCenterX = mTotalWidth / 2;
        mCenterY = mTotalHeight / 2;
        initStarInfo();
        setMeasuredDimension(mTotalWidth,mTotalHeight);
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "---onMeasure----mTotalHeight=" + mTotalHeight);
    }

    /**
     * 初始化星球信息
     */
    private void initStarInfo() {

        StarInfoIm starInfo = null;
        Random random = new Random();
        for (int i = 0; i < mStarCount; i++) {
            // 获取星球大小比例
            float starSize = getStarSize(0.4f, 0.8f);
            // 初始化星球大小
            float[] starLocation = STAR_LOCATION[i];
            starInfo = new StarInfoIm(context);
            starInfo.sizePercent = starSize;

            // 初始化星球透明度
            starInfo.alpha = getStarSize(0.3f, 0.8f);
            // 初始化星球位置
            starInfo.xLocation = (int) (starLocation[0] * mTotalWidth);
            starInfo.yLocation = (int) (starLocation[1] * mTotalHeight);
            Log.i(TAG, "xLocation = " + starInfo.xLocation + "--yLocation = "
                    + starInfo.yLocation);
            Log.i(TAG, "stoneSize = " + starSize + "---stoneAlpha = "
                    + starInfo.alpha);
            TranslateAnimation animation=new TranslateAnimation(starInfo.xLocation,starInfo.yLocation,mCenterX,mCenterY);
            animation.setDuration(ANIMATION_TIME);
            animation.setInterpolator(new AccelerateInterpolator());
            animation.setRepeatMode(Animation.RESTART);
            animation.setRepeatCount(Animation.INFINITE);
            mStarInfos.add(starInfo);

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mStarInfos.size(); i++) {
            StarInfoIm starInfo =mStarInfos.get(i);
            drawStarDynamic(i, starInfo, canvas, paint);
        }
    }

    private void drawStarDynamic(int count, StarInfoIm starInfo,
                                 Canvas canvas, Paint paint) {
//        resetStarFloat(starInfo);
        float starAlpha = starInfo.alpha;
        int xLocation = starInfo.xLocation;
        int yLocation = starInfo.yLocation;
        float sizePercent = starInfo.sizePercent;

        xLocation = (int) (xLocation / sizePercent);
        yLocation = (int) (yLocation / sizePercent);

        Bitmap bitmap = null;
        Rect srcRect = null;
        Rect destRect = new Rect();

        if (count % 3 == 0) {

            bitmap = mStarOne;
            srcRect = mStarOneSrcRect;
            destRect.set(xLocation, yLocation,
                    xLocation + mStarOneWidth, yLocation
                            + mStarOneHeight);
        } else if (count % 2 == 0) {
            bitmap = mStarThree;
            srcRect = mStarThreeSrcRect;
            destRect.set(xLocation, yLocation, xLocation
                    + mStarThreeWidth, yLocation + mStarThreeHeight);
        } else {
            bitmap = mStarTwo;
            srcRect = mStarTwoSrcRect;
            destRect.set(xLocation, yLocation, xLocation
                    + mStarTwoWidth, yLocation + mStarTwoHeight);
        }
        paint.setAlpha((int) (starAlpha * 255));
        canvas.save();
        canvas.scale(sizePercent, sizePercent);
        canvas.drawBitmap(bitmap, srcRect, destRect, paint);
        canvas.restore();
    }



}
