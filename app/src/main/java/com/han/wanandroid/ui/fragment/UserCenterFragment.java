package com.han.wanandroid.ui.fragment;


import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.han.wanandroid.R;
import com.han.wanandroid.base.BaseLazyFragment;
import com.han.wanandroid.presenter.UserCenterPresenter;
import com.han.wanandroid.net.DefaultObserver;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserCenterFragment extends BaseLazyFragment<UserCenterPresenter> {

    @BindView(R.id.uc_text_first)
    TextView ucTextFirst;

    public UserCenterFragment() {
    }


    @Override
    protected void fetchData() {
        ucTextFirst.setText("ucTextFirst fetchData");
        show();
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

}
