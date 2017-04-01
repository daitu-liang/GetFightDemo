package com.daitu_liang.study.mytest.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.entity.TypeListEntity;
import com.facebook.drawee.view.SimpleDraweeView;
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

        if(dataBean!=null&&dataBean.getGroup()!=null&& !TextUtils.isEmpty(dataBean.getGroup().getContent())){
            holder.setText(R.id.joke_title_tv,  dataBean.getGroup().getContent());
            if(dataBean.getGroup().getUser()!=null&&dataBean.getGroup().getUser().getAvatar_url()!=null){
                Uri uri = Uri.parse(dataBean.getGroup().getUser().getAvatar_url());
                SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.joke_image_view);
                draweeView.setImageURI(uri);
            }
        }
    }
}