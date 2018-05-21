package com.example.asus.threemodel.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.threemodel.model.bean.BaseBean;

public abstract class BaseFragment<T extends BaseBean> extends Fragment{

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setChildView(), container, false);
        return view;
    }

    abstract int setChildView();
    abstract void initView();
    abstract void initData();
    abstract void onSuccess(T t);
    abstract void onErr(int code, String errMsg);

    public View getChildView(){
        return view;
    }

}
