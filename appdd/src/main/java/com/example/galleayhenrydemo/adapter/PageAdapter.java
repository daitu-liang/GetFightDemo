package com.example.galleayhenrydemo.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.galleayhenrydemo.R;


/**
 * Created by leo on 16/5/7.
 */
public class PageAdapter extends PagerAdapter {
    private Context context;
    private int img[];

    public PageAdapter(Context context, int img[]) {
        this.context = context;
        this.img = img;
    }

    @Override
    public int getCount() {
        return img.length;
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_movie);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        imageView.setBackgroundResource(img[position]);
        title.setText(position+"");
        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

}
