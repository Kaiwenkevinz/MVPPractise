package com.example.mvpdemopractice.presenter;

import android.util.Log;

import com.example.mvpdemopractice.base.BasePresenter;
import com.example.mvpdemopractice.bean.ArticleListBean;
import com.example.mvpdemopractice.contract.SingleInterfaceContract;
import com.example.mvpdemopractice.interfaces.Callback;
import com.example.mvpdemopractice.model.SingleInterfaceModel;

public class SingleInterfacePresenter extends BasePresenter<SingleInterfaceContract.View> implements SingleInterfaceContract.Presenter {

    SingleInterfaceModel model = null;
    private static final String TAG = "SingleInterfacePresent";

    public SingleInterfacePresenter() {
        // Presenter层持有Model层的引用
        model = new SingleInterfaceModel();
    }

    @Override
    public void getData(int curPage) {
        // 调用Model层
        // 传入callback对象来回调获取数据
        model.getData(curPage, new Callback<ArticleListBean, String>() {
            @Override
            public void onSuccess(ArticleListBean data) {
                // model层获得数据成功，通知View层更新
                Log.d(TAG, ">>> onSuccess() <<<");
                Log.d(TAG, data.toString());
                // TODO：此处执行通知View层代码
                mView.showArticleSuccess(data);
            }


            @Override
            public void onFail(String data) {
                Log.d(TAG, ">>> onFail() <<<");
                Log.d(TAG, data);
                // TODO：此处执行通知View层代码
                mView.showArticleFail(data);
            }
        });

    }

}
