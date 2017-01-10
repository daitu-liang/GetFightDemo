package com.daitu_liang.study.mytest.http.netapi;

import com.daitu_liang.study.mytest.modle.MovieEntity;
import com.daitu_liang.study.mytest.modle.Subject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by leixiaoliang on 2017/1/5.
 */
public interface ApiClientService {

    @GET("top250")
    Call<MovieEntity> getMoviceData(@Query("start") int start,@Query("count") int count);

    @GET("top250")
    Observable<MovieEntity> getgetTopMoviceData(@Query("start") int start,@Query("count") int count);

    @GET("top250")
    Observable<HttpResultTest<List<Subject>>> getgetTopMoviceData1(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getgetTopMoviceData2(@Query("start") int start, @Query("count") int count);
}
