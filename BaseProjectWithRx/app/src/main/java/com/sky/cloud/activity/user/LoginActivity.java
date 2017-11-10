package com.sky.cloud.activity.user;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.sky.cloud.R;
import com.sky.cloud.base.MVPActivity;
import com.sky.cloud.bean.UserInfo;
import com.sky.cloud.databinding.ActivityLoginBinding;
import com.sky.cloud.presenter.ILoginPresenter;
import com.sky.cloud.presenter.impl.LoginPresenter;
import com.sky.cloud.uiView.ILoginActivityView;

/**
 * 登录界面
 */
public class LoginActivity extends MVPActivity<ILoginPresenter> implements ILoginActivityView
{

    ActivityLoginBinding view;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        view = DataBindingUtil.setContentView(this,R.layout.activity_login);
    }

    /**
     *
     * @param v
     */
    public void viewClick(View v)
    {
        presenter.login(view.userName.getText().toString(),view.userPassword.getText().toString());
    }

    @Override
    protected ILoginPresenter createPresenter()
    {
        return new LoginPresenter(this);
    }

    @Override
    public void loginResult(UserInfo userInfo)
    {
        if (userInfo!=null)
        {
            view.userInfoTv.setText(userInfo.toString());
        }
    }
}
