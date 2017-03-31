package com.daitu_liang.study.mytest.adapter;

import android.content.Context;

import com.daitu_liang.study.mytest.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by leixiaoliang on 2017/3/16.
 * 邮箱：lxliang1101@163.com
 */
public class BooksAdapter extends CommonAdapter<String> {
    public BooksAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String info, int position) {
        holder.setText(R.id.text, position + "-" + info);
    }
}
