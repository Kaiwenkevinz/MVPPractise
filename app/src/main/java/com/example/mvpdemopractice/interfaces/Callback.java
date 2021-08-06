package com.example.mvpdemopractice.interfaces;

/**
 * 用来调用上一层
 * @param <K>
 * @param <V>
 */
public interface Callback <K, V> {

    /**
     * 运行成功，通知上一层并传入数据
     * @param data
     */
    void onSuccess(K data);

    /**
     * 运行失败，通知上一层并传入错误信息
     * @param data
     */
    void onFail(V data);

}
