package com.han.wanandroid.utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by hans
 * date: 2018/3/8 15:19.
 * e-mail: hxxx1992@163.com
 */

public class Constant {

    public static final boolean IS_DEBUG_MODE = true;

    public static String BASE_URL = "http://www.wanandroid.com";  //gank

    public static String PATH_CACHE = Environment.getExternalStorageDirectory().getPath() + File.separator + "orchard";

    public static final String SP_NAME = "han_wanandroid";
    public static final String WEB_DETAIL_URL = "web_detail_url";

    public static final String LOGIN_USER_NAME_COOKIE = "loginUserName";
    public static final String LOGIN_USER_PASSWORD_COOKIE = "loginUserPassword";
    public static final String LOGIN_USER_NAME = "userName";
    public static final String LOGIN_USER_PASSWORD = "userPassword";




    public static boolean isLogin = false;
}
