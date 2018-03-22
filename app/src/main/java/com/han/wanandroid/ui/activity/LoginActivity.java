package com.han.wanandroid.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseActivity;
import com.han.wanandroid.base.BasePresenter;
import com.han.wanandroid.model.pojo.ResponseBean;
import com.han.wanandroid.model.pojo.UserBean;
import com.han.wanandroid.net.DefaultObserver;
import com.han.wanandroid.net.RetrofitManager;
import com.han.wanandroid.net.WanApi;
import com.han.wanandroid.utils.Constant;
import com.han.wanandroid.utils.baseutils.LogUtils;
import com.han.wanandroid.utils.baseutils.RxUtils;
import com.han.wanandroid.utils.baseutils.SPUtils;
import com.han.wanandroid.utils.baseutils.ToastUtils;

import butterknife.BindView;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.login_edittext_account)
    EditText loginEdittextAccount;
    @BindView(R.id.login_edittext_password)
    EditText loginEdittextPassword;
    @BindView(R.id.login_btn_login)
    TextView loginBtnLogin;

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void init() {
        loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    String account;
    String password;

    public void show() {
        account = loginEdittextAccount.getEditableText().toString();
        password = loginEdittextPassword.getEditableText().toString();
        LogUtils.e(TAG, "account:" + account + ",password:" + password);
        RetrofitManager.getInstance().create(WanApi.class)
                .requestLogin(account, password)
                .compose(RxUtils.<ResponseBean<UserBean>>applySchedulers())
                .subscribe(new DefaultObserver<ResponseBean<UserBean>>(this) {
                    @Override
                    protected void doOnNext(ResponseBean<UserBean> userBean) {
                        LogUtils.e(TAG, "userBean:" + userBean.toString());
                        if (userBean.getErrorCode() < 0) {
                            ToastUtils.show("登陆失败");
                            Constant.isLogin = false;
                        } else {
                            ToastUtils.show("登录成功");
                            Constant.isLogin = true;
                            SPUtils.put(Constant.LOGIN_USER_NAME, account);
                            SPUtils.put(Constant.LOGIN_USER_PASSWORD, password);
                            ((LoginActivity) context).finish();
                        }
                    }
                });
    }

}
