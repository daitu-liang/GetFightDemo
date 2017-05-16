package com.daitu_liang.study.mytest.entity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leixiaoliang on 2017/5/16.
 * 邮箱：lxliang1101@163.com
 */

public class Album extends DataSupport {
    private String name;

    private float price;

    private byte[] cover;

    private List<Song> songs = new ArrayList<Song>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
