package com.daitu_liang.study.mytest.litepal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.entity.Album;
import com.daitu_liang.study.mytest.entity.Song;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LitePalActivity extends AppCompatActivity {
    private static final String TAG = "LitePalActivity";
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, LitePalActivity.class);
        return intent;
    }

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.textView2)
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.button2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                LitePal.getDatabase();
                break;
            case R.id.button2:
                doUsedByLitePal();
                break;
        }
    }

    private void doUsedByLitePal1() {
        Album albumToUpdate = DataSupport.find(Album.class, 1);
        albumToUpdate.setPrice(20.99f); // raise the price
        albumToUpdate.save();


        albumToUpdate.updateAll("name=? and price=?" ,"album","4.2");
         Song song=new Song();
        song.setToDefault("name");
        song.updateAll();

        DataSupport.deleteAll(Song.class,"duration>?","2015");
        List<Album> list=DataSupport.findAll(Album.class);
        for (Album album:list){
            Log.e(TAG, "doUsedByLitePal1: ---"+album.getName());
        }

        DataSupport.findFirst(Song.class);
        DataSupport.findLast(Song.class);
        DataSupport.select("name","price").find(Song.class);
        DataSupport.where("price>?","200").find(Song.class);
        DataSupport.order("price desc").find(Song.class);
        DataSupport.limit(5).find(Song.class);
        DataSupport.limit(6).offset(3).find(Song.class);
    }

    private void doUsedByLitePal() {
        Album album = new Album();
        album.setName("album");
        album.setPrice(10.99f);
        album.save();
        Song song1 = new Song();
        song1.setName("song1");
        song1.setDuration(320);
        song1.setAlbum(album);
        song1.save();
        Song song2 = new Song();
        song2.setName("song2");
        song2.setDuration(356);
        song2.setAlbum(album);
        song2.save();
    }

}
