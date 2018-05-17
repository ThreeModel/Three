package com.example.asus.threemodel.presenter.presenter;

import com.example.asus.threemodel.model.model.BaseModel;
import com.example.asus.threemodel.view.inter.IBaseView;

public abstract class BasePresenter <V  extends IBaseView>{

    private V v;


    abstract void getJson(String url);


    public void attachView(V iBaseView) {
        this.v = iBaseView;
    }


    public V getView(){
        return  v;
    }

    public BaseModel getBaseModel() {
        return  new BaseModel();
    }

    public void detachView() {
        v = null;
    }

}
