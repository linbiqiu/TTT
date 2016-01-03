package com.nima519.utils;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by nima519 on 2016/1/3.
 * 收集以及释放Activity
 */
public class ActivityManagerUtils {
    private ArrayList<Activity> activityList = new ArrayList<Activity>();
    private static ActivityManagerUtils activityManagerMangerUtils;

    private ActivityManagerUtils() {

    }

    public static ActivityManagerUtils getInstance() {
        if (activityManagerMangerUtils == null) {
            activityManagerMangerUtils = new ActivityManagerUtils();
        }
        return activityManagerMangerUtils;
    }

    public Activity getTopActivity() {
        if (activityList.size() != 0) {
            return activityList.get(activityList.size() - 1);
        }
        return null;
    }

    public void addActivity(Activity ac) {
        activityList.add(ac);
    }

    public void removeAllActivity() {
        for (Activity ac : activityList
                ) {
            if (ac != null) {
                if (!ac.isFinishing()) {
                    ac.finish();
                }
                ac = null;
            }
        }
        activityList.clear();
    }
}
