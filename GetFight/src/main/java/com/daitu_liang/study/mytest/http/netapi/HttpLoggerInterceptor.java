package com.daitu_liang.study.mytest.http.netapi;

import com.daitu_liang.study.mytest.app.GetFightApplicationTinker;
import com.daitu_liang.study.mytest.util.AppInfoUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 经常使用拦截器实现以下功能
 * 设置通用Header
 * 设置通用请求参数
 * 拦截响应
 * 统一输出日志
 * 实现缓存
 * Created by leixiaoliang on 2017/1/10.
 */
public class HttpLoggerInterceptor implements Interceptor {
    public String from = "2";//不可空，1、IOS  2、安卓  3、PC
    public String sys_version = android.os.Build.VERSION.RELEASE + "";//
    public String nunix = GetFightApplicationTinker.getPreferenceManager().getSaveNunix();//unix时间戳	不可空
    public String version = AppInfoUtil.getVsersionName(GetFightApplicationTinker.CONTEXT);//APP版本名称

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
      /*  RequestBody requestBody = new FormBody.Builder()
                .add("from", "2")
                .add("sys_version", android.os.Build.VERSION.RELEASE + "")
                .add("nunix", nunix)
                .add("version", version)
                .build();*/
 /*
        设置通用请求参数,公共的请求参数放到请求Header中
         */
      /*  Request.Builder builder = request.newBuilder();
        builder.addHeader("version", version);
        builder.addHeader("nunix", nunix);
        builder.addHeader("from", "2");*/

//        builder.header("from","2");
//使用addHeader()不会覆盖之前设置的header,若使用header()则会覆盖之前的header

        /*
        设置通用请求参数,将其放在请求参数中
         */

       /* HttpUrl httpUrl = request.url().newBuilder()
                .addQueryParameter("paltform", "android")
                .addQueryParameter("sys_version", android.os.Build.VERSION.RELEASE + "")
                .addQueryParameter("version", "1.0.0").build();
        request = request.newBuilder().url(httpUrl).build();*/

        return chain.proceed(request);

    }
}
