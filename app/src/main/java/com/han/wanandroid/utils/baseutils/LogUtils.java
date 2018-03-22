package com.han.wanandroid.utils.baseutils;

import android.content.Context;
import android.util.Log;

import com.han.wanandroid.utils.Constant;

/**
 * description：
 * author： hans
 * date: 2017/7/25
 * e-mail: hxxx1992@163.com
 */

public class LogUtils {
    public static void e(Context context, String log) {
        if (Constant.IS_DEBUG_MODE) {
            try {
                Log.e(context.getClass().getSimpleName(), log);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void e(String log) {
        if (Constant.IS_DEBUG_MODE) {
            try {
                Log.e("log", log);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void e(String tag, String str) {
        if (Constant.IS_DEBUG_MODE) {
            try {
                Log.e(tag, str);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void w(String tag, String str) {
        if (Constant.IS_DEBUG_MODE) {
            try {
                Log.w(tag, str);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void i(String tag, String str) {
        if (Constant.IS_DEBUG_MODE) {
            try {
                Log.i(tag, str);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void d(String tag, String str) {
        if (Constant.IS_DEBUG_MODE) {
            try {
                Log.d(tag, str);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static void v(String tag, String str) {
        if (Constant.IS_DEBUG_MODE) {
            try {
                Log.v(tag, str);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
