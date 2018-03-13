package com.han.wanandroid.presenter;

import com.han.wanandroid.base.BasePresenter;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.DataBean;
import com.han.wanandroid.model.pojo.ResponseBean;
import com.han.wanandroid.net.DefaultObserver;
import com.han.wanandroid.net.RetrofitManager;
import com.han.wanandroid.net.WanApi;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.utils.RxUtils;
import com.han.wanandroid.iview.IRecommendView;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public class RecommendPresenter extends BasePresenter<IRecommendView> {


    public void getData(int pageIndex) {
        LogUtils.e("RecommendPresenter", "getdata");
        RetrofitManager.getInstance().create(WanApi.class)
                .getArticleList(pageIndex)
                .compose(RxUtils.<ResponseBean<DataBean<ArticleBean>>>applySchedulers())
                .subscribe(new DefaultObserver<ResponseBean<DataBean<ArticleBean>>>(mView) {
                    @Override
                    protected void doOnNext(ResponseBean<DataBean<ArticleBean>> dataBeanResponseBean) {
                        mView.loadMore(dataBeanResponseBean.getData().getDatas());
                    }
                });
    }
}
