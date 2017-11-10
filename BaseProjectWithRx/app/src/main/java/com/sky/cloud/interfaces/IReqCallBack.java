package com.sky.cloud.interfaces;

/**
 * 请求回调接口
 * Created by liujian on 2017/11/10.
 */

public interface IReqCallBack<T>
{
    void reqSuccess(T result);

    void reqFailure(int code, String msg);

    void reqCompleted();
}
