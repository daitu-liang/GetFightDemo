package com.daitu_liang.study.mytest.coverflowview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.daitu_liang.study.mytest.R;

public class CoverFlow3DAdapter extends CoverFlowAdapter {

    private boolean dataChanged;

    public CoverFlow3DAdapter(Context context) {

        image1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.bg);

        image2 = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.ic_launcher);
    }

    public void changeBitmap() {
        dataChanged = true;

        notifyDataSetChanged();
    }

    private Bitmap image1 = null;

    private Bitmap image2 = null;

    @Override
    public int getCount() {
        return dataChanged ? 3 : 8;
    }

    @Override
    public Bitmap getImage(final int position) {
        return (dataChanged && position == 0) ? image2 : image1;
    }
}
