package com.daitu_liang.study.mytest.paint;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.effect.item.StarInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by leixiaoliang on 2016/12/21.
 */
public class FloatView extends View {

    private static final String TAG = "FloatView";
    private static final float[][] STAR_LOCATION = new float[][]{
            {0.8f, 0.2f}, {0.28f, 0.35f}, {0.5f, 0.05f},
            {0.85f, 0.45f}, {0.5f, 0.8f}, {0.15f, 0.8f},
            {0.2f, 0.3f}, {0.77f, 0.4f}, {0.75f, 0.5f},
            {0.8f, 0.55f}, {0.9f, 0.6f}, {0.1f, 0.7f},
            {0.3f, 0.1f}, {0.7f, 0.8f}, {0.25f, 0.6f},
            {0.1f, 0.75f}, {0.4f, 0.2f}, {0.5f, 0.7f}
    };
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;
    public static final int FREE_POINT=4;
    public static final int DEFAULT_TYPE=100;
    public  int floatTyep=100;//默认情况
    private int mFloatTransLowSpeed;
    private int mFloatTransMidSpeed;
    private int mFloatTransFastSpeed;
    private Resources mResources;
    private int mTotalWidth, mTotalHeight;
    private Bitmap mStarOne, mStarTwo, mStarThree;
    private int mStarOneWidth;
    private int mStarOneHeight;
    private int mStarTwoWidth;
    private int mStarTwoHeight;
    private int mStarThreeWidth;
    private int mStarThreeHeight;
    private int mCenterX;
    private int mCenterY;
    private Rect mSrcRect;
    private Rect mDestRect;
    private int mFloatCount = 18;
    private Paint paint;

    List<StarInfo> mStarInfos = new ArrayList<>();
    private Rect mStarOneSrcRect;
    private Rect mStarThreeSrcRect;
    private Rect mStarTwoSrcRect;
    private float mCycleFactorW;

