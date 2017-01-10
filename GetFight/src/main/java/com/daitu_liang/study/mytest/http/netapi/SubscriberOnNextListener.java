package com.daitu_liang.study.mytest.http.netapi;

/**
 * Created by leixiaoliang on 2017/1/6.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
   void onError(Throwable e);
    void onCompleted();
}
