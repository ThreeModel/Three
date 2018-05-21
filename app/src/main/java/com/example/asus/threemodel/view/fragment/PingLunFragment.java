package com.example.asus.threemodel.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.inter.IMainView;
import com.example.asus.threemodel.view.tools.BaseUrl;
import com.google.gson.Gson;

public class PingLunFragment extends Fragment implements IMainView{

    private View view;
    private ListView lv;
    private TextView tv1;
    private String loagUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.pinglun, null);
        initView();
        initData();
        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        loagUrl = bundle.getString("loagUrl");
        tv1.setText(loagUrl);
        Log.d("TTT",loagUrl);
        MainPresenter persenter = new MainPresenter(this);
//        persenter.getJson(getNewLoadUrl());
    }

    private String getNewLoadUrl(){
        String newUrl = loagUrl.substring(BaseUrl.BASEURL.length(), loagUrl.length());
        return newUrl;
    }


    private void initView() {
        lv = view.findViewById(R.id.pinglun_listview);
        tv1 = view.findViewById(R.id.pinglun_text);
    }

    @Override
    public void onSuccess(String json) {
        VideoBean videoBean = new Gson().fromJson(json, VideoBean.class);
        Toast.makeText(getActivity(),videoBean.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErr(int code, String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_SHORT).show();
    }
}
