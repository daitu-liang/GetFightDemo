package com.daitu_liang.study.mytest.entity;

import java.io.Serializable;

/**
 * Created by leixiaoliang on 2017/3/31.
 * 邮箱：lxliang1101@163.com
 */

public class ContentTypeEntity implements Serializable {

    /**
     * double_col_mode : false
     * umeng_event : moment
     * is_default_tab : false
     * url : http://lf.snssdk.com/neihan/dongtai/dongtai_list/v1/
     * list_id : -10001
     * refresh_interval : 86400
     * type : 3
     * name : 关注
     */

    private boolean double_col_mode;
    private String umeng_event;
    private boolean is_default_tab;
    private String url;

    public String getList_id() {
        return list_id;
    }

    public void setList_id(String list_id) {
        this.list_id = list_id;
    }

    private String list_id;
    private int refresh_interval;
    private int type;
    private String name;

    public boolean isDouble_col_mode() {
        return double_col_mode;
    }

    public void setDouble_col_mode(boolean double_col_mode) {
        this.double_col_mode = double_col_mode;
    }

    public String getUmeng_event() {
        return umeng_event;
    }

    public void setUmeng_event(String umeng_event) {
        this.umeng_event = umeng_event;
    }

    public boolean isIs_default_tab() {
        return is_default_tab;
    }

    public void setIs_default_tab(boolean is_default_tab) {
        this.is_default_tab = is_default_tab;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public int getRefresh_interval() {
        return refresh_interval;
    }

    public void setRefresh_interval(int refresh_interval) {
        this.refresh_interval = refresh_interval;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
