package com.daitu_liang.study.mytest.effect.item;

/**
 * Created by leixiaoliang on 2016/12/21.
 */
public class StarInfo {
    // 缩放比例
    public float sizePercent;
    // x位置
    public int xLocation;
    // y位置
    public int yLocation;
    // 透明度
    public float alpha;
    // 漂浮方向
    public  int direction;
    // 漂浮速度
    public int speed;



    public float getSizePercent() {
        return sizePercent;
    }

    public void setSizePercent(float sizePercent) {
        this.sizePercent = sizePercent;
    }

    public int getxLocation() {
        return xLocation;
    }

    public void setxLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getyLocation() {
        return yLocation;
    }

    public void setyLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
