package com.example.asus.threemodel.presenter.presenter;

import com.example.asus.threemodel.model.model.BaseModel;
import com.example.asus.threemodel.presenter.inter.IMainPresenter;
import com.example.asus.threemodel.view.inter.ISpecialView;

public class SpecialPresenter extends BasePresenter implements IMainPresenter{
    private final ISpecialView iSpecialView;

    public SpecialPresenter(ISpecialView iSpecialView) {
        this.iSpecialView = iSpecialView;
    }

    @Override
    void getJson(String url) {
        new BaseModel().getJson(url,this);
    }

    @Override
    public void onSuccess(String data) {
        
    }

    @Override
    public void onErr(int code, String err) {

    }
}
