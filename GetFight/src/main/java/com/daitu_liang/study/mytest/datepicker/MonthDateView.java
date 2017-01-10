package com.daitu_liang.study.mytest.datepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import com.daitu_liang.study.mytest.R;

import java.util.Calendar;
import java.util.List;

public class MonthDateView extends View {
    private static final int NUM_COLUMNS = 7;
    private static final int NUM_ROWS = 6;
    private final Bitmap mSignBg;
    private final int mSignBgW;
    private final int mSignBgH;
    private final Rect srcRec;
    private final Bitmap mSignCurrentBg;
    private final int mSignCurrentBgW;
    private final int mSignCurrentBgH;
    private final Rect srcCurrentRec;
    private final Paint paintOne;
    private final Paint paintTwo;
    private Paint mPaint;
    private int mDayColor = Color.parseColor("#000000");
    private int mSelectDayColor = Color.parseColor("#ffffff");
    private int mCurrentColor = Color.parseColor("#ff0000");
    private int mSelectBGColor = Color.parseColor("#FFFF0000");
    private int mCurrYear, mCurrMonth, mCurrDay;
    private int mSelYear, mSelMonth, mSelDay;
    private int mColumnSize, mRowSize;
    private DisplayMetrics mDisplayMetrics;
    private int mDaySize = 18;
    private TextView tv_date, tv_week;
    private int weekRow;
    private int[][] daysString;
    private int mCircleRadius = 6;
    private DateClick dateClick;
    private int mCircleColor = Color.parseColor("#ff0000");
    private int mSignedColor = Color.parseColor("#DD3D5A74");

    private List<Integer> daysHasThingList;

