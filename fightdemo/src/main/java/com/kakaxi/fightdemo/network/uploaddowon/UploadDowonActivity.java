package com.kakaxi.fightdemo.network.uploaddowon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kakaxi.fightdemo.R;
import com.kakaxi.fightdemo.network.api.commom.ApiCommom;
import com.kakaxi.fightdemo.utils.Logger;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadDowonActivity extends AppCompatActivity implements DownloadProgressListener{
    private static final String TAG = "UploadDowonActivity";
    private Logger log = Logger.getLogger("UploadDowonActivity");
    private TextView title_tv;
    private ProgressBar mProgressBar;
    private Observable<File> observable;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, UploadDowonActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        title_tv = (TextView) findViewById(R.id.textView2);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        Button Buttont2 = (Button) findViewById(R.id.btn_upload);
        Buttont2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upLoadFile();
            }
        });
        Button btnDown = (Button) findViewById(R.id.btn_down);
        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadFile();
            }
        });

    }



    private void upLoadFile() {
        //之前的请求方法
        ApiCommom service = ServiceGenerator.createService(ApiCommom.class);
        File file = new File("/storage/emulated/0/jewelry/cache/crop.jpg");
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
//        Observable<ResponseBody> observable = service.upLoadFile(body);

    }
    private void downloadFile() {
        String url = "http://i.snssdk.com/neihan/video/playback/?video_id=7bc19ddf04494438ac14dcf140de67ba&quality=360p&line=0";
        String url2="http://t.img.i.hsuperior.com/a38ee054-b941-4eb9-9e83-ba45a2ae13a8";
        ApiCommom downloadService = ServiceGenerator.createDownloadService(ApiCommom.class,this);
        String savePath = getExternalFilesDir(null)+ File.separator+"apple.mp4";
//        observable = downloadService.downloadFile(url,savePath);
        mProgressBar.setVisibility(View.VISIBLE);

     /*   observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(@NonNull File file) throws Exception {
                        Log.e("onResponse","file path:"+file.getPath());
                    }
                });*/
        Call call  = downloadService.downloadFileCall(url,savePath);
        call.enqueue(new Callback<File>() {
            @Override
            public void onResponse(Call<File> call, Response<File> response) {
                if(response.isSuccessful() && response.body() != null){
                   log.e("onResponse","file path:"+response.body().getPath());
                }
//                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<File> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }


    @Override
    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
        log.e(TAG,"onDownloadProgress--bytesRead="+bytesRead+"-------contentLength="+contentLength+"----是否完成下载="+done);
        double readNum = (bytesRead / (double) contentLength) * 100;
        log.e(TAG,"onDownloadProgress--readNum="+readNum);
        int progress = (int) Math.ceil(readNum);
        log.e(TAG,"onDownloadProgress--progress="+progress);
//        title_tv.setText("5555555555%");
        mProgressBar.setProgress(progress);
        log.e(TAG,"onDownloadProgress--thread="+Thread.currentThread().getName());

    }
}
