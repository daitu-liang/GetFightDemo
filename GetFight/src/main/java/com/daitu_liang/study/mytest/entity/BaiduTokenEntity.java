package com.daitu_liang.study.mytest.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by leixiaoliang on 2017/4/2.
 * 邮箱：lxliang1101@163.com
 */

public class BaiduTokenEntity implements Serializable {

    String getTotalPrice(String[] listData){
        List<String> flagData = Arrays.asList(listData);
       for (int i=0;i<listData.length;i++){
           if(flagData.contains(listData[i])){
               return listData[i];
           }
       }
       return "";
    }

}
