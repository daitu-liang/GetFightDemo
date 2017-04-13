package com.kakaxi.fightdemo.network;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kakaxi.fightdemo.BaseActivity;
import com.kakaxi.fightdemo.R;
import com.kakaxi.fightdemo.bean.LoginBean;
import com.kakaxi.fightdemo.network.api.home.ApiService;
import com.kakaxi.fightdemo.network.config.HttpConfig;
import com.kakaxi.fightdemo.utils.Logger;
import com.kakaxi.fightdemo.utils.MD5Utils;
import com.kakaxi.fightdemo.utils.UserUtil;

import java.util.Map;

public class TestHttpActivity extends BaseActivity {
    private static final String TAG = "TestHttpActivity";
    private Logger log = Logger.getLogger("TestHttpActivity");
    private TextView title_tv;


    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, TestHttpActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_http);
        title_tv = (TextView) findViewById(R.id.textView2);
        getData();
    }

    private void getData() {
        String phone = "18593276078";
        String md5_passworld = MD5Utils.MD5("666666").toLowerCase();
        Map<String, String> map = ParamsMapUtils.setLoginParams(phone, md5_passworld, 1920 + "*" + 1080, "11111111111", "leshi1011", "12.10.0.01");
        RetrofitClient.getInstance()
                .builder(ApiService.class)
                .getUserInfo(map)
                .compose(HttpConfig.<LoginBean>toTransformer())
                .subscribe(new ApiSubscriber<LoginBean>(this) {
                    @Override
                    protected void onSuccess(LoginBean bean) {
                        Log.d(TAG, " onSuccess : " + bean.toString());
                        title_tv.setText(bean.toString());
                        UserUtil.dealLoginResponse(bean);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
