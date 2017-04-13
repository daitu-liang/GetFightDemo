package com.kakaxi.fightdemo.network.api.commom;

import com.kakaxi.fightdemo.bean.NiuxInfo;
import com.kakaxi.fightdemo.network.HttpResult;
import com.kakaxi.fightdemo.network.api.NetApi;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by leixiaoliang on 2017/4/13.
 * 邮箱：lxliang1101@163.com
 */

public interface ApiCommom {

    @GET(NetApi.UGET_NUIX)
    Observable<HttpResult<NiuxInfo>> getNunix();
}
