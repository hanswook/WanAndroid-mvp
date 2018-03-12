package com.han.wanandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hans
 * date: 2018/3/8 14:06.
 * e-mail: hxxx1992@163.com
 */

public abstract class BaseActivity<T extends BasePresenter> extends BaseCoreActivity {
    protected Unbinder unbinder;
    protected Context context;
    protected String TAG;

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
            TAG = getClass().getSimpleName();
            unbinder = ButterKnife.bind(this);
        }
        mPresenter = loadPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
        //TODO 修改为在子类中控制，不适用常量
        if (isFullScreen()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        init();
    }

    protected abstract T loadPresenter();

    protected abstract void init();

    public abstract int getLayoutId();

    protected boolean isFullScreen() {
        return false;
    }

    /**
     * 设置系统标题栏的透明度
     *
     * @param activity
     * @param on
     */
    protected void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (null != mPresenter) {
            mPresenter.dettachView();
        }
        super.onDestroy();

    }
}
