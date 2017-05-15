package com.example.galleayhenrydemo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.example.galleayhenrydemo.adapter.PageAdapter;


public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private PageAdapter adapter;
    private LoadingDialog loaddingDialog;
    private int img[] = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //获取一个Api实例
        loaddingDialog = new LoadingDialog(this);
        //初始化ViewPager
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new PageAdapter(MainActivity.this, img);
        viewPager.setOffscreenPageLimit(20);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(adapter);
        loaddingDialog.cancel();

    }

}
