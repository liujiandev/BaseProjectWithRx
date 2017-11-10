package com.sky.cloud.presenter.impl;

import com.sky.cloud.bean.UserInfo;
import com.sky.cloud.bean.NetworkResultBean;
import com.sky.cloud.interfaces.IReqCallBack;
import com.sky.cloud.network.BaseSubscriber;
import com.sky.cloud.presenter.BasePresenter;
import com.sky.cloud.presenter.ILoginPresenter;
import com.sky.cloud.uiView.ILoginActivityView;

/**
 * 登录业务接口实现
 * Created by liujian on 2017/11/10.
 */

public class LoginPresenter extends BasePresenter<ILoginActivityView> implements ILoginPresenter
{
    public LoginPresenter(ILoginActivityView view)
    {
        attachView(view);
    }

    @Override
    public void login(String userName, String userPwd)
    {
        iView.showProgressDialog();
        addSubscription(apiServers.login(userName,userPwd), new BaseSubscriber<NetworkResultBean<UserInfo>>(
                new IReqCallBack<NetworkResultBean<UserInfo>>()
        {
            @Override
            public void reqSuccess(NetworkResultBean<UserInfo> result)
            {
                switch (result.getCode())
                {
                    case "1":
                        iView.loginResult(result.getResultBean());
                        break;
                    default:
                        iView.showPrompt(result.getMsg());
                        break;
                }
            }

            @Override
            public void reqFailure(int code, String msg)
            {
                iView.showPrompt(msg);
            }

            @Override
            public void reqCompleted()
            {
                iView.dismissProgressDialog();
            }
        }));
    }
}
