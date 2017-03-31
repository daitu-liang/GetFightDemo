package com.daitu_liang.study.mytest.http.Jokenet;

import com.daitu_liang.study.mytest.entity.ContentTypeEntity;
import com.daitu_liang.study.mytest.entity.TypeListEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Created by leixiaoliang on 2017/3/31.
 * 邮箱：lxliang1101@163.com
 */
public interface ApiClientJokeService {
    /**
     * 获取content type
     * @param url
     * @return
     */
    @GET
    Observable<HttpJokeResult<List<ContentTypeEntity>>> getJokeContentTypeUrl(@Url String url);

    /**
     * 获取content type对应列表内容

     * @return
     */
    @GET
    Observable<HttpJokeResult<TypeListEntity>> getJokeTypeListEntityUrl(@Url String url);
}
