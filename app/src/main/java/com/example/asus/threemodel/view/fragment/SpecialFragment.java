package com.example.asus.threemodel.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.ResultBean;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.inter.IMainView;
import com.google.gson.Gson;

public class SpecialFragment extends Fragment implements IMainView {

    private View view;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.special_frament, null);
        initView();
        initData();
        return view;
    }

    private void initView() {
        recyclerView = view.findViewById(R.id.special_reecycler);
    }

    private void initData() {
        MainPresenter persenter = new MainPresenter(this);
        persenter.getJson("front/homePageApi/homePage.do");
    }

    @Override
    public void onSuccess(String bean) {
        ResultBean resultBean = new Gson().fromJson(bean, ResultBean.class);
    }

    @Override
    public void onErr(int code, String errMsg) {

    }
}
