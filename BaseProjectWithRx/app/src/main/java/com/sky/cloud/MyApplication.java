package com.sky.cloud;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.sky.cloud.manager.ActivityLifecycleManager;

/**
 * Created by liujian on 2017/11/10.
 */

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks
{
    private static class SingletonHolder
    {
        private static final MyApplication INSTANCE = new MyApplication();
    }
    public MyApplication (){}
    public static final MyApplication getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState)
    {
        ActivityLifecycleManager.getInstance().addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity)
    {
        ActivityLifecycleManager.getInstance().finishActivity(activity);
    }


}
