package com.han.wanandroid.iview;

import com.han.wanandroid.base.IBaseView;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public interface IUserCenterView extends IBaseView {

    void showUserNicename(String nickname);

    void showUserLoginStatus(boolean loginStatus);

    void toCollectionPage();

    void toAboutMePage();

    void toLoginPage();



}
