package com.han.wanandroid.presenter;

import android.content.Context;

import com.han.wanandroid.base.BasePresenter;
import com.han.wanandroid.model.pojo.ResponseBean;
import com.han.wanandroid.model.pojo.UserBean;
import com.han.wanandroid.net.DefaultObserver;
import com.han.wanandroid.net.RetrofitManager;
import com.han.wanandroid.net.WanApi;
import com.han.wanandroid.utils.Constant;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.utils.RxUtils;
import com.han.wanandroid.utils.SPUtils;
import com.han.wanandroid.utils.ToastUtils;
import com.han.wanandroid.view.IUserCenterView;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public class UserCenterPresenter extends BasePresenter<IUserCenterView> {


    public void clickLogin() {
        if (Constant.isLogin) {
            mView.showUserLoginStatus(true);
        } else {
            mView.toLoginPage();
        }
    }

    public void clickAbout() {
        mView.toAboutMePage();
    }

    public void clickCollection() {
        mView.toCollectionPage();

    }
}
