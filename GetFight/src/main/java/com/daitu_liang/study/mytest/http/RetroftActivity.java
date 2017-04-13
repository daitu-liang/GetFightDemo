package com.daitu_liang.study.mytest.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daitu_liang.study.mytest.R;
import com.daitu_liang.study.mytest.app.GetFightApplication;
import com.daitu_liang.study.mytest.entity.MessageEvent;
import com.daitu_liang.study.mytest.entity.MovieEntity;
import com.daitu_liang.study.mytest.entity.NiuxInfo;
import com.daitu_liang.study.mytest.entity.Subject;
import com.daitu_liang.study.mytest.http.netapi.ApiClientService;
import com.daitu_liang.study.mytest.http.netapi.HttpMethods;
import com.daitu_liang.study.mytest.http.netapi.HttpResultTest;
import com.daitu_liang.study.mytest.http.netapi.ProgressSubscriber;
import com.daitu_liang.study.mytest.http.netapi.SubscriberOnNextListener;
import com.daitu_liang.study.mytest.util.Logger;
import com.daitu_liang.study.mytest.util.PreferencesManager;
import com.daitu_liang.study.mytest.util.otto.BusProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;




public class RetroftActivity extends AppCompatActivity {
    private Logger log = Logger.getLogger("RetroftActivity");

    private static final String TAG = "RetroftActivity";
    @BindView(R.id.click_me_BN)
    Button clickMeBN;
    @BindView(R.id.result_TV)
    TextView resultTV;
    private String baseUrl = "https://api.douban.com/v2/movie/";
    private Subscriber<MovieEntity> subscriber;
    private Subscriber<HttpResultTest<List<Subject>>> subscriber1;
    private Subscriber<List<Subject>> subscriber2;
    private SubscriberOnNextListener<List<Subject>> getMovieOnNext;
    private ProgressSubscriber<NiuxInfo> sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retroft);
        ButterKnife.bind(this);
        BusProvider.getInstance().register(this);   //订阅事件
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {

        getData();
    }

    private void getData() {
        SubscriberOnNextListener<NiuxInfo> getSubscriber = new SubscriberOnNextListener<NiuxInfo>() {
            @Override
            public void onNext(NiuxInfo s) {
                resultTV.setText(s.getNunix());
                PreferencesManager pre = GetFightApplication.getPreferenceManager();
                pre.setSaveNunix(s.getNunix());
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setMsg(s.getNunix());
                BusProvider.getInstance().post(messageEvent);
                finish();

            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
                BusProvider.getInstance().post(e.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        };
        sub=new ProgressSubscriber<NiuxInfo>(getSubscriber,this);
        HttpMethods.getInstance().getNunix(sub,"https://webapi.hsuperior.com/sys/getnunix");
    }


    private void getDat1a() {
        getMovieOnNext=new SubscriberOnNextListener<List<Subject>>(){

            @Override
            public void onNext(List<Subject> subjects) {
                List<Subject> infoList = subjects;
                String mtitle = "";
                for (int i = 0; i < infoList.size(); i++) {
                    mtitle = mtitle + "\n" + infoList.get(i).getTitle() + "----封prossbar装---" + infoList.get(i).getYear();
                }
                resultTV.setText(mtitle);
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onCompleted() {

            }
        };
        HttpMethods.getInstance().getMovieData3(new ProgressSubscriber<List<Subject>>(getMovieOnNext,RetroftActivity.this),0,15);
    }
    private void getData5() {
        subscriber2 = new Subscriber<List<Subject>>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i(TAG, "onStart");
                Toast.makeText(RetroftActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
                Toast.makeText(RetroftActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(List<Subject> listHttpResult) {
                List<Subject> infoList = listHttpResult;
                String mtitle = "";
                for (int i = 0; i < infoList.size(); i++) {
                    mtitle = mtitle + "\n" + infoList.get(i).getTitle() + "----封Func1装---" + infoList.get(i).getYear();
                }
                resultTV.setText(mtitle);
            }
        };
        HttpMethods.getInstance().getMovieData2(subscriber2, 1, 2);
    }

    private void getData4() {
        subscriber1 = new Subscriber<HttpResultTest<List<Subject>>>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i(TAG, "onStart");
                Toast.makeText(RetroftActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
                Toast.makeText(RetroftActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(HttpResultTest<List<Subject>> listHttpResult) {
                List<Subject> infoList = listHttpResult.getSubjects();
                String mtitle = "";
                for (int i = 0; i < infoList.size(); i++) {
                    mtitle = mtitle + "\n" + infoList.get(i).getTitle() + "----封装---" + infoList.get(i).getYear();
                }
                resultTV.setText(mtitle);
            }
        };
        HttpMethods.getInstance().getMovieData1(subscriber1, 1, 50);
    }
    private void getData3() {
        subscriber = new Subscriber<MovieEntity>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i(TAG, "onStart");
                Toast.makeText(RetroftActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
                Toast.makeText(RetroftActivity.this, "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                Log.i(TAG, "onNext");
                List<MovieEntity.SubjectsBean> infoList = movieEntity.getSubjects();
                String mtitle = "";
                for (int i = 0; i < infoList.size(); i++) {
                    mtitle = mtitle + "\n" + infoList.get(i).getTitle() + "--" + infoList.get(i).getYear();
                }
                resultTV.setText(mtitle);

            }
        };
        HttpMethods.getInstance().getMovieData(subscriber, 1, 50);
    }


    private void getData2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiClientService clientService = retrofit.create(ApiClientService.class);
        Observable<MovieEntity> observable = clientService.getgetTopMoviceData(0, 15);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RetroftActivity.this, "Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        resultTV.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        List<MovieEntity.SubjectsBean> infoList = movieEntity.getSubjects();
                        String mtitle = "";
                        for (int i = 0; i < infoList.size(); i++) {
                            mtitle = mtitle + "\n" + infoList.get(i).getTitle() + "--" + infoList.get(i).getYear();
                        }
                        resultTV.setText(mtitle);

                    }
                });

    }


    private void getData1() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiClientService apiClient = retrofit.create(ApiClientService.class);
        Call<MovieEntity> call = apiClient.getMoviceData(0, 10);
        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                List<MovieEntity.SubjectsBean> infoList = response.body().getSubjects();
                String mtitle = "";
                for (int i = 0; i < infoList.size(); i++) {
                    mtitle = mtitle + "\n" + infoList.get(i).getTitle();
                }
                resultTV.setText(mtitle);
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                resultTV.setText(t.getMessage());
            }
        });
        Toast.makeText(this, "sss", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.e("","onDestroy-1CanlSubscriberror="+ sub.isUnsubscribed());
        sub.onCancelProgress();
        log.e("","onDestroy-2CanlSubscriberror="+ sub.isUnsubscribed());
    }
}
