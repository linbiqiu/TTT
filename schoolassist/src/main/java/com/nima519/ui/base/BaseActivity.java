package com.nima519.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import com.nima519.MyApplication;
import com.nima519.utils.SPUtils;

public class BaseActivity extends Activity {
    protected  static String TAG;
    protected MyApplication mMyApplication;
    protected SPUtils spUtils;
    protected Resources mResources;
    protected Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        initConfig();
    }

    private void initConfig() {
        mContext = this;
        if(mMyApplication == null){
            mMyApplication = MyApplication.getInstance();
        }

        mMyApplication.addActivity(this);
        mResources = getResources();
    }

    /**
     * Activity跳转
     * @param context
     * @param targetActivity
     * @param bundle
     */
    public void redictToActivity(Context context,Class<?> targetActivity,Bundle bundle){
        Intent intent = new Intent(context, targetActivity);
        if(null != bundle){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     *activity.findViewById()
     * @param context
     * @param id
     * @param <T>
     * @return
     */
    public static  <T extends View>  T $(Activity context,int id){
        return (T)context.findViewById(id);
    }

    /**
     * rootView.findViewById()
     * @param rootView
     * @param id
     * @return
     */
    public static <T extends View> T f(View rootView, int id) {
        return (T) rootView.findViewById(id);
    }
}
