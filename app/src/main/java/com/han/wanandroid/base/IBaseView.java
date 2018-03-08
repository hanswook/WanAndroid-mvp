package com.han.wanandroid.base;

import io.reactivex.disposables.Disposable;

/**
 * Created by hans
 * date: 2018/3/8 13:54.
 * e-mail: hxxx1992@163.com
 */

public interface IBaseView {
    boolean addRxDestroy(Disposable disposable);

    boolean addRxStop(Disposable disposable);

    void remove(Disposable disposable);
    /**
     * 显示ProgressDialog
     */
    void showProgress();

    /**
     * 显示ProgressDialog
     */
    void showProgress(String msg);

    /**
     * 取消ProgressDialog
     */
    void dismissProgress();
}
