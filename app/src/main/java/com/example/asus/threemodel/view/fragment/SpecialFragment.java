package com.example.asus.threemodel.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.application.MyApplication;
import com.example.asus.threemodel.model.bean.BaseBean;
import com.example.asus.threemodel.model.bean.SpecialBean;

public class SpecialFragment extends BaseFragment<SpecialBean> {

    private RecyclerView rv;

    @Override
    int setChildView() {
        return R.layout.special_frament;
    }

    @Override
    void initView() {
        rv = getChildView().findViewById(R.id.special_rv);
        rv.setLayoutManager(new GridLayoutManager(MyApplication.getAppContext(),2));
    }

    @Override
    void initData() {}

    @Override
    void onSuccess(SpecialBean specialBean) {}


    @Override
    void onErr(int code, String errMsg) {}
}
