package com.sky.cloud.base;

import android.os.Bundle;

import com.sky.cloud.presenter.Presenter;

/**
 * MVP模式Activity基础类
 * Created by liujian on 2017/11/10.
 */

public abstract class MVPActivity<P extends Presenter> extends BaseActivity
{
    protected P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (null != presenter)
        {
            presenter.detachView();
        }
    }
}
