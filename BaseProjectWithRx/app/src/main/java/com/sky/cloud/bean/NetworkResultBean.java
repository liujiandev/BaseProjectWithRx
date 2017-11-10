package com.sky.cloud.bean;

/**
 * 网络请求结果Bean
 * Created by liujian on 2017/11/10.
 */

public class NetworkResultBean<T>
{
    String code;
    String msg;
    T resultBean;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResultBean() {
        return resultBean;
    }

    public void setResultBean(T resultBean) {
        this.resultBean = resultBean;
    }
}
