package com.example.mvpdemopractice.model;

import com.example.mvpdemopractice.interfaces.Callback;

/**
 * 面向接口编程
 * 方便复用。以后如果需要替换新的model，只要让新model实现此接口即可
 */
public interface ISingleInterfaceModel <K, V> {
    void getData(int curPage, Callback<K, V> callback);
}
