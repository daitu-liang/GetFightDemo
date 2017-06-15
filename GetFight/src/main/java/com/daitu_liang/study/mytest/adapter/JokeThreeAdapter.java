package com.daitu_liang.study.mytest.adapter;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplicationTinker;
import com.daitu_liang.study.mytest.entity.TypeListEntity;
import com.daitu_liang.study.mytest.util.Logger;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;


/**
 * Created by leixiaoliang on 2017/3/31.
 * 邮箱：lxliang1101@163.com
 */

public class JokeThreeAdapter extends CommonAdapter<TypeListEntity.DataBean> {

    private  int setSize;

    public JokeThreeAdapter(Context context, int layoutId, List<TypeListEntity.DataBean> datas, int setSize) {
        super(context, layoutId, datas);
        this.setSize=setSize;
    }

    @Override
    protected void convert(ViewHolder holder, TypeListEntity.DataBean dataBean, int position) {

        if(dataBean!=null&&dataBean.getGroup()!=null){
            holder.setText(R.id.joke_title_tv,  dataBean.getGroup().getContent());
            if(dataBean.getGroup().get_$480p_video()!=null){
                TypeListEntity.DataBean.GroupBean._$480pVideoBean vedioInfo = dataBean.getGroup().get_$480p_video();
                String url = vedioInfo.getUrl_list().get(1).getUrl();
                Logger.getLogger("JokeThreeAdapter").i("","333url="+url);
                JCVideoPlayerStandard jcVideoPlayerStandard = (JCVideoPlayerStandard) holder.getView(R.id.videoplayer);
                if(setSize==12){
                    jcVideoPlayerStandard.widthRatio=2;
                    jcVideoPlayerStandard.heightRatio=3;
                }

                jcVideoPlayerStandard.setUp(url, JCVideoPlayerStandard.SCREEN_LAYOUT_LIST,  dataBean.getGroup().getTitle());
                String imgeUrlVedio = dataBean.getGroup().getLarge_cover().getUrl_list().get(2).getUrl();
                Logger.getLogger("JokeThreeAdapter").i("","imgeUrlVedio="+imgeUrlVedio);
//                jcVideoPlayerStandard.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
                Glide.with(GetFightApplicationTinker.getContext())
                        .load(imgeUrlVedio)
                        .into(jcVideoPlayerStandard.thumbImageView);
            }

        }

    }
}