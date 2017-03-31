package com.daitu_liang.study.mytest.http.Jokenet;

/**
 * Created by leixiaoliang on 2017/3/31.
 * 邮箱：lxliang1101@163.com
 */

public class HttpJokeResult<T> {
    private  String message;
    private T data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
