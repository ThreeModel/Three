package com.example.asus.threemodel.model.model;

import com.example.asus.threemodel.presenter.inter.IMainPresenter;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

public class BaseModel {

    public void getJson(String url, final IMainPresenter iMainPresenter) {

        ViseHttp.GET().suffixUrl(url).request(new ACallback<String>() {
            @Override
            public void onSuccess(String data) {
                iMainPresenter.onSuccess(data);
            }

            @Override
            public void onFail(int errCode, String errMsg) {
                iMainPresenter.onErr(errCode,errMsg);
            }
        });
    }
}
