package com.han.wanandroid.ui.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.net.DefaultObserver;
import com.han.wanandroid.presenter.UserCenterPresenter;
import com.han.wanandroid.ui.activity.AboutActivity;
import com.han.wanandroid.ui.activity.CollectiionActivity;
import com.han.wanandroid.ui.activity.LoginActivity;
import com.han.wanandroid.utils.Constant;
import com.han.wanandroid.utils.baseutils.LogUtils;
import com.han.wanandroid.utils.baseutils.SPUtils;
import com.han.wanandroid.iview.IUserCenterView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCenterFragment extends BaseLazyFragment<UserCenterPresenter> implements IUserCenterView, View.OnClickListener {


    @BindView(R.id.uc_text_name)
    TextView ucTextName;
    @BindView(R.id.uc_text_collection)
    TextView ucTextCollection;
    @BindView(R.id.uc_text_about)
    TextView ucTextAbout;
    @BindView(R.id.uc_text_login)
    TextView ucTextLogin;


    private final int UCCode = 0x008801;

    public UserCenterFragment() {
    }


    @Override
    protected void fetchData() {
        show();
        initText();
    }

    private void initText() {
        ucTextName.setText(getResources().getString(R.string.uc_default_nickname));
        ucTextLogin.setText(getResources().getString(R.string.uc_sign));
        ucTextAbout.setText(getResources().getString(R.string.uc_about));
        ucTextCollection.setText(getResources().getString(R.string.uc_collection));
        ucTextCollection.setOnClickListener(this);
        ucTextAbout.setOnClickListener(this);
        ucTextLogin.setOnClickListener(this);
    }

    private void show() {
        showProgress();
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(new DefaultObserver<Long>(this) {
                    @Override
                    protected void doOnNext(Long aLong) {
                        dismissProgress();
                    }
                });
    }

    @Override
    protected UserCenterPresenter loadPresenter() {
        return new UserCenterPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user_center;
    }


    @Override
    public void showUserNicename(String nickname) {
        ucTextName.setText(nickname);
    }

    @Override
    public void showUserLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            ucTextName.setText(SPUtils.getString(Constant.LOGIN_USER_NAME));
            ucTextLogin.setText("退出登录");
        } else {
            ucTextName.setText(getResources().getString(R.string.uc_default_nickname));
            ucTextLogin.setText("登录");
            SPUtils.put(Constant.LOGIN_USER_NAME, "");
            SPUtils.put(Constant.LOGIN_USER_PASSWORD, "");
        }
    }

    @Override
    public void toCollectionPage() {
        startActivity(new Intent(getContext(), CollectiionActivity.class));
    }

    @Override
    public void toAboutMePage() {
        startActivity(new Intent(getContext(), AboutActivity.class));

    }

    @Override
    public void toLoginPage() {
        startActivityForResult(new Intent(getContext(), LoginActivity.class), UCCode);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uc_text_about:
                mPresenter.clickAbout();
                break;
            case R.id.uc_text_collection:
                mPresenter.clickCollection();
                break;
            case R.id.uc_text_login:
                mPresenter.clickLogin();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e(TAG, "requestCode:" + requestCode + ",resultCode:" + resultCode);
        showUserLoginStatus(Constant.isLogin);
    }
}
