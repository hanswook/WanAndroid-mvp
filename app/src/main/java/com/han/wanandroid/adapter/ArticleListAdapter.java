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
 * e-mail: hxxx1992@163.com
 */

public class ArticleListAdapter extends BaseQuickAdapter<ArticleBean, BaseViewHolder> {


    public ArticleListAdapter(int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        helper.setText(R.id.recommend_item_text_title, item.getTitle());
        helper.setText(R.id.recommend_item_text_author, item.getAuthor());
        helper.setText(R.id.recommend_item_text_chaptername, item.getChapterName());
        helper.setText(R.id.recommend_item_text_nicedate, item.getNiceDate());
        if (item.isCollect()) {
            helper.setText(R.id.recommend_item_text_collect, mContext.getResources().getString(R.string.ic_collect_sel));
        } else {
            helper.setText(R.id.recommend_item_text_collect, mContext.getResources().getString(R.string.ic_collect_nor));
        }
    }

    @Override
    public void setOnItemClickListener(@Nullable OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
    }
}
