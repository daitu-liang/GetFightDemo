package com.kakaxi.fightdemo.network.uploaddowon;

/**
 * Created by leixiaoliang on 2017/4/15.
 * 邮箱：lxliang1101@163.com
 */

public interface DownloadProgressListener {
    /**
     * 下载进度
     * @param bytesRead
     * @param contentLength
     * @param done
     */

    void onDownloadProgress(long bytesRead, long contentLength, boolean done);
}
