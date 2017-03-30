package com.daitu_liang.study.mytest.ui;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.daitu_liang.study.mytest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MatrixActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    private AlertDialog alertDialog;
    private boolean showDialog = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        ButterKnife.bind(this);
        initDialog();
    }

    @OnClick({R.id.btn1, R.id.btn2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                showDialog();
                break;
            case R.id.btn2:
                hideDialog();
                break;
        }
    }

    private void showDialog() {
        if (!showDialog) {
            alertDialog.show();
        }
    }

    private void hideDialog() {
        if (!showDialog) {
            alertDialog.dismiss();
        }
    }

    private void initDialog() {
        alertDialog = new AlertDialog.Builder(this).create();
        View view = View.inflate(this, R.layout.loading_dialog, null);
        alertDialog.setView(view);
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width =500;
        attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        alertDialog.setCancelable(false);

    }


}
