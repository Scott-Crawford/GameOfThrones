package com.example.scott.gameofthrones;

import android.app.Application;
import android.content.Context;

/**
 * Created by scott on 6/13/2017.
 */

public class MyApp extends Application {
    private static Context mContext;


    public static Context getContext() {
        //  return instance.getApplicationContext();
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //  instance = this;
        mContext = getApplicationContext();
    }
}