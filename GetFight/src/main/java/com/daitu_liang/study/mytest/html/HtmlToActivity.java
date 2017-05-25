package com.daitu_liang.study.mytest.html;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.ui.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HtmlToActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.html_btn)
    Button htmlBtn;
    @BindView(R.id.html_btn_bg)
    Button htmlBtnBg;
    @BindView(R.id.toolbar_pull)
    Toolbar toolbarPull;
    private Unbinder bf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_to);
        bf = ButterKnife.bind(this);
        setSupportActionBar(toolbarPull);
        initViewJavaToH5();
        initViewH5ToJava();
    }

    private void initViewJavaToH5() {
        //放在assets的html需加上 android_asset/ ；也可以用网络上的文件
        String url = "file:///android_asset/test.html";
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);
        htmlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String color = "#05f6f3";
                webview.loadUrl("javascript: changeColor('"+color+"');");//调用HTML5页面中的JS方法
            }
        });
        htmlBtnBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               webview.loadUrl("javascript:chcolor()");
            }
        });
    }


    private void initViewH5ToJava() {
        //放在assets的html需加上 android_asset/ ；也可以用网络上的文件
        String url = "file:///android_asset/test.html";
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JSInterface1(),"testHtml");
        webview.loadUrl(url);
    }

    class JSInterface1 {
        //JavaScript调用此方法
        @JavascriptInterface
        public void callAndroidMethod(int a,float b, String c,boolean d){
            if(d){
                String strMessage = "a+b+c="+a+b+c;
                Toast.makeText(HtmlToActivity.this,"html="+strMessage,Toast.LENGTH_LONG).show();
                startActivity(new Intent(HtmlToActivity.this, LoginActivity.class));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.backup:
                Toast.makeText(this,"backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"settings",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bf != null) {
            bf.unbind();
        }
    }
}
