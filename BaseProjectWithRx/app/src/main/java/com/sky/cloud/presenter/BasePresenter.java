package com.sky.cloud.presenter;

import com.sky.cloud.interfaces.ApiServers;
import com.sky.cloud.network.HttpClient;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * 基础Presenter
 * Created by liujian on 2017/11/10.
 */

public abstract class BasePresenter<V> implements Presenter<V>
{
    protected V iView;
    protected ApiServers apiServers = HttpClient.retrofit().create(ApiServers.class);
    private CompositeSubscription mCompositeSubscription;
    //RXjava取消注册，以避免内存泄露
    public void onUnSubscribe()
    {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions())
        {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber)
    {
        if (mCompositeSubscription == null)
        {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    @Override
    public void attachView(V view)
    {
        this.iView = view;
    }

    @Override
    public void detachView()
    {
        this.iView = null;
        onUnSubscribe();
    }
}
