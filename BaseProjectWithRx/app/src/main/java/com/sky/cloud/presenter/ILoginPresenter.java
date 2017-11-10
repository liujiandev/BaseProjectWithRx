package com.sky.cloud.presenter;

import com.sky.cloud.uiView.ILoginActivityView;

/**
 * 登录业务接口定义
 * Created by liujian on 2017/11/10.
 */

public interface ILoginPresenter extends Presenter<ILoginActivityView>
{
    /**
     * 登录
     * @param userName
     * @param userPwd
     */
    void login(String userName,String userPwd);
}
