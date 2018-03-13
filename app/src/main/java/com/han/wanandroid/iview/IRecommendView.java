package com.han.wanandroid.iview;

import com.han.wanandroid.base.IBaseView;
import com.han.wanandroid.model.pojo.ArticleBean;

import java.util.List;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public interface IRecommendView extends IBaseView {

    void loadMore(List<ArticleBean> list);



}
