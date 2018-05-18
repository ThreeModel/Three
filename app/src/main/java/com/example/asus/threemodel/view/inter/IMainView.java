package com.example.asus.threemodel.view.inter;

public interface IMainView<T> extends IBaseView{
    void onSuccess(T object);
    void onErr(int code,String errMsg);

}
