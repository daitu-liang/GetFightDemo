package com.daitu_liang.study.mytest.unittest;

import android.test.ProviderTestCase2;

/**
 * Created by leixiaoliang on 2017/2/24.
 */
public class ContentProviderTestCase extends ProviderTestCase2<ContentProvideTest> {

    public ContentProviderTestCase() {
        super(ContentProvideTest.class, ContentProvideTest.providerAuthority);
    }
}
