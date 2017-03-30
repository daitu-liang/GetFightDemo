package com.daitu_liang.study.mytest.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.http.netapi.HttpMethods;
import com.daitu_liang.study.mytest.http.netapi.ProgressSubscriber;
import com.daitu_liang.study.mytest.http.netapi.SubscriberOnNextListener;
import com.daitu_liang.study.mytest.entity.LoginEntity;
import com.daitu_liang.study.mytest.entity.MessageEvent;
import com.daitu_liang.study.mytest.entity.NiuxInfo;
import com.daitu_liang.study.mytest.util.AppInfoUtil;
import com.daitu_liang.study.mytest.util.GetSign;
import com.daitu_liang.study.mytest.util.Logger;
import com.daitu_liang.study.mytest.util.MD5Utils;
import com.daitu_liang.study.mytest.util.otto.BusProvider;
import com.squareup.otto.Produce;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HttpTestActivity extends AppCompatActivity {

    @BindView(R.id.click_me_BN)
    Button clickMeBN;
    @BindView(R.id.result_TV)
    TextView resultTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getData2();
    }

    private void getData() {

        String phone = "18200399223";
        DisplayMetrics dm = GetFightApplication.CONTEXT.getResources()
                .getDisplayMetrics();

        String from = "2";//不可空，1、IOS  2、安卓  3、PC
        String sys_version = android.os.Build.VERSION.RELEASE + "";//
        String nunix = GetFightApplication.getPreferenceManager().getSaveNunix();//unix时间戳	不可空
        String version = AppInfoUtil.getVsersionName(GetFightApplication.CONTEXT);//APP版本名称
        HashMap<String, String> map = new HashMap<>();
        map.put("from", "2");
        map.put("version", version);
        map.put("nunix", nunix);
        Logger.getLogger("").e("", "nunix-huoqu时间戳-Times=" + nunix);
        map.put("mobile", phone);
        map.put("sms_mode", "register");
        map.put("sign", GetSign.giveSign(map));

        SubscriberOnNextListener<String> getCode = new SubscriberOnNextListener<String>() {
            @Override
            public void onNext(String s) {
            }
            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }
            @Override
            public void onCompleted() {
            }
        };
        HttpMethods.getInstance().getCode(new ProgressSubscriber<String>(getCode, this), map);

    }

    private void getData2() {
        String phone = "18200399223";
        String md5_passworld = MD5Utils.MD5("666666").toLowerCase();
        DisplayMetrics dm = GetFightApplication.CONTEXT.getResources()
                .getDisplayMetrics();
        String from = "2";//不可空，1、IOS  2、安卓  3、PC
        String sys_version = android.os.Build.VERSION.RELEASE + "";//
        String nunix = GetFightApplication.getPreferenceManager().getSaveNunix();//unix时间戳	不可空
        String version = AppInfoUtil.getVsersionName(GetFightApplication.CONTEXT);//APP版本名称
        Logger.getLogger("").e("", "nunix-huoqu时间戳-Times=" + nunix);
        HashMap<String, String> map = new HashMap<>();
        map.put("dev_name", AppInfoUtil.getAppDeviceName());
        map.put("dev_num", AppInfoUtil.getAppDeviceId(this));
        map.put("ip", AppInfoUtil.getPhoneIp());
        map.put("mobile", phone);
        map.put("password", md5_passworld);

        map.put("screen_size", dm.heightPixels + "*" + dm.widthPixels);
        map.put("from", "2");
        map.put("nunix", nunix);
        map.put("sys_version", sys_version);
        map.put("version", version);
        map.put("sign", GetSign.giveSign(map));
        SubscriberOnNextListener<LoginEntity> getLoginSubscrible = new SubscriberOnNextListener<LoginEntity>() {
            @Override
            public void onNext(LoginEntity loginResponse) {
                resultTV.setText(loginResponse.getMobile() + "-" + loginResponse.getNick_name() + "--" + loginResponse.getU_guid());
                BusProvider.getInstance().post(sendContent(loginResponse.getMobile() + "-" + loginResponse.getNick_name()));
            }

            @Override
            public void onError(Throwable e) {
//                MessageEvent messageEvent=  new MessageEvent();
//                messageEvent.setMsg(e.getMessage());
//                BusProvider.getInstance().post(messageEvent);
                BusProvider.getInstance().post(sendContent(e.getMessage()));
                resultTV.setText(e.getMessage());
                finish();
            }
            @Override
            public void onCompleted() {
            }
        };
        HttpMethods.getInstance().getUserInfo(new ProgressSubscriber<LoginEntity>(getLoginSubscrible, this), map);
    }

    @Produce
    public MessageEvent sendContent(String s) {
        MessageEvent messageEvent = new MessageEvent();
        messageEvent.setMsg(s);
        return messageEvent;
    }


    private void getData1() {
        SubscriberOnNextListener<NiuxInfo> getSubscriber = new SubscriberOnNextListener<NiuxInfo>() {
            @Override
            public void onNext(NiuxInfo s) {
                resultTV.setText(s.getNunix());
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        };
        HttpMethods.getInstance().getNunix(new ProgressSubscriber<NiuxInfo>(getSubscriber, this), "https://webapi.hsuperior.com/sys/getnunix");
    }





}
