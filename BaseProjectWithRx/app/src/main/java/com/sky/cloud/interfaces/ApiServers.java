package com.sky.cloud.interfaces;

import com.sky.cloud.bean.UserInfo;
import com.sky.cloud.bean.NetworkResultBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liujian on 2017/11/9.
 */

public interface ApiServers
{
    String HTTP = "http://";
    String IP = "192.168.9.165:8080/";
    String BASE_URL = HTTP + IP + "ProductManager/";
    @FormUrlEncoded
    @POST("user/clientLogin.do")
    Observable<NetworkResultBean<UserInfo>> login(@Field("userName") String userName, @Field("userPassword") String userPassword);
}
