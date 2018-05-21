package com.example.asus.threemodel.presenter.presenter;


import com.example.asus.threemodel.presenter.inter.IMainPresenter;
import com.example.asus.threemodel.view.inter.IMainView;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {
    IMainView iMainView;

    public MainPresenter(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    @Override
    public void getJson(String url) {
        getBaseModel().getJson(url, this);
    }


    @Override
    public void onSuccess(String data) {
        iMainView.onSuccess(data);
    }

    @Override
    public void onErr(int code, String err) {
        getView().onErr(code,err);
    }
}
