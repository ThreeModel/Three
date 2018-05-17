package com.example.asus.threemodel.presenter.inter;

public interface IMainPresenter extends IBasePresenter{
    void onSuccess(String data);

    void onErr(int code,String err);

}
