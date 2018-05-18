package com.example.asus.threemodel.application;

import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.vise.xsnow.http.ViseHttp;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {
    private static Context context;
    private static int tid;

    @Override
    public void onCreate() {
        super.onCreate();

        ViseHttp.init(this);
        ViseHttp.CONFIG()
                //配置请求主机地址
                .baseUrl("http://api.svipmovie.com/")
                .converterFactory(GsonConverterFactory.create())
                .callAdapterFactory(RxJava2CallAdapterFactory.create());
        context = getApplicationContext();
        tid = Process.myTid();
    }


    public static Context getAppContext() {
        return context;
    }

    public static int getTid() {
        return tid;
    }
}
