package com.p2p.dsad.news.appliction;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 *
 * Created by dsad on 2017/9/11.
 */

public class MyAppliction extends Application
{
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        inintokhttp();
    }

    private void inintokhttp()
    {
        OkHttpClient c = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();
        OkHttpUtils.initClient(c);
    }

    public static Context getContext() {
        return context;
    }
}
