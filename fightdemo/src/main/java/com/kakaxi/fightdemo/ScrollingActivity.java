package com.kakaxi.fightdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.R.attr.value;

public class ScrollingActivity extends AppCompatActivity {

    private static final String TAG = "ScrollingActivity";
    private Disposable mDisposable;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        testRxJava2Step8();
    }

    /**
     * Flowable和 Subscriber来处理上下游流速不均衡的问题
     *
     *
     */
    private void testRxJava2Step8() {

        Flowable<Integer> flowable=Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                //emitter.requested()下流处理事件能力大小，没发送一个就减一个
                Log.d(TAG, "emitter.requested()="+emitter.requested());
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emitter.requested()="+emitter.requested());
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
//                emitter.requested();//获取下游处理事件的能力大小
                Log.d(TAG, "emitter.requested()="+emitter.requested());
                Log.d(TAG, "emit complete");
                emitter.onComplete();
            }
            //用来选择背压,也就是出现上下游流速不均衡的时候应该怎么处理的办法,
            // 这里我们直接用BackpressureStrategy.ERROR这种方式,
            // 这种方式会在出现上下游流速不均衡的时候直接抛出一个异常,
            // 这个异常就是著名的MissingBackpressureException.
            //BackpressureStrategy.BUFFER ,BackpressureStrategy.DROP
            //BackpressureStrategy.LATEST
        }, BackpressureStrategy.ERROR);//增加了一个参数


        Subscriber<Integer> subscriber=new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                Log.d(TAG, "onSubscribe");
//                s.request(Long.MAX_VALUE);  //注意这句代码,表述下流接受上流的处理能力大小
                s.request(50);
               // s.request(150);//处理能力是累加效果，告诉上流150
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable t) {
                Log.w(TAG, "onError: ", t);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
        flowable.subscribe(subscriber);
        Log.d(TAG, "----------------------------------");
    }


    /**
     *一是从数量上进行治理, 减少发送进水缸里的事件
     二是从速度上进行治理, 减缓事件发送进水缸的速度
     but 过滤事件会导致事件丢失, 减速又可能导致性能损失
     */
    private void testRxJava2Step7() {
        Log.d(TAG, "----------------------------------");
        Observable<Integer> observable1=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i=0;;i++){
                    emitter.onNext(i);
//                    Thread.sleep(1000);//减速
                }
            }
        }).subscribeOn(Schedulers.io());

        //sample操作符,每隔指定的时间就从上游中取出一个事件发送给下游
        observable1.sample(3,TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.d(TAG, "accept: " + integer);
            }
        });
    }

    /**
     * Zip通过一个函数将多个Observable发送的事件结合到一起，
     * 然后发送这些组合到一起的事件.
     * 它按照严格的顺序应用这个函数。
     * 它只发射与发射数据项最少的那个Observable一样多的数据
     */
    private void testRxJava2Step6() {
        Log.d(TAG, "----------------------------------");
        Observable<Integer> observable1=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Thread.sleep(2000);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Thread.sleep(2000);

                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Thread.sleep(2000);

                Log.d(TAG, "emit 4");
                emitter.onNext(4);
                Thread.sleep(2000);
                emitter.onNext(5);
                Thread.sleep(2000);
               Log.d(TAG, "observable1-emit complete1");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emit A");
                emitter.onNext("A");
                Thread.sleep(2000);

                Log.d(TAG, "emit B");
                emitter.onNext("B");
                Thread.sleep(2000);

//                emitter.onError(new Exception("5555"));

                Log.d(TAG, "emit C");
                emitter.onNext("C");
                Thread.sleep(2000);

                Log.d(TAG, "observable2-emit complete2");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
                return "zip组合="+integer+s;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.d(TAG, "accept: " + s);
            }
        });
    }


    /**
     * concatMap 将一个事件转化为多个事件,进行发送，但是事件顺序为有序
     */
    private void testRxJava2Step5() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(100);
                e.onNext(200);
                e.onNext(300);
                e.onNext(400);
                e.onNext(500);
                e.onNext(600);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {

                List<String> list=new ArrayList<String>();
                for (int i=0;i<4;i++){
                    list.add("concatMap 将一个事件转化为多个有序事件= "+integer);
                }
                return Observable.fromIterable(list).delay(5000, TimeUnit.MICROSECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.d(TAG, "accept="+s);
            }
        });
    }
    /**
     * flatmap 将一个事件转化为多个事件,进行发送，但是事件顺序为无序
     */
    private void testRxJava2Step4() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(100);
                e.onNext(200);
                e.onNext(300);
                e.onNext(400);

            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {

                List<String> list=new ArrayList<String>();
                for (int i=0;i<4;i++){
                    list.add("flatmap 将一个事件转化为多个无序事件= "+integer);
                }
                return Observable.fromIterable(list).delay(5000, TimeUnit.MICROSECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.d(TAG, "accept="+s);
            }
        });
    }

    /**
     * 使用map进行事件转化
     * 就是对上游发送的每一个事件应用一个函数,
     * 使得每一个事件都按照指定的函数去变化.
     */
    private void testRxJava2Step3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(100);
                Log.d(TAG, "emit 100");
                e.onNext(200);
                Log.d(TAG, "emit 200");
                e.onComplete();
                Log.d(TAG, "emit onComplete1");
//                e.onError(new Exception("555"));
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "整形转化为字符串="+integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.d(TAG, s);
            }
        });
    }

    /**
     * Observable和Consumer
     */
    private void testRxJava2Step2() {
        Observable<Integer> observable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(100);
                Log.d(TAG, "emit 100");
                e.onNext(200);
                Log.d(TAG, "emit 200");
                e.onNext(300);
                Log.d(TAG, "emit 300");
                e.onComplete();
                Log.d(TAG, "emit onComplete1");
                e.onNext(400);
                Log.d(TAG, "emit 400");
                e.onComplete();
                Log.d(TAG, "emit onComplete2");
//                e.onError(new Exception("555"));
            }
        });
        Consumer<Integer> consumer =new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                Log.d(TAG, "Consumer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };


        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    /**
     * Observer和Observable
     */
    private void testRxJava2Step1() {
        Observable<Integer> observable=Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(100);
                Log.d(TAG, "emit 100");
                e.onNext(200);
                Log.d(TAG, "emit 200");
                e.onNext(300);
                Log.d(TAG, "emit 300");
                e.onComplete();
                Log.d(TAG, "emit onComplete1");
                e.onNext(400);
                Log.d(TAG, "emit 400");
                e.onComplete();
                Log.d(TAG, "emit onComplete2");
//                e.onError(new Exception("555"));
            }
        });

        Observer observer=new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
                mDisposable = d;
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext: " + value);
                i++;
                if (i == 2) {
                    Log.d(TAG, "dispose");
                    mDisposable.dispose();
                    Log.d(TAG, "isDisposed : " + mDisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
