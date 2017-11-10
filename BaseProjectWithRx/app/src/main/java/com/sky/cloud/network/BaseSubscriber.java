package com.sky.cloud.network;


import com.sky.cloud.bean.NetworkResultBean;
import com.sky.cloud.interfaces.IReqCallBack;

import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;


/**
 * 基础Observer
 * Created by liujian on 2017/11/10.
 */

public class BaseSubscriber<T> extends Subscriber<T>
{
    private IReqCallBack<T> reqCallBack;

    public BaseSubscriber(IReqCallBack<T> iReqCallBack)
    {
        this.reqCallBack = iReqCallBack;
    }


    @Override
    public void onCompleted()
    {
        if (reqCallBack!=null)
        {
            reqCallBack.reqCompleted();
        }
    }

    @Override
    public void onError(Throwable e)
    {
        e.printStackTrace();
        if (reqCallBack!=null)
        {
            if (e instanceof HttpException)
            {
                HttpException httpException = (HttpException) e;
                int code = httpException.code();
                String msg = httpException.getMessage();
                if (code == 504)
                {
                    msg = "网络不给力,请检查网络";
                }
                reqCallBack.reqFailure(code, msg);
            }
            else if (e instanceof SocketTimeoutException)
            {
                reqCallBack.reqFailure(333, "链接超时");
            }
            else
            {
                reqCallBack.reqFailure(0, e.getMessage());
            }
            reqCallBack.reqCompleted();
        }
    }

    @Override
    public void onNext(T t)
    {
        NetworkResultBean result = (NetworkResultBean) t;
        switch (result.getCode())
        {
            case "999004"://登录超时

                break;
            case "999999":
            case "999001":
            case "999002":
            case "999003":
            case "999005":
            case "999006":
                break;

            default:
                if (reqCallBack!=null)
                {
                    reqCallBack.reqSuccess(t);
                }
                break;
        }
    }



}
