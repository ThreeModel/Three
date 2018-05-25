package com.example.asus.threemodel.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.adapter.SpecialAdapter;
import com.example.asus.threemodel.view.inter.IMainView;
import com.example.asus.threemodel.view.tools.BaseUrl;
import com.google.gson.Gson;

import java.util.List;

public class SpecialFragment extends Fragment implements IMainView {

    private View view;
    private GridView gv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.special_frament, null);
        initView();
        initData();
        return view;
    }

    private void initView() {
        gv = view.findViewById(R.id.special_gridview);
    }

    private void initData() {
        MainPresenter persenter = new MainPresenter(this);
        persenter.getJson(BaseUrl.BASEURL+"front/homePageApi/homePage.do");
    }

    @Override
    public void onSuccess(String bean) {
        VideoBean resultBean = new Gson().fromJson(bean, VideoBean.class);
        List<VideoBean.RetBean.ListBean> listBeans = resultBean.getRet().getList();
        SpecialAdapter adapter = new SpecialAdapter(listBeans, getContext());
        gv.setAdapter(adapter);
    }

    @Override
    public void onErr(int code, String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_SHORT).show();
    }


}
