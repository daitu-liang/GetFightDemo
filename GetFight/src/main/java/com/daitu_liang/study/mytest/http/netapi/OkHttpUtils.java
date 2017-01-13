package com.daitu_liang.study.mytest.http.netapi;

import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.util.AppInfoUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by leixiaoliang on 2017/1/10.
 */
public class OkHttpUtils {
    private static final int DEFAULT_TIMEOUT = 5;
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
//                        .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
//                        .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                          .addInterceptor(new BasicParamsInterceptor.Builder()
                                .addParam("from","3")
                                .addParam("sys_version",android.os.Build.VERSION.RELEASE+"")
                                .addParam("version", AppInfoUtil.getVsersionName(GetFightApplication.CONTEXT))
                                .addParam("nunix", GetFightApplication.getPreferenceManager().getSaveNunix())
                                .build())
                        .addInterceptor(new LoggerInterceptor("HttpMethods",true))
                        .build();
    }

    public static final OkHttpClient getClient() {
        return GetOkHttpInstance.INSTANCE;
    }

}
