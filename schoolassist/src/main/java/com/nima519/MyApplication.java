package com.nima519;

import android.app.Activity;
import android.app.Application;

import com.nima519.utils.ActivityManagerUtils;

/**
 * Created by nima519 on 2015/12/30.
 */
public class MyApplication extends Application {
    public static String TAG;
    private static MyApplication myApplication = null;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        TAG = getClass().getSimpleName();
        myApplication = this;

    }

    public void addActivity(Activity ac){
        ActivityManagerUtils.getInstance().addActivity(ac);
    }

    public void exit(){
        ActivityManagerUtils.getInstance().removeAllActivity();
    }

    public Activity getTopActivity(){
        return ActivityManagerUtils.getInstance().getTopActivity();
    }
}
