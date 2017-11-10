package com.sky.cloud.network;

import com.sky.cloud.BuildConfig;
import com.sky.cloud.MyApplication;
import com.sky.cloud.interfaces.ApiServers;
import com.sky.cloud.utils.ConfigInfo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit请求客户端实例初始化
 * Created by liujian on 2017/11/10.
 */

public class HttpClient
{
    public static Retrofit mRetrofit;

    public static Retrofit retrofit()
    {
        if (mRetrofit == null)
        {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            /**
             * Log信息拦截器
             */
            if (BuildConfig.DEBUG)
            {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }

            /**
             *设置缓存
             */
//            File cacheFile = new File(ConfigInfo.BASE_PATH, "RetrofitCache");
//            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
//            builder.cache(cache).addInterceptor(new CacheInterceptor());

            /**
             * 设置公共请求参数
             */
//            builder.addInterceptor(new CommonParamInterceptor());

            /**
             * 设置请求头
             */
            Interceptor headerInterceptor = new Interceptor()
            {
                @Override
                public Response intercept(Chain chain) throws IOException
                {
                    Request originalRequest = chain.request();
                    Request.Builder requestBuilder = originalRequest.newBuilder()
                            .header("Content-Type", "text/html;charset=UTF-8")
                            .method(originalRequest.method(), originalRequest.body());
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            };
            builder.addInterceptor(headerInterceptor);

            /**
             * 设置超时和重连
             */
            builder.connectTimeout(90, TimeUnit.SECONDS);
            builder.readTimeout(90, TimeUnit.SECONDS);
            builder.writeTimeout(90, TimeUnit.SECONDS);
            builder.retryOnConnectionFailure(true);

            /**
             * 设置https请求证书
             */
//            builder.sslSocketFactory();

            OkHttpClient okHttpClient = builder.build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(ApiServers.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }
}
