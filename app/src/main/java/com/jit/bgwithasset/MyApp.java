package com.jit.bgwithasset;

import android.app.Application;
import android.content.Context;

/**
 * @author crazyZhangxl on 2018/12/24.
 * Describe:
 */
public class MyApp extends Application {
    private  static  Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getmContext() {
        return mContext;
    }
}
