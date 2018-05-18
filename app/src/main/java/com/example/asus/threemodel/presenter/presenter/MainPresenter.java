package com.example.asus.threemodel.presenter.presenter;


import com.example.asus.threemodel.model.bean.ResultBean;
import com.example.asus.threemodel.presenter.inter.IMainPresenter;
import com.example.asus.threemodel.view.inter.IMainView;
import com.google.gson.Gson;

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {


    @Override
    public void getJson(String url) {
        getBaseModel().getJson(url, this);
    }


    @Override
    public void onSuccess(String data) {
        ResultBean resultBean = new Gson().fromJson(data, ResultBean.class);
        getView().onSuccess(resultBean);
    }

    @Override
    public void onErr(int code, String err) {
        getView().onErr(code,err);
    }
}
