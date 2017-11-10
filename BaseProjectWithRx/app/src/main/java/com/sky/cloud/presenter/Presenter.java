package com.sky.cloud.presenter;

/**
 * Created by liujian on 2017/11/10.
 */

public interface Presenter<V>
{
    void attachView(V view);

    void detachView();
}
