package com.han.wanandroid.net;


import com.han.wanandroid.utils.Constant;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.utils.SPUtils;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    private HashSet<String> cookies = new HashSet<>();

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        okhttp3.Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {

            for (String header : originalResponse.headers("Set-Cookie")) {
                LogUtils.d("ReceivedCookiesInterceptor", "header:" + header);
                SPUtils.put(Constant.SP_NAME,header.split("=")[0],header);
                cookies.add(header);
            }
        }
        return originalResponse;
    }
}
