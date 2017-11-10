package com.sky.cloud.uiView;

import com.sky.cloud.bean.UserInfo;

/**
 * Created by liujian on 2017/11/10.
 */

public interface ILoginActivityView extends IBaseView
{
    /**
     * 登录结果
     * @param userInfo
     */
    void loginResult(UserInfo userInfo);
}
