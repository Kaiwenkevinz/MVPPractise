package com.example.mvpdemopractice.presenter;

/**
 * TODO: 还不太清楚这个接口的意义
 * 初步猜测是实现对View层生命周期的感知
 */
public interface IPresenter <T> {

    void attachView(T View);

    void detachView(T View);

    boolean isViewAttached();

}
