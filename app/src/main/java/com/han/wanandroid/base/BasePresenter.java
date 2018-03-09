package com.han.wanandroid.base;

/**
 * Created by hans
 * date: 2018/3/8 16:31.
 * e-mail: hxxx1992@163.com
 */

public abstract class BasePresenter<T extends IBaseView> {

    protected T mView;

    protected void attachView(T mView) {
        this.mView = mView;
    }

    protected void dettachView() {
        mView = null;

    }

}