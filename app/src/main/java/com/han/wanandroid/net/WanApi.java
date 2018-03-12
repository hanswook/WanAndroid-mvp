package com.han.wanandroid.net;

import com.han.wanandroid.model.pojo.ArticleBean;
import com.han.wanandroid.model.pojo.BannerBean;
import com.han.wanandroid.model.pojo.CollectBean;
import com.han.wanandroid.model.pojo.DataBean;
import com.han.wanandroid.model.pojo.FriendBean;
import com.han.wanandroid.model.pojo.HotkeyBean;
import com.han.wanandroid.model.pojo.NaviPrimaryBean;
import com.han.wanandroid.model.pojo.ProjectBean;
import com.han.wanandroid.model.pojo.ResponseBean;
import com.han.wanandroid.model.pojo.TreeBean;
import com.han.wanandroid.model.pojo.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by hans
 * e-mail: hxxx1992@163.com
 */

public interface WanApi {


    //    参数：页码，拼接在连接中，从0开始。
    @GET("/article/list/{pageIndex}/json")
    Observable<ResponseBean<DataBean<ArticleBean>>> getArticleList(
            @Path("pageIndex") int pageIndex
    );

    @GET("/banner/json")
    Observable<ResponseBean<List<BannerBean>>> getBannerList(
    );

    @GET("/friend/json")
    Observable<ResponseBean<List<FriendBean>>> getFriendList(
    );

    @GET("/hotkey/json")
    Observable<ResponseBean<List<HotkeyBean>>> getHotkeyList(
    );

    @GET("/tree/json")
    Observable<ResponseBean<List<TreeBean<TreeBean>>>> getTreeList(
    );

    @GET("/article/list/{pageIndex}/json")
    Observable<ResponseBean<DataBean<ArticleBean>>> getTreeDetailList(
            @Path("pageIndex") int pageIndex,
            @Query("cid") int cid
    );

    @GET("/navi/json")
    Observable<ResponseBean<List<NaviPrimaryBean<ArticleBean>>>> getNaviList(
    );

    @GET("/project/tree/json")
    Observable<ResponseBean<List<ProjectBean>>> getProjectList(
    );

    //    页码：拼接在链接中，从1开始。
    @GET("/project/list/{pageIndex}/json?cid=294")
    Observable<ResponseBean<DataBean<ArticleBean>>> getProjectDetailList(
            @Path("pageIndex") int pageIndex,
            @Query("cid") int cid
    );


    @POST("/user/login")
    Observable<ResponseBean<UserBean>> requestLogin(
            @Query("username") String username,
            @Query("password") String password

    );

    @GET("/lg/collect/list/0/json")
    Observable<ResponseBean<DataBean<CollectBean>>> requestCollectList(

    );

    @POST("/lg/collect/{articleId}/json")
    Observable<ResponseBean> requestCollect(
            @Path("articleId") int articleId
    );

    @POST("/lg/uncollect_originId/{articleId}/json")
    Observable<ResponseBean> requestUnCollectInList(
            @Path("articleId") int articleId
    );



}
