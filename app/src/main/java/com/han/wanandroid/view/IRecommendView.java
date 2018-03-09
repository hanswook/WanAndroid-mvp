package com.han.wanandroid.view;

import com.han.wanandroid.base.IBaseView;
import com.han.wanandroid.model.pojo.ArticleBean;

import java.util.List;

/**
 * Created by hans
 * date: 2018/3/8 20:22.
 * e-mail: hxxx1992@163.com
 */

public interface IRecommendView extends IBaseView {

    void loadRecycler(List<ArticleBean> list);
}
