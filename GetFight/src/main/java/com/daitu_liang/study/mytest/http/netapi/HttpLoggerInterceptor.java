package com.daitu_liang.study.mytest.http.netapi;

import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.util.AppInfoUtil;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by leixiaoliang on 2017/1/10.
 */
public class HttpLoggerInterceptor implements Interceptor {
    public String  from="2";//不可空，1、IOS  2、安卓  3、PC
    public String  sys_version=android.os.Build.VERSION.RELEASE+"";//
    public  String nunix= GetFightApplication.getPreferenceManager().getSaveNunix();//unix时间戳	不可空
    public  String version= AppInfoUtil.getVsersionName(GetFightApplication.CONTEXT);//APP版本名称
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        RequestBody requestBody=new FormBody.Builder()
                .add("from","2")
                .add("sys_version",android.os.Build.VERSION.RELEASE+"")
                .add("nunix",nunix)
                .add("version",version)
                .build();
//        String postBodyString = Utils.bodyToString(request.body());
//        postBodyString += ((postBodyString.length() > 0) ? "&" : "") +  Utils.bodyToString(formBody);
//        request = requestBuilder
//                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8"),
//                        postBodyString))
//                .build();
        return chain.proceed(request);

    }
}
