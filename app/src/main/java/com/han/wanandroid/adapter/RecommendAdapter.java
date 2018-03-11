package com.han.wanandroid.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.han.wanandroid.R;
import com.han.wanandroid.model.pojo.ArticleBean;

import java.util.List;

/**
 * Created by hans
 * date: 2018/3/9 17:39.
 * e-mail: hxxx1992@163.com
 */

public class RecommendAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {


    public RecommendAdapter(int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.recommend_item_text_title, item.getTitle());
        helper.setText(R.id.recommend_item_text_author, item.getAuthor());
        helper.setText(R.id.recommend_item_text_chaptername, item.getChapterName());
        helper.setText(R.id.recommend_item_text_nicedate, item.getNiceDate());
        helper.setText(R.id.recommend_item_text_collect, mContext.getResources().getString(R.string.ic_collect_nor));
    }
}
