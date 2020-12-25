package com.example.appdocbao;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        //chinh sửa
        //chỉnh sửa lần 2
        //chỉnh sửa lần 3
        // Chỉnh sửa tại web
    }
}
