package com.daitu_liang.study.mytest.dispatchevent;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by leixiaoliang on 2017/2/6.
 */
public class TestDispatchEvent extends ViewGroup {
    public TestDispatchEvent(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
