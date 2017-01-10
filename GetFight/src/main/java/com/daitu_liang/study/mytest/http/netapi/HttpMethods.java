package com.daitu_liang.study.mytest.http.netapi;

import com.daitu_liang.study.mytest.modle.MovieEntity;
import com.daitu_liang.study.mytest.modle.Subject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by leixiaoliang on 2017/1/5.
 */
public class HttpMethods {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";
    private static final int DEFAULT_TIMEOUT = 5;
    private final ApiClientService mService;
    private final Retrofit retrofit;

    private HttpMethods() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new LoggerInterceptor("HttpMethods",true));


        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mService = retrofit.create(ApiClientService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods instance = new HttpMethods();
    }

    //获取单列
    public static HttpMethods getInstance() {
        return SingletonHolder.instance;
    }

    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getCount() ==0) {
                throw new ApiException(101);
            }
            return tHttpResult.getSubjects();
        }
    }


    /**
     * @param movieEntitySubscriber
     * @param start
     * @param count
     */
    public void getMovieData(Subscriber<MovieEntity> movieEntitySubscriber, int start, int count) {
        mService.getgetTopMoviceData(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieEntitySubscriber);
    }

    //相同格式的Http请求数据该如何封装
    public void getMovieData1(Subscriber<HttpResultTest<List<Subject>>> movieEntitySubscriber, int start, int count) {
        mService.getgetTopMoviceData1(start, count)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieEntitySubscriber);
    }

    //相同格式的Http请求数据统一进行预处理
    public void getMovieData2(Subscriber<List<Subject>> movieEntitySubscriber, int start, int count) {
        mService.getgetTopMoviceData2(start, count)
                .map(new HttpResultFunc<List<Subject>>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieEntitySubscriber);
    }
    public void getMovieData3(Subscriber<List<Subject>> movieEntitySubscriber, int start, int count) {
        Observable<List<Subject>> observable = mService.getgetTopMoviceData2(start, count)
                .map(new HttpResultFunc<List<Subject>>());
        toSubscribe(observable, movieEntitySubscriber);
    }

    private void toSubscribe(Observable  observable, Subscriber<List<Subject>> subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}
