package com.sky.cloud.uiView;

/**
 * Created by liujian on 2017/11/10.
 */

public interface IBaseView
{
    /**
     * 显示加载进度条
     */
    void showProgressDialog();

    /**
     * 隐藏加载进度条
     */
    void dismissProgressDialog();

    /**
     * 显示吐司提示语
     * @param msg
     */
    void showPrompt(String msg);
}
