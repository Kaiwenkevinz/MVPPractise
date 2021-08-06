package com.example.mvpdemopractice.contract;

import com.example.mvpdemopractice.bean.ArticleListBean;

/**
 * 回调接口
 */
public interface SingleInterfaceContract {

    /**
     * View层的回调接口
     */
    interface View {

        void showArticleSuccess(ArticleListBean bean);

        void showArticleFail(String msg);

    }

    /**
     * Presenter层的回调接口
     */
    interface Presenter {

        void getData(int curPage);

    }
}
