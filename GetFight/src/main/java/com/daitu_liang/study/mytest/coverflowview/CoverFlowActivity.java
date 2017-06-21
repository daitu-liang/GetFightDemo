package com.daitu_liang.study.mytest.coverflowview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.daitu_liang.study.mytest.R;


public class CoverFlowActivity extends Activity {
    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, CoverFlowActivity.class);
        return intent;
    }
    protected static final String VIEW_LOG_TAG = "CoverFlowDemo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = LayoutInflater.from(this).inflate(R.layout.activity_cover_flow,
                null, false);
        setContentView(v);

        final CoverFlowView<CoverFlow3DAdapter> mCoverFlowView = (CoverFlowView<CoverFlow3DAdapter>) findViewById(R.id.coverflow);

        final CoverFlow3DAdapter adapter = new CoverFlow3DAdapter(this);
        mCoverFlowView.setAdapter(adapter);
        mCoverFlowView
                .setCoverFlowListener(new CoverFlowView.CoverFlowListener<CoverFlow3DAdapter>() {

                    @Override
                    public void imageOnTop(
                            CoverFlowView<CoverFlow3DAdapter> view,
                            int position, float left, float top, float right,
                            float bottom) {
                        Log.e(VIEW_LOG_TAG, position + " on top!");
                    }

                    @Override
                    public void topImageClicked(
                            CoverFlowView<CoverFlow3DAdapter> view, int position) {
                        Log.e(VIEW_LOG_TAG, position + " clicked!");
                    }

                    @Override
                    public void invalidationCompleted() {

                    }
                });

        mCoverFlowView
                .setTopImageLongClickListener(new CoverFlowView.TopImageLongClickListener() {

                    @Override
                    public void onLongClick(int position) {
                        Log.e(VIEW_LOG_TAG, "top image long clicked == >"
                                + position);
                    }
                });

        findViewById(R.id.change_bitmap_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeBitmap();
            }
        });
    }
}
