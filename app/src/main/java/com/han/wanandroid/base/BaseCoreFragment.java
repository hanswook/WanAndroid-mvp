package com.han.wanandroid.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.han.wanandroid.tools.CustomProgressDialog;
import com.han.wanandroid.tools.CustomProgressDialogHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public abstract class BaseCoreFragment<T extends BasePresenter> extends BaseRxFragment {
    protected View rootView;
    protected LayoutInflater inflater;
    protected Activity activity;
    protected Context context;
    private CustomProgressDialog mProgressDialog;
    private Unbinder unbinder;

    protected String TAG;
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        this.inflater = inflater;
        mProgressDialog = CustomProgressDialogHelper.createDialog(getContext());
        mProgressDialog.setCanceledOnTouchOutside(false);
        if (rootView == null) {
            rootView = inflater.inflate(this.getLayoutId(), container, false);
        }
        activity = getActivity();
        context = getContext();
        TAG = this.getClass().getSimpleName();
        unbinder = ButterKnife.bind(this, rootView);
        mPresenter = loadPresenter();
        if (null != mPresenter) {
            mPresenter.attachView(this);
        }
        init();
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }

        return rootView;

    }

    protected abstract T loadPresenter();


    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress(String msg) {
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 显示ProgressDialog
     */
    @Override
    public void showProgress() {
        mProgressDialog.show();
    }

    /**
     * 取消ProgressDialog
     */
    @Override
    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    @Override
    public void onDestroyView() {
        if (null != unbinder) {
            unbinder.unbind();
        }
        if (null != mPresenter) {
            mPresenter.dettachView();
        }
        super.onDestroyView();
    }
}
