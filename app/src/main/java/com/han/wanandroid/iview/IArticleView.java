package com.han.wanandroid.iview;

import com.han.wanandroid.base.IBaseView;
import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.TreeBean;

import java.util.List;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public interface IArticleView extends IBaseView{

    void loadTabsData(List<TreeBean<TreeBean>> data);
    void loadRecyclerData(List<ArticleBean> datas);
    void showFlow(List<TreeBean> data);
}
