package com.daitu_liang.study.mytest.adapter;

import android.content.Context;

import com.daitu_liang.study.mytest.entity.TypeListEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by leixiaoliang on 2017/3/31.
 * 邮箱：lxliang1101@163.com
 */

public class JokeOneAdapter extends CommonAdapter<TypeListEntity.DataBean> {

    public JokeOneAdapter(Context context, int layoutId, List<TypeListEntity.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TypeListEntity.DataBean dataBean, int position) {

    }
}