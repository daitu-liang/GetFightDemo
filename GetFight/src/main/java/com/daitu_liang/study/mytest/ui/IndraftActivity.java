package com.daitu_liang.study.mytest.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.squareup.leakcanary.RefWatcher;

import java.util.Random;

public class IndraftActivity extends AppCompatActivity {

    private static final String TAG = "IndraftActivity";
    private int ANIMATION_TIME = 1000;
    private int ANIMATION_SCALE_TIME = 1000;
    private RelativeLayout parent;
    private int mTotalWidth;
    private int mTotalHeight;
    private int count = 40;
    private Bitmap mStarOne, mStarTwo, mStarThree;
    private int mCenterX, mCenterY;
    private Bitmap bitmap;
    public static Intent getIntent(Context context) {
        Intent intent=new Intent(context, IndraftActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indraft);
        parent = (RelativeLayout) findViewById(R.id.float_btn);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            indraftStar();
        }
    }
    private void indraftStar() {
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        mTotalWidth = dm.widthPixels;
        mTotalHeight = dm.heightPixels;
        Log.i(TAG,"mTotalWidth="+mTotalWidth+"--------mTotalHeight="+mTotalHeight);

        mCenterX = mTotalWidth/2-30;
        mCenterY = mTotalHeight/2-100;

        //不同样式，大小的小球
        mStarOne = ((BitmapDrawable) getResources().getDrawable(R.drawable.snow1)).getBitmap();
        mStarTwo = ((BitmapDrawable) getResources().getDrawable(R.drawable.snow2)).getBitmap();
        mStarThree = ((BitmapDrawable) getResources().getDrawable(R.drawable.snow3)).getBitmap();
        Log.i(TAG,"mCenterX="+mCenterX+"----0----mCenterY="+mCenterY);

        //初始化，造批量小球
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            // 获取小球大小比例
            float starSize = getStarSize(0.2f, 0.8f);
            // 初始化小球大小
            ImageView starInfovView = new ImageView(this);
            starInfovView.setScaleX(starSize);
            starInfovView.setScaleY(starSize);
            // 初始化小球透明度
            starInfovView.setAlpha(getStarSize(0.5f, 1.8f));
            // 初始化小球位置，随机生成一定坐标范围内的小球
            int rvalueX =400-random.nextInt(800);
            int rvalueY = 450-random.nextInt(900);
            Log.i(TAG, "rvalueX = " + rvalueX + "--rvalueY = " + rvalueY);
            int x = mCenterX +rvalueX;
            int y = mCenterY +rvalueY;
            Log.i(TAG, "xLocation = " + x + "--yLocation = " + y);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                    (RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            starInfovView.setTop(y);
            starInfovView.setLeft(x);
            if (count % 3 == 0) {
                bitmap = mStarOne;
            } else if (count % 2 == 0) {
                bitmap = mStarThree;
            } else {
                bitmap = mStarTwo;
            }
            starInfovView.setImageBitmap(bitmap);
            starInfovView.setLayoutParams(layoutParams);
            AnimationSet animatorSet =new AnimationSet (false);
            ScaleAnimation scaleAnimation =new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
                    Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleAnimation.setDuration(ANIMATION_SCALE_TIME);
            scaleAnimation.setInterpolator(new AccelerateInterpolator());
            scaleAnimation.setRepeatCount(Animation.INFINITE);
            scaleAnimation.setRepeatMode(Animation.RESTART);
            animatorSet.addAnimation(scaleAnimation);

            TranslateAnimation translateAnimation = new TranslateAnimation(x,mCenterX ,y , mCenterY);
            translateAnimation.setDuration(ANIMATION_TIME);
            translateAnimation.setInterpolator(new AccelerateInterpolator());
            translateAnimation.setRepeatMode(Animation.RESTART);
            translateAnimation.setRepeatCount(Animation.INFINITE);
            animatorSet.addAnimation(translateAnimation);
            starInfovView.startAnimation(animatorSet);
            parent.addView(starInfovView);
        }
    }
    /**
     * 获取小球大小
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(parent!=null){
            parent.removeAllViews();
            parent=null;
        }
        RefWatcher refWatcher = GetFightApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }


}
