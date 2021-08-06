package com.example.mvpdemopractice.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpdemopractice.presenter.IPresenter;

public abstract class BaseMVPActivity<T extends IPresenter> extends AppCompatActivity {
    
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 在View onCreate的时候初始化Presenter
        initPresenter();
        initUI();
    }

    private void initPresenter() {
        mPresenter = createPresenter();
        // 绑定生命周期
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView(this);
        }

        super.onDestroy();
    }

    protected abstract void initUI();

    protected abstract T createPresenter();
}
