package com.daitu_liang.study.mytest.http.netapi;

import com.daitu_liang.study.mytest.entity.LoginEntity;
import com.daitu_liang.study.mytest.entity.MovieEntity;
import com.daitu_liang.study.mytest.entity.NiuxInfo;
import com.daitu_liang.study.mytest.entity.Subject;

import java.util.HashMap;
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
 * Created by leixiaoliang on 2017/1/5.
 */
public class HttpMethods {
    public static final String BASE_URL1 = "https://api.douban.com/v2/movie/";
    public static final String BASE_URL = "https://webapi.hsuperior.com";

    private final ApiClientService mService;
    private final Retrofit retrofit;
    private HttpMethods() {
        retrofit = new Retrofit.Builder()
                .client(OkHttpUtils.getInstance().getClient())
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

/*    //（双重校验锁）创建单列
    private static  HttpMethods getSingletonHolderInstance(){
        if(instance==null){
            synchronized (HttpMethods.class){
                if(instance==null){
                    instance=new HttpMethods();
                }
            }
        }
        return instance;
    }*/


    /**
     * RxJava 提供了对事件序列进行变换的支持,
     * 在这里把HttpResult<T>类型处理，返回T类型
     * @param <T>
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> tHttpResult) {
            if (tHttpResult.getErrcode()!=200) {
                throw new ApiException(tHttpResult.getErrcode(),tHttpResult.getErrmsg());
            }
            return tHttpResult.getResult();
        }
    }


    public void getCode(Subscriber<String> subscriber,HashMap<String,String> map){
       mService.getCode(map)
                .map(new HttpResultFunc<String>())//事件转化，处理为data类型返回给subscriber
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
                .subscribe(subscriber);
    }

    public void getUserInfo(Subscriber<LoginEntity>subscriber, HashMap<String,String> map){
        Observable<LoginEntity> observable = mService.getUserInfo(map)
                .map(new HttpResultFunc<LoginEntity>());;
        toSubscribe(observable,subscriber);

    }
    public void getNunix(Subscriber<NiuxInfo> subscriber, String url){
        mService.getNunix(url)
                .map(new HttpResultFunc<NiuxInfo>())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
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

    private void toSubscribe(Observable  observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }


}
