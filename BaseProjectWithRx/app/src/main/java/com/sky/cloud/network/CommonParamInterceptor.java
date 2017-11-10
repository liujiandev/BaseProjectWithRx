package com.sky.cloud.network;

import com.sky.cloud.MyApplication;
import com.sky.cloud.utils.ConfigInfo;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 公共请求参数拦截器
 * Created by liujian on 2017/11/10.
 */

public class CommonParamInterceptor implements Interceptor
{
    @Override
    public Response intercept(Chain chain) throws IOException
    {
        Request originalRequest = chain.request();
        Request request;
        HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                // Provide your custom parameter here
                .addQueryParameter("versionType", ConfigInfo.APP_TYPE)
                .addQueryParameter("clientType", ConfigInfo.APP_TYPE)
                .addQueryParameter("appCategory", ConfigInfo.APP_TYPE)
                .addQueryParameter("version", ConfigInfo.API_VERSION)
                .addQueryParameter("versionNo", ConfigInfo.API_VERSION)
                .build();
        request = originalRequest.newBuilder().url(modifiedUrl).build();
        return chain.proceed(request);
    }
}
