package com.example.myapplication;


import android.app.Application;
public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        setTheme(androidx.appcompat.R.style.Theme_AppCompat);
    }
}
