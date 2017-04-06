package com.daitu_liang.study.mytest.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.entity.PieData;
import com.daitu_liang.study.mytest.paint.PathView;
import com.daitu_liang.study.mytest.paint.PieView;

import java.util.ArrayList;

public class PieViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPathView();

//        initPieView();
    }

    private void initPathView() {
        PathView pathView=new PathView(this,null);
        setContentView(pathView);
        pathView.startPath();
    }

    /**
     * 画饼
     */
    private void initPieView() {
        PieView view = new PieView(this);
        setContentView(view);

        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        view.setData(datas);
    }
}
