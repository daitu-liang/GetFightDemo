package com.daitu_liang.study.mytest.unittest;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.daitu_liang.study.mytest.R;

/**
 * Created by leixiaoliang on 2017/2/24.
 */
public class ActivityTestUnitCase extends ActivityInstrumentationTestCase2<UnitTestActivity> {
    private UnitTestActivity mActivity;
    private EditText et;
    private TextView tv;
    private String addValue;

    public ActivityTestUnitCase() {
        super(UnitTestActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Intent intent=new Intent(getInstrumentation().getTargetContext(),UnitTestActivity.class);
        getInstrumentation().getTargetContext().startActivity(intent);
        mActivity=getActivity();
        initData();
    }

    public void initData() {
         et =(EditText) findViewById(R.id.test_et);

        assertNotNull(et);
         tv =(TextView) findViewById(R.id.test_tv);
        assertNotNull(et);
       
    }

    public void testAddTest() {
       final String etValue = "qq";//et.getText().toString().trim();

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText(etValue+"apple");
                addValue=etValue+"apple";
            }
        });
        getInstrumentation().waitForIdleSync();//等待UI线程空闲后再继续执行
        assertEquals("qqapple",addValue);
    }
    private <T extends View> T findViewById(int id){
        return (T) mActivity.findViewById(id);
    }
}
