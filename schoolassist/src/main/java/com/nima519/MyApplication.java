package com.nima519;

import android.app.Application;

/**
 * Created by nima519 on 2015/12/30.
 */
public class MyApplication extends Application{
    public static String TAG;
    private  static MyApplication myApplication = null;

    public static MyApplication getInstance(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
