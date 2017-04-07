package com.daitu_liang.study.mytest.dispatchevent;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * 1.ViewGroup才有拦截，view没有拦截
 * 2.自定义ViewGroup的时候，一般不建议重写分发方法，主要重写拦截和处理方法
 * 2.who消费的Down事件，who就继续消费后续事件Move和Up
 * Created by leixiaoliang on 2017/2/6.
 */
public class TestDispatchEvent extends ViewGroup {
    public TestDispatchEvent(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * 是否干任务，false 交给自己的onInterceptTouchEvent，
     * true交给自己的onTouchEvent
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 是否拦截任务，false 交给下一级的dispatchTouchEvent进行分发，
     * true 自己干任务，交给自己onTouchEvent
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 自己是否干掉任务，false，交给上一级的onTouchEvent去做，
     * true 自己干掉任务，事件消失
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        switch (MotionEvent.ACTION_DOWN)event.getAction()==;
        return super.onTouchEvent(event);
    }
}
