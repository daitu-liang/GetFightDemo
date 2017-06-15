package com.daitu_liang.study.mytest.javanature;

/*public class HandleTestActivity extends Activity {

    *//**修复方法：在 Activity 中避免使用非静态内部类，
     * 比如上面我们将 Handler 声明为静态的，则其存活期跟 Activity 的生命周期就无关了。
     * 同时通过弱引用的方式引入 Activity，避免直接将 Activity 作为 context 传进去，见下面代码：
     * Instances of static inner classes do not hold an implicit
     * reference to their outer class.
     *//*
    private static class MyHandler extends Handler {
        private final WeakReference<HandleTestActivity> mActivity;

        public MyHandler(HandleTestActivity activity) {
            mActivity = new WeakReference<HandleTestActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandleTestActivity activity = mActivity.get();
            if (activity != null) {
                // ...
            }
        }
    }

    private final MyHandler mHandler = new MyHandler(this);

    */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplicationTinker;
import com.squareup.leakcanary.RefWatcher;

/**
     * Instances of anonymous classes do not hold an implicit
     * reference to their outer class when they are "static".
     *//*
    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() { *//* ... *//* }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        // Post a message and delay its execution for 10 minutes.
        mHandler.postDelayed(sRunnable, 1000 * 60 * 10);

        // Go back to the previous Activity.
        finish();
    }
}*/



public class HandleTestActivity extends AppCompatActivity {

    private final Handler mLeakyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // ...
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        // Post a message and delay its execution for 10 minutes.
        mLeakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //...

 }
        }, 1000 * 60 * 10);

        // Go back to the previous Activity.
        finish();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = GetFightApplicationTinker.getRefWatcher(this);
        refWatcher.watch(this);
    }

}
/**
 * 在该 HandleTestActivity 中声明了一个延迟10分钟执行的消息 Message，mLeakyHandler
 * 将其 push 进了消息队列 MessageQueue 里。当该 Activity 被 finish() 掉时，
 * 延迟执行任务的 Message 还会继续存在于主线程中，它持有该 Activity 的 Handler 引用，
 * 所以此时 finish() 掉的 Activity 就不会被回收了从而造成内存泄漏
 * （因 Handler 为非静态内部类，它会持有外部类的引用，在这里就是指 HandleTestActivity）
 */
