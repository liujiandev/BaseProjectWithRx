package com.sky.cloud.utils;

import android.os.Environment;

/**
 * App配置信息
 * Created by liujian on 2017/11/10.
 */

public interface ConfigInfo
{

    String BASE_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡

    String APP_TYPE = "01";
    String APP_VERSION = "1.0";
    String API_VERSION = "v1";
}
