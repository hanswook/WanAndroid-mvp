package com.han.wanandroid.presenter;


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
import com.han.wanandroid.iview.IHomeView;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public class HomePresenter extends BasePresenter<IHomeView> {

    public void defaultLogin() {
        String username = SPUtils.getString(Constant.LOGIN_USER_NAME);
        String password = SPUtils.getString(Constant.LOGIN_USER_PASSWORD);
        LogUtils.e(TAG, "username:" + username + ",password:" + password);

        RetrofitManager.getInstance().create(WanApi.class)
                .requestLogin(username, password)
                .compose(RxUtils.<ResponseBean<UserBean>>applySchedulers())
                .subscribe(new DefaultObserver<ResponseBean<UserBean>>(getmView()) {
                    @Override
                    protected void doOnNext(ResponseBean<UserBean> userBean) {
                        LogUtils.e(TAG, "userBean:" + userBean.toString());
                        if (userBean.getErrorCode() < 0) {
                            Constant.isLogin = false;
                            ToastUtils.show("默认登陆失败");
                        } else {
                            ToastUtils.show("默认登录成功");
                            Constant.isLogin = true;
                        }
                    }
                });
    }
}
