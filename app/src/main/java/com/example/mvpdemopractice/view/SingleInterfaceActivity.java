package com.example.mvpdemopractice.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpdemopractice.R;
import com.example.mvpdemopractice.base.BaseMVPActivity;
import com.example.mvpdemopractice.bean.ArticleListBean;
import com.example.mvpdemopractice.contract.SingleInterfaceContract;
import com.example.mvpdemopractice.presenter.SingleInterfacePresenter;

/**
 * 继承了BaseMVPActivity使得可以移除onCreate等方法，减少Activity的代码量
 */
public class SingleInterfaceActivity extends BaseMVPActivity<SingleInterfacePresenter> implements SingleInterfaceContract.View {

    private Button button;
    private TextView textView;

    @Override
    protected void initUI() {
        setContentView(R.layout.activity_singleinterface);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getData(0);
            }
        });
    }

    @Override
    protected SingleInterfacePresenter createPresenter() {
        return new SingleInterfacePresenter();
    }

    @Override
    public void showArticleSuccess(ArticleListBean bean) {
        textView.setText(bean.toString());
    }

    @Override
    public void showArticleFail(String msg) {
        Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
    }
}