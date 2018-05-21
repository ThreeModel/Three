package com.example.asus.threemodel.view.inter;

public interface IMainView extends IBaseView{

    void onSuccess(String bean);
    void onErr(int code,String errMsg);

}
