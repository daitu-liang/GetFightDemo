package com.daitu_liang.study.mytest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by leixiaoliang on 2017/4/19.
 * 邮箱：lxliang1101@163.com
 */

public class ZoomLoopScrollViewPager extends ViewPager {

    public ZoomLoopScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ZoomLoopScrollViewPager(Context context) {
        super(context);
    }

    @Override
    protected boolean drawChild(Canvas paramCanvas, View paramView, long drawingTime) {
        paramCanvas.save();
        int scrollPos = getScrollX() + getWidth() / 2;
        int widthCenter = paramView.getLeft() + paramView.getWidth() / 2;
        int heightCenter = paramView.getTop() + paramView.getHeight() / 2;

        int m = scrollPos - widthCenter;
        int isLeftOrRight = 0;
        if (m > 0)
            isLeftOrRight = 1;
        int i1 = Math.abs(m);
        float postionXY = 1.0F - 0.2F * (1.0F * Math.min(getWidth(), i1) / getWidth());

        if (isLeftOrRight != 0) {
            paramCanvas.scale(postionXY, postionXY, paramView.getLeft() + paramView.getWidth(), heightCenter);
        } else {
            paramCanvas.scale(postionXY, postionXY, paramView.getLeft(), heightCenter);
        }
        super.drawChild(paramCanvas, paramView, drawingTime);
        paramCanvas.restore();
        return true;
    }
}

