package com.csquare.sampleapp;

import android.app.Application;

public class AppClass extends Application {

    private static AppClass sAppClass;

    public static AppClass getInstance(){
        return sAppClass;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppClass = this;
    }
}
