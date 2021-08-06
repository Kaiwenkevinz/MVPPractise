package com.example.mvpdemopractice.base;

import com.example.mvpdemopractice.presenter.IPresenter;

/**
 * Presenter基类，负责P对V层感知生命周期
 * @param <T>
 */
public abstract class BasePresenter <T> implements IPresenter<T> {

    // 持有V层的引用, 这样继承了BasePresenter的Presenter可以直接用
    // 访问级别设置为protected, 子类可访问
    protected T mView;

    /**
     * 这里注意mView需要初始化，一种方法是在BaseActivity中操作
     * @param view : Activity, Fragment
     */
    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView(T View) {
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }
}
