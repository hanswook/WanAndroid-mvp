package com.han.wanandroid.ui.activity;

import android.view.MotionEvent;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseActivity;
import com.han.wanandroid.base.BasePresenter;
import com.han.wanandroid.utils.baseutils.LogUtils;

public class AboutActivity extends BaseActivity {


    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void init() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e(TAG, "event:" + event.toString());
        if (event.getAction() == MotionEvent.ACTION_UP) {
            this.finish();
        }
        return super.onTouchEvent(event);
    }
}
