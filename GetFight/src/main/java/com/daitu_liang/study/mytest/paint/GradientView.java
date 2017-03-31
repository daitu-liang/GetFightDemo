package com.daitu_liang.study.mytest.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;


/**
 * Created by leixiaoliang on 2016/12/13.
 */
public class GradientView extends View {
    private final Paint mGradientPaint = new Paint();
    // 定义几种初始颜色
    private static final int COLOR_BLUE = 0xff0000ff;
    private static final int COLOR_GREEN = 0xff00ff00;
    private static final int COLOR_RED = 0xffff0000;
    private static final int mCurrentColors=0xff00ff00;
    private final Context context;

    // 颜色数组
    private int[] mColors = new int[] {
            COLOR_RED, COLOR_GREEN, COLOR_GREEN, COLOR_BLUE
    };
    // 与颜色数组对应的位置数组
    private float[] mPositions = new float[] {
            0f, 0.2f, 0.4f, 1f
    };




    // 颜色数组
    private static final int[] COLOR_BLUES = new int[] {
            0xff1db6ff, 0xff1db6ff, 0xff0b58c2, 0xff002a6d
    };

    // 颜色对应的位置数组
    private static final float[] COLOR_LOCATIONS = new float[] {
            0, 0.15f, 0.65f, 1f
    };
    // 定义起始和最终颜色
    private int mStartColor = COLOR_BLUE;
    private int mToColor = COLOR_RED;
    private DisplayMetrics dm;
    private int screenWidth;
    private int screenHeight;
    private int SCREEN_HEIGHT_FRACTION;

    public GradientView(Context context) {
        super(context);
        this.context=context;
        initPaint();
    }
    private void initPaint() {
//        DisplayMetrics displayMetrics=new DisplayMetrics();
        dm=context.getApplicationContext().getResources().getDisplayMetrics();


         screenWidth = dm.widthPixels;
         screenHeight = dm.heightPixels;
        Log.i("info","screenWidth="+screenWidth+"   screenHeight="+screenHeight);
        mGradientPaint.setAntiAlias(true);
        mGradientPaint.setStyle(Paint.Style.FILL);
//        mGradientPaint.setStrokeWidth(6);
        mGradientPaint.setColor(Color.RED);

        /*设置图像效果，使用Shader可以绘制出各种渐变效果；
        Shader下面有五个子类可用：
        BitmapShader :位图图像渲染
        LinearGradient:线性渲染
        RadialGradient:环形渲染
        SweepGradient:扫描渐变渲染/梯度渲染
        ComposeGradient:组合渲染，可以和其他几个子类组合起来使用*/
//        mGradientPaint.setShader(new LinearGradient(screenWidth, 0, screenWidth, screenHeight/3, mStartColor, mToColor, Shader.TileMode.MIRROR));
//        mGradientPaint.setShader(
//                new LinearGradient(screenWidth, 0, screenWidth, screenHeight/3, mColors, mPositions, Shader.TileMode.REPEAT));

//        mGradientPaint.setShader(new RadialGradient());


//        mGradientPaint.setColorFilter(new ColorMatrixColorFilter())
        SCREEN_HEIGHT_FRACTION=14/15;
      /*  RadialGradient mRadialGradient = new RadialGradient(screenWidth, screenHeight * SCREEN_HEIGHT_FRACTION,
                screenHeight * SCREEN_HEIGHT_FRACTION,
                mCurrentColors, COLOR_LOCATIONS, Shader.TileMode.MIRROR);
        mLightGradientPaint.setShader(mRadialGradient);
        canvas.drawRect(0, 0, mTotalWidth, screenHeight, mLightGradientPaint);*/
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Path  path=new Path();

      /*  canvas.drawRect(0, 0, screenWidth, screenHeight,
                mGradientPaint);

        canvas.drawLine(0,0,500,500,mGradientPaint);*/
       
        
        //创建位图
      /*  Bitmap mBitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.timg)).getBitmap();
        //将图scale成我们想要的大小
        mBitmap = Bitmap.createScaledBitmap(mBitmap, screenWidth, screenHeight, false);



        // 创建位图渲染
        BitmapShader bitmapShader = new BitmapShader(mBitmap,  Shader.TileMode.REPEAT,  Shader.TileMode.REPEAT);
        // 创建环形渐变
        RadialGradient radialGradient = new RadialGradient(screenWidth/3, screenWidth/3, screenWidth*3/4,
                Color.TRANSPARENT, Color.WHITE, Shader.TileMode.MIRROR);
        // 创建组合渐变，由于直接按原样绘制就好，所以选择Mode.SRC_OVER
        ComposeShader composeShader = new ComposeShader(bitmapShader, radialGradient,
                PorterDuff.Mode.DST_OUT);
        // 将组合渐变设置给paint
        mGradientPaint.setShader(composeShader);
        // 创建位图渲染
        canvas.drawCircle(screenWidth/3, screenWidth/3, screenWidth/3, mGradientPaint);*/

        mGradientPaint.setColor(Color.RED);
        canvas.drawColor(Color.BLUE);
        canvas.drawRect(new Rect(0, 0, 400, 400), mGradientPaint);

        // 保存画布状态
        canvas.save();
        canvas.scale(0.5f, 0.5f);
        mGradientPaint.setColor(Color.YELLOW);
        canvas.drawRect(new Rect(0, 0, 400, 400), mGradientPaint);
        // 画布状态回滚
        canvas.restore();
//        canvas.rotate(45,200,200);
//        canvas.scale(0.5f, 0.5f);

        //float sx:将画布在x方向上倾斜相应的角度，sx倾斜角度的tan值，
        //float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值.
        canvas.skew(1,0);
        mGradientPaint.setColor(0x8800ff00);
        canvas.drawRect(new Rect(0, 0, 400, 400), mGradientPaint);
    }
}