    public MonthDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDisplayMetrics = getResources().getDisplayMetrics();
        mSignBg = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_signed_day)).getBitmap();
        mSignBgW = mSignBg.getWidth();
        mSignBgH = mSignBg.getHeight();

        mSignCurrentBg = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_signed_current_day)).getBitmap();
        mSignCurrentBgW = mSignCurrentBg.getWidth();
        mSignCurrentBgH = mSignCurrentBg.getHeight();

        Calendar calendar = Calendar.getInstance();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mCurrYear = calendar.get(Calendar.YEAR);
        mCurrMonth = calendar.get(Calendar.MONTH);
        mCurrDay = calendar.get(Calendar.DATE);
        setSelectYearMonth(mCurrYear, mCurrMonth, mCurrDay);
        srcRec = new Rect(0, 0, mSignBgW, mSignBgH);
        srcCurrentRec = new Rect(0, 0, mSignCurrentBgW, mSignCurrentBgH);

        //日期底部背景
        paintOne = new Paint();
        paintOne.setAntiAlias(true);
        paintOne.setColor(Color.BLUE);// 设置红色
        paintOne.setStyle(Paint.Style.FILL);
        //日期底部背景
        paintTwo = new Paint();
        paintTwo.setAntiAlias(true);
        paintTwo.setColor(Color.GREEN);// 设置红色
        paintTwo.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initSize();
        daysString = new int[6][7];
        mPaint.setTextSize(mDaySize * mDisplayMetrics.scaledDensity);
        String dayString;
        int mMonthDays = DateUtils.getMonthDays(mSelYear, mSelMonth);
        int weekNumber = DateUtils.getFirstDayWeek(mSelYear, mSelMonth);
        Log.d("DateView", "DateView:" + mSelMonth + "月1号周" + weekNumber);
        for (int day = 0; day < mMonthDays; day++) {
            dayString = (day + 1) + "";
            int column = (day + weekNumber - 1) % 7;
            int row = (day + weekNumber - 1) / 7;
            daysString[row][column] = day + 1;
            int startX = (int) (mColumnSize * column + (mColumnSize - mPaint.measureText(dayString)) / 2);
            int startY =(int) (mRowSize * row + mRowSize / 2 - (mPaint.ascent() + mPaint.descent()) / 2);
            if (dayString.equals(mSelDay + "")) {
                //绘制背景色矩形
                int startRecX = mColumnSize * column;
                int startRecY = mRowSize * row;
                int endRecX = startRecX + mColumnSize;
                int endRecY = startRecY + mRowSize;
                mPaint.setColor(mSelectBGColor);
//	wujiaxing	canvas.drawRect(startRecX, startRecY, endRecX, endRecY, mPaint);
                canvas.drawCircle((startRecX + endRecX) / 2, (startRecY + endRecY) / 2, (endRecX - startRecX) / 2 - 10, mPaint);
                //记录第几行，即第几周
                weekRow = row + 1;
            }
            //绘制事务圆形标志
            drawCircle(row, column, day + 1, canvas);
            if (dayString.equals(mSelDay + "")) {
                mPaint.setColor(mSelectDayColor);
            } else if (dayString.equals(mCurrDay + "") && mCurrDay != mSelDay && mCurrMonth == mSelMonth) {
                //正常月，选中其他日期，则今日为红色
                mPaint.setColor(mCurrentColor);
                Log.d("DateView", "mCurr-----Day:" + mCurrDay);
            } else {
                mPaint.setColor(mDayColor);
            }

           /* //日期底部
            int startRecX = mColumnSize * column;
            int startRecY = mRowSize * row;
            int endRecX = startRecX + mColumnSize;
            int endRecY = startRecY + mRowSize;
            int left=startRecX;
            int leftTop=endRecY;
            int right=endRecX;
            int rightBottom=endRecY+mRowSize;
            if(day==5||day==15){
                canvas.drawRect(left,leftTop,right,rightBottom,paintTwo);
            }*/


            canvas.drawText(dayString, startX, startY, mPaint);
            if (tv_date != null) {
                tv_date.setText(mSelYear + "年" + (mSelMonth + 1) + "月");
            }
            if (tv_week != null) {
                tv_week.setText("第" + weekRow + "周");
            }
        }
    }

    private void drawCircle(int row, int column, int day, Canvas canvas) {

        int startRecX = mColumnSize * column;
        int startRecY = mRowSize * row;
        int endRecX = startRecX + mColumnSize;
        int endRecY = startRecY + mRowSize;
        Log.d("DateView", "mCurrDay:" + mCurrDay + "--------day=" + day);
        if (daysHasThingList != null && daysHasThingList.size() > 0) {
            if (!daysHasThingList.contains(day)) return;
//			mPaint.setColor(mCircleColor);
            mPaint.setColor(mSignedColor);
//			float circleX = (float) (mColumnSize * column +	mColumnSize*0.8);
//			float circley = (float) (mRowSize * row + mRowSize*0.2);
//			canvas.drawCircle(circleX, circley, mCircleRadius, mPaint);
            //绘制签到过的背景色矩形
//			canvas.drawCircle((startRecX+endRecX)/2, (startRecY+endRecY)/2, (endRecX-startRecX)/2-10, mPaint);

            if (day == mCurrDay) {
                //当前日
                Rect desCurrentRec = new Rect(startRecX, startRecY, endRecX, endRecY);
                canvas.drawBitmap(mSignCurrentBg, srcCurrentRec, desCurrentRec, mPaint);
            }else {
                Rect desRec = new Rect(startRecX, startRecY, endRecX, endRecY);
                canvas.drawBitmap(mSignBg, srcRec, desRec, mPaint);
            }

            ////日期底部
           /* int left=startRecX;
            int leftTop=endRecY;
            int right=endRecX;
            int rightTop=endRecY+mRowSize;
            canvas.drawRect(left,leftTop,right,rightTop,paintOne);*/
        }

    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private int downX = 0, downY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventCode = event.getAction();
        switch (eventCode) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                downY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                int upX = (int) event.getX();
                int upY = (int) event.getY();
            /*if(Math.abs(upX-downX) < 10 && Math.abs(upY - downY) < 10){//点击事件
				performClick();
				doClickAction((upX + downX)/2,(upY + downY)/2);
			}else if((Math.abs(upX-downX)/mColumnSize)>=1&&Math.abs(upX-downX)>=40){
				if (upX-downX>0) {
					onLeftClick();
				}else{
					onRightClick();
				}
			}*/
                break;
        }
        return true;
    }

    /**
     * 初始化列宽行高
     */
    private void initSize() {
        mColumnSize = getWidth() / NUM_COLUMNS;
        mRowSize = getHeight() / NUM_ROWS;
    }

    /**
     * 设置年月
     *
     * @param year
     * @param month
     */
    private void setSelectYearMonth(int year, int month, int day) {
        mSelYear = year;
        mSelMonth = month;
        mSelDay = day;
    }

    /**
     * 执行点击事件
     *
     * @param x
     * @param y
     */
    private void doClickAction(int x, int y) {
        int row = y / mRowSize;
        int column = x / mColumnSize;
        setSelectYearMonth(mSelYear, mSelMonth, daysString[row][column]);
        invalidate();
        //执行activity发送过来的点击处理事件
        if (dateClick != null) {
            dateClick.onClickOnDate();
        }
    }

    /**
     * 左点击，日历向后翻页
     */
    public void onLeftClick() {
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDay;
        if (month == 0) {//若果是1月份，则变成12月份
            year = mSelYear - 1;
            month = 11;
        } else if (DateUtils.getMonthDays(year, month) == day) {
            //如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month - 1;
            day = DateUtils.getMonthDays(year, month);
        } else {
            month = month - 1;
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }

    /**
     * 右点击，日历向前翻页
     */
    public void onRightClick() {
        int year = mSelYear;
        int month = mSelMonth;
        int day = mSelDay;
        if (month == 11) {//若果是12月份，则变成1月份
            year = mSelYear + 1;
            month = 0;
        } else if (DateUtils.getMonthDays(year, month) == day) {
            //如果当前日期为该月最后一点，当向前推的时候，就需要改变选中的日期
            month = month + 1;
            day = DateUtils.getMonthDays(year, month);
        } else {
            month = month + 1;
        }
        setSelectYearMonth(year, month, day);
        invalidate();
    }

    /**
     * 获取选择的年份
     *
     * @return
     */
    public int getmSelYear() {
        return mSelYear;
    }

    /**
     * 获取选择的月份
     *
     * @return
     */
    public int getmSelMonth() {
        return mSelMonth;
    }

    /**
     * 获取选择的日期
     *
     * @return
     */
    public int getmSelDay() {
        return this.mSelDay;
    }

    /**
     * 普通日期的字体颜色，默认黑色
     *
     * @param mDayColor
     */
    public void setmDayColor(int mDayColor) {
        this.mDayColor = mDayColor;
    }

    /**
     * 选择日期的颜色，默认为白色
     *
     * @param mSelectDayColor
     */
    public void setmSelectDayColor(int mSelectDayColor) {
        this.mSelectDayColor = mSelectDayColor;
    }

    /**
     * 选中日期的背景颜色，默认蓝色
     *
     * @param mSelectBGColor
     */
    public void setmSelectBGColor(int mSelectBGColor) {
        this.mSelectBGColor = mSelectBGColor;
    }

    /**
     * 当前日期不是选中的颜色，默认红色
     *
     * @param mCurrentColor
     */
    public void setmCurrentColor(int mCurrentColor) {
        this.mCurrentColor = mCurrentColor;
    }

    /**
     * 日期的大小，默认18sp
     *
     * @param mDaySize
     */
    public void setmDaySize(int mDaySize) {
        this.mDaySize = mDaySize;
    }

    /**
     * 设置显示当前日期的控件
     *
     * @param tv_date 显示日期
     * @param tv_week 显示周
     */
    public void setTextView(TextView tv_date, TextView tv_week) {
        this.tv_date = tv_date;
        this.tv_week = tv_week;
        invalidate();
    }

    /**
     * 设置事务天数
     *
     * @param daysHasThingList
     */
    public void setDaysHasThingList(List<Integer> daysHasThingList) {
        this.daysHasThingList = daysHasThingList;
    }

    /***
     * 设置圆圈的半径，默认为6
     *
     * @param mCircleRadius
     */
    public void setmCircleRadius(int mCircleRadius) {
        this.mCircleRadius = mCircleRadius;
    }

    /**
     * 设置圆圈的半径
     *
     * @param mCircleColor
     */
    public void setmCircleColor(int mCircleColor) {
        this.mCircleColor = mCircleColor;
    }

    /**
     * 设置日期的点击回调事件
     *
     * @author shiwei.deng
     */
    public interface DateClick {
        public void onClickOnDate();
    }

    /**
     * 设置日期点击事件
     *
     * @param dateClick
     */
    public void setDateClick(DateClick dateClick) {
        this.dateClick = dateClick;
    }

    /**
     * 跳转至今天
     */
    public void setTodayToView() {
        setSelectYearMonth(mCurrYear, mCurrMonth, mCurrDay);
        invalidate();
    }
}
