package com.example.asus.threemodel.presenter.presenter;


import android.util.Log;

import com.example.asus.threemodel.model.bean.MainBean;
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
        Log.e("onSuccess: ",data );
       /* MainBean mainBean = new Gson().fromJson(data, MainBean.class);
        getView().onSuccess(mainBean);*/
    }

    @Override
    public void onErr(int code, String err) {
        getView().onErr(code,err);
    }
}
