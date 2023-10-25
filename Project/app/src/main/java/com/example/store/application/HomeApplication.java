package com.example.store.application;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

public class HomeApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext=getApplicationContext();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static Context getAppContext() {
        return appContext;
    }
}
