package com.daitu_liang.study.mytest.entity;

import org.litepal.crud.DataSupport;

/**
 * Created by leixiaoliang on 2017/5/16.
 * 邮箱：lxliang1101@163.com
 */

public class Song extends DataSupport {
    private String name;
    private int duration;
    private String uselessField;
    private Album album;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getUselessField() {
        return uselessField;
    }

    public void setUselessField(String uselessField) {
        this.uselessField = uselessField;
    }
}
