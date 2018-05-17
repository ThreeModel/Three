package com.example.asus.threemodel.view.activity;

import android.view.View;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.MainBean;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.inter.IMainView;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView<MainBean> {


    @Override
    void initView() {

    }

    @Override
    void initData() {
        getPresenter().onSuccess("");
    }

    @Override
    View getChildView() {
        return View.inflate(this,R.layout.activity_main,null);
    }

    @Override
    MainPresenter setPresenter() {
        return new MainPresenter();
    }

    @Override
    String getTitleText() {
        return "发现";
    }


    @Override
    public void onSuccess(MainBean mainBean) {

    }

    @Override
    public void onErr(int code, String errMsg) {

    }
}
