package com.example.kmtest;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

/**
 * application基类
 * 静态字段：context，可用于全局获取上下文
 * @author wxc
 * @date 2021.5.17
 */
public class MyApplication extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
