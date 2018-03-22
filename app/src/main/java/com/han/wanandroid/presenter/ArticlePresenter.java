package com.han.wanandroid.presenter;

import com.han.wanandroid.base.BasePresenter;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.DataBean;
import com.han.wanandroid.model.pojo.ResponseBean;
import com.han.wanandroid.model.pojo.TreeBean;
import com.han.wanandroid.net.DefaultObserver;
import com.han.wanandroid.net.RetrofitManager;
import com.han.wanandroid.net.WanApi;
import com.han.wanandroid.utils.baseutils.LogUtils;
import com.han.wanandroid.utils.baseutils.ObjectUtils;
import com.han.wanandroid.utils.baseutils.RxUtils;
import com.han.wanandroid.iview.IArticleView;

import java.util.List;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public class ArticlePresenter extends BasePresenter<IArticleView> {

    private List<TreeBean<TreeBean>> tabDatas;



    public void getTabData() {
        RetrofitManager.getInstance().create(WanApi.class)
                .getTreeList()
                .compose(RxUtils.<ResponseBean<List<TreeBean<TreeBean>>>>applySchedulers())
                .subscribe(new DefaultObserver<ResponseBean<List<TreeBean<TreeBean>>>>(getmView()) {
                    @Override
                    protected void doOnNext(ResponseBean<List<TreeBean<TreeBean>>> listResponseBean) {
                        if (null == listResponseBean || listResponseBean.getErrorCode() < 0) {
                            return;
                        }
                        tabDatas = listResponseBean.getData();
                        getmView().loadTabsData(listResponseBean.getData());
                    }
                });
    }

    public void getRecyclerData(int treeId) {
        LogUtils.e(TAG, "treeId:" + treeId);
        RetrofitManager.getInstance().create(WanApi.class)
                .getTreeDetailList(0, treeId)
                .compose(RxUtils.<ResponseBean<DataBean<ArticleBean>>>applySchedulers())
                .subscribe(new DefaultObserver<ResponseBean<DataBean<ArticleBean>>>(getmView()) {
                    @Override
                    protected void doOnNext(ResponseBean<DataBean<ArticleBean>> dataBeanResponseBean) {

                        if (ObjectUtils.isEmpty(dataBeanResponseBean)) {
                            return;
                        }
                        getmView().loadRecyclerData(dataBeanResponseBean.getData().getDatas());

                    }
                });
    }




}
