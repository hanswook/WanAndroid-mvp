package com.han.wanandroid.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.han.wanandroid.utils.CrashUtils;
import com.han.wanandroid.utils.SPUtils;
import com.han.wanandroid.utils.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by hans
 * date: 2018/3/8 14:13.
 * e-mail: hxxx1992@163.com
 */

public class BaseApp extends Application {
    private static BaseApp app;

    public static Context getAppContext() {
        return app;
    }

    public static Resources getAppResources() {
        return app.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        app=this;
        SPUtils.init(this);
        Utils.init(this);
        CrashUtils.getInstance().init();
        CrashReport.initCrashReport(getApplicationContext(), "a308b192c0", false);

    }

}
