package com.sky.cloud.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.sky.cloud.R;
import com.sky.cloud.uiView.IBaseView;
import com.sky.cloud.utils.SystemBarTintManager;
import com.sky.cloud.utils.ToastUtils;

/**
 * Created by liujian on 2017/11/10.
 */

public abstract class BaseActivity extends FragmentActivity implements IBaseView
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        initStatusBar(R.color.action_bar_color);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void dismissProgressDialog() {

    }

    @Override
    public void showPrompt(String msg)
    {
        ToastUtils.showPrompt(this,msg);
    }

    /**
     * 初始化statusBar的背景颜色
     * @param color 背景颜色
     */
    public void initStatusBar(int color)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(color);//通知栏所需颜色
            tintManager.setNavigationBarTintEnabled(true);
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on)
    {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on)
        {
            winParams.flags |= bits;
        }
        else
        {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
