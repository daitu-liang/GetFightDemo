package com.daitu_liang.study.mytest.util;

import android.content.Context;


public class PreferencesManager extends BasePreferencesManager {


    private static PreferencesManager instance;



    private static final String NUNIX_VALUE = "Nunix";//保存时间戳



    public String getSaveNunix() {
        return saveNunix;
    }

    private String saveNunix;


    public static PreferencesManager getInstance(Context context) {
        if (null == instance) instance = new PreferencesManager(context.getApplicationContext());
        return instance;
    }

    private PreferencesManager(Context context) {
        super(context);
        loadData();
    }

    @Override
    public void logout() {

    }

    private void loadData() {
        try {
            saveNunix=getString(NUNIX_VALUE, "");

        } catch (Exception e){}
    }






    /**
     * 保存时间戳
     * @param nunix
     */
    public void setSaveNunix(String nunix) {
        this.saveNunix = nunix;
        saveString(NUNIX_VALUE, nunix);
        Logger.getLogger("").e("保存时间戳","nunix--Times="+nunix);
    }


}
