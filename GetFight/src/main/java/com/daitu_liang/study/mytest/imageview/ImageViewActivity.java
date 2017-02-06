package com.daitu_liang.study.mytest.imageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daitu_liang.study.mytest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageViewActivity extends AppCompatActivity {

    @BindView(R.id.test_iv1)
    ImageView testIv1;
    @BindView(R.id.test_iv2)
    ImageView testIv2;

    String url="https://github.com/daitu-liang/GetFightDemo/blob/master/screenshots/test_animation.gif";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);
        Glide.with(this).load("http://t.img.i.hsuperior.com/a92a64a4-7be7-4775-8707-f313144b6136")

                .placeholder(R.mipmap.ic_launcher)

                .into(testIv1);
        Glide.with(this).load(R.drawable.test)
                .asGif()
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(testIv2);


    }


}
