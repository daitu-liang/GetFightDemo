/*
 * Copyright (C) 2017 Baidu, Inc. All Rights Reserved.
 */
package com.daitu_liang.study.mytest.baidu;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.util.Logger;
import com.daitu_liang.study.mytest.util.PreferencesManager;

public class BaiduMainActivity extends AppCompatActivity {
    private Logger log = Logger.getLogger("BaiduMainActivity");
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, BaiduMainActivity.class);
        return intent;
    }
    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_main);
        alertDialog = new AlertDialog.Builder(this);

        findViewById(R.id.general_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaiduMainActivity.this, GeneralActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.idcard_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaiduMainActivity.this, IDCardActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bankcard_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaiduMainActivity.this, BankCardActivity.class);
                startActivity(intent);
            }
        });

        // 请选择您的初始化方式
         initAccessToken();
//        initAccessTokenWithAkSk();
//         OCR.getInstance().initWithToken(getApplicationContext(), "您获取的oauth access_token");
    }

    private void initAccessToken() {

        OCR.getInstance().initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                log.i("baidu","token="+token);
                PreferencesManager pre = GetFightApplication.getPreferenceManager();
               pre.setBaiduToken(token);
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                log.i("baidu","onError="+error.getMessage());
                alertText("licence方式获取token失败", error.getMessage());
            }
        }, getApplicationContext());
    }

    /**
     * 明文AK/SK方式
     */
    private void initAccessTokenWithAkSk() {
        OCR.getInstance().initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                String token = result.getAccessToken();
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
                alertText("AK，SK方式获取token失败", error.getMessage());
            }
        }, getApplicationContext(), "286zAgTCKmhtMzLIpi4PhERF", "qoXUaUfeOep72tqWNbml77bR5s6WklIF");
    }

    private void alertText(String title, String message) {
        boolean isNeedLoop = false;
        if (Looper.myLooper() == null) {
            Looper.prepare();
            isNeedLoop = true;
        }
        alertDialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", null)
                .show();
        if (isNeedLoop) {
            Looper.loop();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]  permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initAccessToken();
        } else {
            Toast.makeText(getApplicationContext(), "需要android.permission.READ_PHONE_STATE", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放内存资源
        OCR.getInstance().release();
    }
}
