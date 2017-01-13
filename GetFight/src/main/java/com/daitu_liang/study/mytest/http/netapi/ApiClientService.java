package com.daitu_liang.study.mytest.http.netapi;

import com.daitu_liang.study.mytest.modle.LoginResponse;
import com.daitu_liang.study.mytest.modle.MovieEntity;
import com.daitu_liang.study.mytest.modle.NiuxInfo;
import com.daitu_liang.study.mytest.modle.Subject;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;
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


    @GET
    Observable<HttpResult<NiuxInfo>> getNunix(@Url String url);
//    https://webapi.hsuperior.com/sys/getnunix

    @FormUrlEncoded
    @POST("user/login")
    Observable<HttpResult<LoginResponse>> getUserInfo(@FieldMap() Map<String,String>map);

    @FormUrlEncoded
    @POST("/user/vcodeget")
    Observable<HttpResult<String>> getCode(@FieldMap() Map<String,String>map);
}
