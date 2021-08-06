package com.example.mvpdemopractice.api;

import com.example.mvpdemopractice.bean.ArticleListBean;


import rx.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 提供给Retrofit的接口
 */
public interface Api {

    @GET("article/list/{curPage}/json")
    Observable<ArticleListBean> getData(@Path("curPage") int curPage);

}
