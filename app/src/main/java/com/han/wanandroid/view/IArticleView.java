package com.han.wanandroid.view;

import com.han.wanandroid.base.IBaseView;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.TreeBean;

import java.util.List;

/**
 * Created by hans
 * date: 2018/3/8 20:57.
 * e-mail: hxxx1992@163.com
 */

public interface IArticleView extends IBaseView{

    void loadTabsData(List<TreeBean<TreeBean>> data);
    void loadRecyclerData(List<ArticleBean> datas);
}
