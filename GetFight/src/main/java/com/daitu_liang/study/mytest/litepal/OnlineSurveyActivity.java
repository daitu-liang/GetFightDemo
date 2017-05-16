package com.daitu_liang.study.mytest.litepal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;

import static android.R.attr.type;

/**
 * Created by zhaocd on 2017/5/15.
 * describe： 在线调查
 */

public class OnlineSurveyActivity extends AppCompatActivity {


    private String mLoginName;
    private String mUrl;
    private String mType;
    private WebView myWebView;
    private Context mContent;

    private String TAG = "OnlineSurveyActivity";

    /**
     *
     * @param mContent
     * @param loginName  登陆的用户名
     * @param url   网页url
     * @param type  类型
     * @return
     */
   public static Intent getInent(Context mContent){
       Intent intent = new Intent(mContent, OnlineSurveyActivity.class);

       return intent;
   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mContent = this;
        Log.e(TAG, "onCreate: " );
        initView();
        initData();
    }
    private void initData() {
//        if (CommonUtil.isNetworkConnected(mContent)) {
            myWebView.loadUrl(mUrl == null ? "" : mUrl);
//        }
    }

    private void initView() {

        mLoginName = getIntent().getStringExtra("loginName");
        mUrl ="http://www.lz13.cn/shiju/40523.html";
        mType = getIntent().getStringExtra("type");
        myWebView = (WebView) findViewById(R.id.webView);
        TextView tv = (TextView) findViewById(R.id.toolbar_text);


        WebSettings setting = myWebView.getSettings();
        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setting.setBuiltInZoomControls(true);// 隐藏缩放按钮
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        setting.setUseWideViewPort(true);// 可任意比例缩放
        setting.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        //setting.setSavePassword(true);
        //setting.setSaveFormData(true);// 保存表单数据
        setting.setJavaScriptEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setGeolocationEnabled(true);// 启用地理定位
        setting.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");// 设置定位的数据库路径
        setting.setDomStorageEnabled(true);
        setting.setSupportMultipleWindows(true);// 新加

        // 设置Web视图
        myWebView.setWebViewClient(new CustomWebViewClient());
        //webview.setDownloadListener(new MyWebViewDownLoadListener());
        myWebView.setHorizontalScrollBarEnabled(false);// 水平不显示
        myWebView.setVerticalScrollBarEnabled(false); // 垂直不显示
        myWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);// 滚动条在WebView内侧显示
        myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);// 滚动条在WebView外侧显示
    }

    private final class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            myWebView.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //mDefView.setStatus(DefaultView.Status.showData);
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(myWebView!=null){
            myWebView.loadData("<a></a>", "text/html", "utf-8");
            myWebView.clearCache(true);
            myWebView.removeAllViews();
            myWebView.destroy();
            myWebView = null;
        }
        if (myWebView != null) {
            myWebView.loadData("<a></a>", "text/html", "utf-8");
            myWebView.clearCache(true);
            myWebView.removeAllViews();
            myWebView.destroy();
            myWebView = null;
        }
    }
}
