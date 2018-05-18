package com.example.asus.threemodel.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.presenter.presenter.BasePresenter;
import com.example.asus.threemodel.view.inter.IBaseView;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    private P presenter;
    private FrameLayout fl;
   public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initSelfView();
        presenter = setPresenter();
        presenter.attachView(this);
        initData();
        initView();

    }

    public P getPresenter() {
        return presenter;
    }

    abstract void initView();
    abstract void initData();
    abstract View getChildView();
    abstract P setPresenter();



    private void initSelfView() {
        fl = findViewById(R.id.base_frameLayout);
        fl.addView(getChildView());
        tv = findViewById(R.id.base_title);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }



}
