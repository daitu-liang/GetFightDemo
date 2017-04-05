package com.daitu_liang.study.mytest.http.netapi;

import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.util.NetWorkUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by leixiaoliang on 2017/1/10.
 */
public class OkHttpUtils {
    private static final int DEFAULT_TIMEOUT = 5;
    private static final int DEFAULT_READ_TIMEOUT = 20;
    private static final int DEFAULT_WRITE_TIMEOUT = 20;
    //创建Cache
    private static Cache cache = new Cache(GetFightApplication.CONTEXT.getCacheDir(), 10 * 1024 * 1024);
    private static class OkHttpHolder {
        private static final OkHttpUtils INSTANCE = new OkHttpUtils();

    }

    public static final OkHttpUtils getInstance() {
        return OkHttpUtils.OkHttpHolder.INSTANCE;
    }


    private static class GetOkHttpInstance {

        private static final OkHttpClient INSTANCE =
                new OkHttpClient.Builder()
                        .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                        .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                        .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
//                        .addInterceptor(new HttpLoggerInterceptor())
                        .addInterceptor(new LoggerInterceptor("HttpMethods",true))
                        .addInterceptor(getHttpLoggingInterceptor())
                        .addNetworkInterceptor(getCacheInterceptor()).cache(cache).addInterceptor(getCacheInterceptor())
                        .build();
    }

    public static final OkHttpClient getClient() {
        return GetOkHttpInstance.INSTANCE;
    }

    /**
     * 日志拦截器
     * @return
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    /**
     *缓存策略应该由服务器指定，但是在有些情况下服务器并不支持缓存策略，这就要求我们客户端自行设置缓存策略。
     * 因此器缓存策略完全由客户端通过重写request和response来实现器缓存策略完全由客户端通过重写request和response来实现
     * @return
     */
    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetWorkUtil.isNetworkConnected(GetFightApplication.CONTEXT)) {
                    //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
                    request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);
                //有网络情况下，根据请求接口的设置，配置缓存。
                if (NetWorkUtil.isNetworkConnected(GetFightApplication.CONTEXT)) {
                    //这样在下次请求时，根据缓存决定是否真正发出请求。
//                    String cacheControl = request.cacheControl().toString();
                    //当然如果你想在有网络的情况下都直接走网络，那么只需要
                    //将其超时时间s设置为0即可:
                     String cacheControl="Cache-Control:public,max-age=0";
                    return response.newBuilder().header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                }else{//无网络
                    return response.newBuilder().header("Cache-Control", "public,only-if-cached,max-stale=360000")
                            .removeHeader("Pragma")
                            .build();
                }

            }
        };
    }
}
