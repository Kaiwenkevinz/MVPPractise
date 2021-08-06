package com.example.mvpdemopractice.model;

import android.util.Log;

import com.example.mvpdemopractice.api.Api;
import com.example.mvpdemopractice.bean.ArticleListBean;
import com.example.mvpdemopractice.interfaces.Callback;
import com.example.mvpdemopractice.utils.NetUtils;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Model层
 * 主要负责数据的抓取和业务逻辑处理
 * @author zhangkaiwen
 */
public class SingleInterfaceModel implements ISingleInterfaceModel<ArticleListBean, String> {

    private static final String TAG = "SingleInterfaceModel";

    /**
     *
     * @param curPage : 第几页
     * @param callback : 使用Callback接口来实现的回调函数, 用来通知Presenter层
     */
    @Override
    public void getData(int curPage, Callback<ArticleListBean, String> callback) {
        NetUtils.getRetrofit()
                .create(Api.class)
                .getData(curPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArticleListBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, ">>> onCompleted() <<<");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFail("出现错误");
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(ArticleListBean bean) {
                        if (null == bean) {
                            callback.onFail("出现错误");
                        } else if (bean.errorCode != 0) {
                            callback.onFail(bean.errorMsg);
                        } else {
                            callback.onSuccess(bean);
                        }
                    }
                });
    }

}
