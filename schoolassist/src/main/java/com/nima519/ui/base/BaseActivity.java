package com.nima519.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.nima519.MyApplication;

public class BaseActivity extends Activity {
    protected  static String TAG;
    protected MyApplication mMyApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
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
