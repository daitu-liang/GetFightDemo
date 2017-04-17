package com.daitu_liang.study.mytest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.util.ScreenUtils;
import com.daitu_liang.study.mytest.widget.SlidingButtonView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


/**
 * Created by leixiaoliang on 2017/3/16.
 * 邮箱：lxliang1101@163.com
 */
public class BooksAdapter extends CommonAdapter<String> implements SlidingButtonView.IonSlidingButtonListener{
    private Context mContext;

    private IonSlidingItemViewClickListener mIDeleteBtnClickListener;
    private SlidingButtonView mMenu;

    public BooksAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        mContext = context;

//        mIDeleteBtnClickListener = (IonSlidingViewClickListener) context;
    }

    public void  setIonSlidingViewClickListener(IonSlidingItemViewClickListener mIDeleteBtnClickListener){
        this.mIDeleteBtnClickListener=mIDeleteBtnClickListener;
    }



    @Override
    protected void convert(final ViewHolder holder, String info, int position) {

        TextView btn_Delete = (TextView) holder.getView(R.id.tv_delete);
        TextView textView = (TextView) holder.getView(R.id.text_tv);
        ViewGroup layout_content = (ViewGroup) holder.getView(R.id.layout_content);
        ((SlidingButtonView) holder.getView(R.id.sliding_view)).setSlidingButtonListener(BooksAdapter.this);
       textView.setText(info);
        //设置内容布局的宽为屏幕宽度
       layout_content.getLayoutParams().width = ScreenUtils.getScreenWidth(mContext);

       textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断是否有删除菜单打开
                if (menuIsOpen()) {
                    closeMenu();//关闭菜单
                } else {
                    int n = holder.getLayoutPosition();
                    mIDeleteBtnClickListener.onItemClick(v, n);
                }

            }
        });
        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = holder.getLayoutPosition();
                mIDeleteBtnClickListener.onDeleteBtnCilck(v, n);
            }
        });
    }
    public void addData(int position) {
        mDatas.add(position, "添加项");
        notifyItemInserted(position);
    }

    public void removeData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * 删除菜单打开信息接收
     */
    @Override
    public void onMenuIsOpen(View view) {
        mMenu = (SlidingButtonView) view;
    }

    /**
     * 滑动或者点击了Item监听
     * @param slidingButtonView
     */
    @Override
    public void onDownOrMove(SlidingButtonView slidingButtonView) {
        if(menuIsOpen()){
            if(mMenu != slidingButtonView){
                closeMenu();
            }
        }
    }
    /**
     * 关闭菜单
     */
    public void closeMenu() {
        mMenu.closeMenu();
        mMenu = null;

    }
    /**
     * 判断是否有菜单打开
     */
    public Boolean menuIsOpen() {
        if(mMenu != null){
            return true;
        }
        Log.i("asd","mMenu为null");
        return false;
    }


    public interface IonSlidingItemViewClickListener {
        void onItemClick(View view,int position);
        void onDeleteBtnCilck(View view,int position);
    }
}
