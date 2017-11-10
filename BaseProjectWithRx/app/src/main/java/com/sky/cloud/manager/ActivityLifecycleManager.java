package com.sky.cloud.manager;

import android.app.Activity;
import java.util.Stack;

/**
 * Created by liujian on 2017/11/10.
 */

public class ActivityLifecycleManager
{
    private static class SingletonHolder
    {
        private static final ActivityLifecycleManager INSTANCE = new ActivityLifecycleManager();
    }
    private ActivityLifecycleManager(){}
    public static final ActivityLifecycleManager getInstance()
    {
        return SingletonHolder.INSTANCE;
    }
    private static Stack<Activity> activityStack;

    /**
     * 添加Activity
     * @param activity
     */
    public void addActivity(Activity activity)
    {
        if (activityStack==null)
        {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除Activity
     * @param activity
     */
    public void removeActivity(Activity activity)
    {
        if (activityStack==null || activityStack.size() <=0)
        {
            return;
        }
        activityStack.remove(activity);
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity()
    {
        if (activityStack==null)
        {
            return;
        }
        int size = activityStack.size();
        for (int i = 0; i < size; i++)
        {
            if (null != activityStack.get(i))
            {
                Activity activity = activityStack.get(i);
                if (!activity.isFinishing())
                {
                    finishActivity(activity);
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 结束Activity
     * @param activity
     */
    public void finishActivity(Activity activity)
    {
        if (activity!=null)
        {
            activity.finish();
            removeActivity(activity);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity()
    {
        if (activityStack==null)
        {
            return null;
        }
        return activityStack.lastElement();
    }

    /**
     * 结束当前的Activity
     */
    public void finishCurrentActivity()
    {
        Activity currentActivity = currentActivity();
        if (currentActivity!=null)
        {
            finishActivity(currentActivity);
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp()
    {
        try
        {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
