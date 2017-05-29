package com.kakaxi.fightdemo.ui.acitivity;

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
import com.kakaxi.fightdemo.network.uploaddowon.DownloadProgressListener;
import com.kakaxi.fightdemo.network.uploaddowon.ServiceGenerator;
import com.kakaxi.fightdemo.utils.Logger;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UploadDowonActivity extends AppCompatActivity implements DownloadProgressListener {
    private static final String TAG = "UploadDowonActivity";
    private Logger log = Logger.getLogger(TAG);
    @BindView(R.id.textView2)
    TextView title_tv;
    @BindView(R.id.btn_down)
    Button btnDown;
    @BindView(R.id.btn_upload)
    Button btnUpload;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private Observable<File> observable;

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, UploadDowonActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);


    }
    @OnClick({R.id.btn_down, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_down:
                downloadFile();
                break;
            case R.id.btn_upload:
                upLoadFile();
                break;
        }
    }
    /**
     * 上传
     */
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

    /**
     * 下载
     */
    private void downloadFile() {
        String url = "http://issuecdn.baidupcs.com/issue/netdisk/apk/BaiduNetdisk_7.15.1.apk";
        String url2 = "http://t.img.i.hsuperior.com/a38ee054-b941-4eb9-9e83-ba45a2ae13a8";
        ApiCommom downloadService = ServiceGenerator.createDownloadService(ApiCommom.class, this);
        String savePath = getExternalFilesDir(null) + File.separator + "BaiduNetdisk.apk";
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
        Call call = downloadService.downloadFileCall(url, savePath);
        call.enqueue(new Callback<File>() {
            @Override
            public void onResponse(Call<File> call, Response<File> response) {
                if (response.isSuccessful() && response.body() != null) {
                    log.e("onResponse", "call_onResponse_file path:" + response.body().getPath());
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
        log.e(TAG, "onDownloadProgress--bytesRead=" + bytesRead + "-------contentLength=" + contentLength + "----是否完成下载=" + done);
        double readNum = (bytesRead / (double) contentLength) * 100;
        log.e(TAG, "onDownloadProgress--readNum=" + readNum);
        int progress = (int) Math.ceil(readNum);
        log.e(TAG, "onDownloadProgress--progress=" + progress);
//        title_tv.setText("5555555555%");
        mProgressBar.setProgress(progress);
        log.e(TAG, "onDownloadProgress--thread=" + Thread.currentThread().getName());

    }


}
