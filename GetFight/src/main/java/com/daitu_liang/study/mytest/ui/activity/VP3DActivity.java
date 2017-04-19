package com.daitu_liang.study.mytest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.adapter.PageAdapter;
import com.daitu_liang.study.mytest.widget.ZoomLoopScrollViewPager;

public class VP3DActivity extends AppCompatActivity {
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, VP3DActivity.class);
        return intent;
    }
    private ZoomLoopScrollViewPager viewPager;
    private PageAdapter adapter;

    private int img[] = {R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp3_d);
        //初始化ViewPager
        viewPager = (ZoomLoopScrollViewPager) findViewById(R.id.viewPager);
        adapter = new PageAdapter(this, img);
        viewPager.setOffscreenPageLimit(5);
//        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        viewPager.setAdapter(adapter);

    }
}
