package com.daitu_liang.study.mytest.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.entity.PieData;
import com.daitu_liang.study.mytest.util.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VauleActivity extends AppCompatActivity {
    private Logger log = Logger.getLogger("VauleActivity");
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.et)
    EditText et;
    private PieData pieData;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, VauleActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaule);
        ButterKnife.bind(this);
        pieData = new PieData("kakaxi", 6666);
        textView.setText(pieData.getName() + pieData.getValue());

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                log.i("","onTextChanged合格="+s.toString());
                String text=s.toString();
                Pattern pChar= Pattern.compile("[a-zA-Z]");
                Matcher m1=pChar.matcher(text);

                Pattern   pChina=Pattern.compile("[\u4e00-\u9fa5]");
                Matcher   m2=pChina.matcher(text);
                if(m2.matches()&&m1.matches()){

                    log.i("","合格="+s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        testVaule(pieData);
    }

    private void testVaule(PieData pieData) {
        PieData pieData1 = pieData;
        pieData1.setName("ppppp-1");
        pieData1.setValue(8888);
        textView.setText(pieData1.getName() + pieData.getValue());

    }


}
