package com.daitu_liang.study.mytest.entity;

import java.io.Serializable;

/**
 * Created by leixiaoliang on 2017/3/31.
 * 邮箱：lxliang1101@163.com
 */

public class CommentsEntity implements Serializable {
    private String text;
    private String user_name;
    private String comment_count;
    private String digg_count;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(String digg_count) {
        this.digg_count = digg_count;
    }
}
