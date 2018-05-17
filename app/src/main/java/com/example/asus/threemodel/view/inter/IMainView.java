package com.example.asus.threemodel.view.inter;

import com.example.asus.threemodel.model.bean.BaseBean;

public interface IMainView<T extends BaseBean> extends IBaseView{

    void onSuccess(T t);
    void onErr(int code,String errMsg);

}
