package com.daitu_liang.study.mytest.http.Jokenet;

import com.daitu_liang.study.mytest.entity.ContentTypeEntity;
import com.daitu_liang.study.mytest.entity.TypeListEntity;
import com.daitu_liang.study.mytest.http.netapi.ApiException;
import com.daitu_liang.study.mytest.http.netapi.OkHttpUtils;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by leixiaoliang on 2017/3/31.
 * 邮箱：lxliang1101@163.com
 */
public class HttpJokeMethods {
    public static final String BASE_URL = "http://lf.snssdk.com/neihan/service/tabs/";

    private final ApiClientJokeService mService;
    private final Retrofit retrofit;

    private HttpJokeMethods() {
        retrofit = new Retrofit.Builder()
                .client(OkHttpUtils.getInstance().getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mService = retrofit.create(ApiClientJokeService.class);

    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpJokeMethods instance = new HttpJokeMethods();
    }

    //获取单列
    public static HttpJokeMethods getInstance() {
        return SingletonHolder.instance;
    }



    /**
     * RxJava 提供了对事件序列进行变换的支持,
     * 在这里把HttpResult<T>类型处理，返回T类型
     *
     * @param <T>
     */
    private class HttpResultFunc<T> implements Func1<HttpJokeResult<T>, T> {
        @Override
        public T call(HttpJokeResult<T> tHttpResult) {
            if (!"success".equals(tHttpResult.getMessage())) {
                throw new ApiException(tHttpResult.getMessage());
            }
            return tHttpResult.getData();
        }
    }
    public void getJokeContentType(Subscriber<List<ContentTypeEntity>> subscriber, String url) {
        Observable<List<ContentTypeEntity>> observable =  mService.getJokeContentTypeUrl(url)
                .map(new HttpResultFunc<List<ContentTypeEntity>>());
        toSubscribe(observable, subscriber);
    }
    public void getJokeTypeListEntity(Subscriber<TypeListEntity> subscriber, String url) {
        Observable<TypeListEntity> observable =  mService.getJokeTypeListEntityUrl(url)
                .map(new HttpResultFunc<TypeListEntity>());
        toSubscribe(observable, subscriber);
    }

    private void toSubscribe(Observable observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}
