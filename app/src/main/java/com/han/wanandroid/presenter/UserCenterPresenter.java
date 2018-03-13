package com.han.wanandroid.presenter;

import com.han.wanandroid.base.BasePresenter;
import com.han.wanandroid.utils.Constant;
import com.han.wanandroid.iview.IUserCenterView;

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