    private boolean isRuning;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(isRuning){
                postInvalidate();
                handler.sendMessageDelayed(Message.obtain(),50);
            }
        }
    };


    public void releaseHandle(){
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }
    public FloatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
        initBitmapInfo();
        initPaint();
    }
    public void startAnimationFloat(){
        isRuning=true;
        handler.sendMessage(Message.obtain());
    }

    public void stopAnimationFloat(){
        isRuning=false;

    }
    public void restartAnimationFloat(){
        startAnimationFloat();
    }
    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // 防抖动
        paint.setDither(true);
        // 开启图像过滤
        paint.setFilterBitmap(true);
    }

    public void setFloatType(int floatType ){
        this.floatTyep=floatType;

    }

    //定义三种不同快慢的漂浮速度
    private void initData(Context context) {
        mResources = getResources();
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();

        mTotalWidth = dm.widthPixels;
        mTotalHeight = dm.heightPixels;
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "--1--mTotalHeight=" + mTotalHeight);
        //设置三个不同大小的速度值
        mFloatTransLowSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.5f,
                mResources.getDisplayMetrics());
        mFloatTransMidSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0.75f,
                mResources.getDisplayMetrics());
        mFloatTransFastSpeed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f,
                mResources.getDisplayMetrics());
    }

    /**
     * 获取星星大小
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
     * 设置动画目标,三张大小不同，样式不一，为了美观
     * init bitmap info
     */
    private void initBitmapInfo() {
        mStarOne = ((BitmapDrawable) mResources.getDrawable(R.drawable.star2)).getBitmap();
        mStarOneWidth = mStarOne.getWidth();
        mStarOneHeight = mStarOne.getHeight();

        mStarTwo = ((BitmapDrawable) mResources.getDrawable(R.drawable.star1)).getBitmap();
        mStarTwoWidth = mStarTwo.getWidth();
        mStarTwoHeight = mStarTwo.getHeight();

        mStarThree = ((BitmapDrawable) mResources.getDrawable(R.drawable.star3)).getBitmap();
        mStarThreeWidth = mStarThree.getWidth();
        mStarThreeHeight = mStarThree.getHeight();
    }

    /**
     *在测量完View并使用setMeasuredDimension函数之后View的大小基本上已经确定了，
     * 那么为什么还要再次确定View的大小呢？
     *  这是因为View的大小不仅由View本身控制，而且受父控件的影响，
     *  所以我们在确定View大小的时候最好使用系统提供的onSizeChanged回调函数。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = mTotalWidth / 2;
        mCenterY = mTotalHeight / 2;
        mSrcRect = new Rect();
        mDestRect = new Rect(0, 0, mTotalWidth, mTotalHeight);

        mStarOneSrcRect = new Rect(0, 0, mStarOneWidth, mStarOneHeight);
        mStarTwoSrcRect = new Rect(0, 0, mStarTwoWidth, mStarTwoHeight);
        mStarThreeSrcRect = new Rect(0, 0, mStarThreeWidth, mStarThreeHeight);
        Log.i(TAG, "mTotalWidth=" + mTotalWidth + "---2-mTotalHeight=" + mTotalHeight);
    }

    /**
     * 如果对View的宽高进行修改了，
     * 不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec);
     * 要调用 setMeasuredDimension( widthsize, heightsize); 这个函数。
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //初始化数据结束，然后在进行测量时进行初始化星星数据
        initStarInfo();
    }

    /**
     * 不同移动轨迹，除过左右上下，也可以定义其他方向，如对角线，曲线之类的
     * 初始化星星运行方向
     */
    private int getStarDirection() {
        int randomInt;
        Random random = new Random();
        if(floatTyep==100){
            randomInt = random.nextInt(3);
        }else {
            randomInt=floatTyep;
        }
        int direction = 0;
        switch (randomInt) {
            case 0:
                direction = LEFT;
                break;
            case 1:
                direction = RIGHT;
                break;
            case 2:
                direction = TOP;
                break;
            case 3:
                direction = BOTTOM;
                break;
            case 4:
                direction = FREE_POINT;
                break;
            default:
                break;
        }
        return direction;
    }

    private void resetStarFloat(StarInfo starInfo) {
        switch (starInfo.direction) {
            case LEFT:
                if (starInfo.xLocation < -20) {
                    starInfo.xLocation = mTotalWidth;
                } else {
                    starInfo.xLocation -= starInfo.speed;
                }

                break;
            case RIGHT:
                if (starInfo.xLocation > mTotalWidth+20) {
                    starInfo.xLocation = 0;
                } else {
                    starInfo.xLocation += starInfo.speed;
                }

                break;
            case TOP:
                if (starInfo.yLocation < -20) {
                    starInfo.yLocation = mTotalHeight;
                } else {
                    starInfo.yLocation -= starInfo.speed;
                }


                break;
            case BOTTOM:
                if (starInfo.yLocation > mTotalHeight+30) {
                    starInfo.yLocation = 0;
                } else {
                    starInfo.yLocation += starInfo.speed;
                }
                break;
            case FREE_POINT:

                if (starInfo.yLocation > mTotalHeight+30) {
                    starInfo.yLocation = 0;
                } else {
                    starInfo.yLocation += starInfo.speed;
                }

                if (starInfo.xLocation < -20) {
                    starInfo.xLocation = mTotalWidth;
                } else {
                    starInfo.xLocation -= starInfo.speed;
                }
                break;
            default:
                break;
        }
    }

    /**
     * 初始化星星信息
     */
    private void initStarInfo() {

        StarInfo starInfo = null;
        Random random = new Random();
        for (int i = 0; i < mFloatCount; i++) {
            // 获取星星大小比例
            float starSize = getStarSize(0.4f, 0.8f);
            //小球的坐标
            float[] starLocation = STAR_LOCATION[i];
            starInfo = new StarInfo();
            // 初始化星星大小
            starInfo.sizePercent = starSize;
            // 初始化漂浮速度
            int randomSpeed = random.nextInt(3);
            switch (randomSpeed) {
                case 0:
                    starInfo.speed = mFloatTransLowSpeed;
                    break;
                case 1:
                    starInfo.speed = mFloatTransMidSpeed;
                    break;
                case 2:
                    starInfo.speed = mFloatTransFastSpeed;
                    break;
                default:
                    starInfo.speed = mFloatTransMidSpeed;
                    break;
            }
            // 初始化星星透明度
            starInfo.alpha = getStarSize(0.3f, 0.8f);
            // 初始化星星位置
            starInfo.xLocation = (int) (starLocation[0] * mTotalWidth);
            starInfo.yLocation = (int) (starLocation[1] * mTotalHeight);
            Log.i(TAG, "xLocation = " + starInfo.xLocation + "--yLocation = "
                    + starInfo.yLocation);
            Log.i(TAG, "stoneSize = " + starSize + "---stoneAlpha = "
                    + starInfo.alpha);
            // 初始化星星位置
            starInfo.direction = getStarDirection();
            mStarInfos.add(starInfo);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mStarInfos.size(); i++) {
            StarInfo starInfo = mStarInfos.get(i);
            drawStarDynamic(i, starInfo, canvas, paint);
        }
//        canvas.drawPicture(new Picture());

    }

    private void drawStarDynamic(int count, StarInfo starInfo,
                                 Canvas canvas, Paint paint) {
        resetStarFloat(starInfo);
        float starAlpha = starInfo.alpha;
        int xLocation = starInfo.xLocation;
        int yLocation = starInfo.yLocation;
        float sizePercent = starInfo.sizePercent;

        xLocation = (int) (xLocation / sizePercent);
        yLocation = (int) (yLocation / sizePercent);

        Bitmap bitmap = null;
        Rect srcRect = null;
        Rect destRect = new Rect();

        mStarOneSrcRect = new Rect(0, 0, mStarOneWidth, mStarOneHeight);
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
