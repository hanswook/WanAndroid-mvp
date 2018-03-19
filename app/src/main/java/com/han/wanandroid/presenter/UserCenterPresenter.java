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
            getmView().showUserLoginStatus(true);
        } else {
            getmView().toLoginPage();
        }
    }

    public void clickAbout() {
        getmView().toAboutMePage();
    }

    public void clickCollection() {
        getmView().toCollectionPage();

    }
}
