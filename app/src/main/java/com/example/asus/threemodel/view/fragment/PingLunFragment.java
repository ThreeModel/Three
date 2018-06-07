package com.example.asus.threemodel.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.PingLunBean;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.adapter.PingLunAdapter;
import com.example.asus.threemodel.view.inter.IMainView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PingLunFragment extends Fragment implements IMainView {

    private View view;
    private ListView lv;
    private String video_id;
    private List<PingLunBean.RetBean.ListBean> listBeans = new ArrayList<>();

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
        video_id = bundle.getString("video_ID");   //  拿到视频的ID  获取指定的视频评论
        MainPresenter persenter = new MainPresenter(this);
        persenter.getJson("http://api.svipmovie.com/front/Commentary/getCommentList.do?mediaId=" + video_id);
    }


    private void initView() {
        lv = view.findViewById(R.id.pinglun_listview);
    }

    @Override
    public void onSuccess(String json) {

        PingLunBean pingLunBean = new Gson().fromJson(json, PingLunBean.class);
        for (int i = 0; i < pingLunBean.getRet().getList().size(); i++) {
            listBeans.add(pingLunBean.getRet().getList().get(i));
        }
        PingLunAdapter adapter = new PingLunAdapter(listBeans,getActivity());
        lv.setAdapter(adapter);
    }

    @Override
    public void onErr(int code, String errMsg) {
        Toast.makeText(getActivity(), errMsg, Toast.LENGTH_SHORT).show();
    }
}
