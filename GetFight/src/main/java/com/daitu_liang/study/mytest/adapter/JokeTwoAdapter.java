package com.daitu_liang.study.mytest.adapter;

import android.content.Context;
import android.net.Uri;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.entity.TypeListEntity;
import com.daitu_liang.study.mytest.util.Logger;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by leixiaoliang on 2017/3/31.
 * 邮箱：lxliang1101@163.com
 */

public class JokeTwoAdapter extends CommonAdapter<TypeListEntity.DataBean> {

    public JokeTwoAdapter(Context context, int layoutId, List<TypeListEntity.DataBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, TypeListEntity.DataBean dataBean, int position) {

        if(dataBean!=null&&dataBean.getGroup()!=null){
            holder.setText(R.id.joke_title_tv,  dataBean.getGroup().getContent());

           if(dataBean.getGroup().getMiddle_image()!=null&&dataBean.getGroup().getMiddle_image().getUrl_list()!=null&&dataBean.getGroup().getMiddle_image().getUrl_list().get(0).getUrl()!=null){
               Logger.getLogger("JokeTwoAdapter").i("","url="+dataBean.getGroup().getMiddle_image().getUrl_list().get(0).getUrl());
               Uri uri = Uri.parse(dataBean.getGroup().getMiddle_image().getUrl_list().get(0).getUrl());
               SimpleDraweeView draweeView = (SimpleDraweeView) holder.getView(R.id.joke_image_view);
               draweeView.setImageURI(uri);
           }

        }


    }
}