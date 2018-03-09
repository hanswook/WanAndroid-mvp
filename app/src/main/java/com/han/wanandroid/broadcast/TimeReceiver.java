package com.han.wanandroid.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.han.wanandroid.utils.LogUtils;

/**
 * Created by hans
 * date: 2018/3/9 15:05.
 * e-mail: hxxx1992@163.com
 */

public class TimeReceiver extends BroadcastReceiver {
    private String TAG = "TimeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.e(TAG, "TimeReceiver onReceive");
    }
}
