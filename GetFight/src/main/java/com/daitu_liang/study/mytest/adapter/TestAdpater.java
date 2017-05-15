package com.daitu_liang.study.mytest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.entity.TypeListEntity;

import java.util.List;

/**
 * Created by leixiaoliang on 2017/5/13.
 * 邮箱：lxliang1101@163.com
 */

public class TestAdpater extends RecyclerView.Adapter<TestAdpater.ViewHolder> {


    private List<TypeListEntity> list;

    public TestAdpater(List<TypeListEntity> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke_one, parent);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitileTv.setText("joke");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitileTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitileTv = (TextView) itemView.findViewById(R.id.joke_title_tv);
        }
    }
}
