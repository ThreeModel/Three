package com.example.asus.threemodel.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.activity.VideoPlayActivity;
import com.example.asus.threemodel.view.adapter.VideoGridViewAdapter;
import com.example.asus.threemodel.view.costom.MyGridView;
import com.example.asus.threemodel.view.costom.StretchyTextView;
import com.example.asus.threemodel.view.inter.IMainView;
import com.example.asus.threemodel.view.inter.TitleBarCallBack;
import com.example.asus.threemodel.view.tools.BaseUrl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JianJieFragment extends Fragment implements IMainView {

    private View view;
    private MyGridView lv;
    private StretchyTextView stretchyTextView;
    private List<VideoBean.RetBean.ListBean.ChildListBean> childListBeans = new ArrayList<>();
    private String loagUrl;
    private TitleBarCallBack titleBarCallBack;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.jianjie, null);
        initView();
        initData();

        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        loagUrl = bundle.getString("loagUrl");
        MainPresenter persenter = new MainPresenter(this);
        persenter.getJson(getNewLoadUrl());



    }

    private String getNewLoadUrl() {
        String newUrl = loagUrl.substring(BaseUrl.BASEURL.length(), loagUrl.length());
        return newUrl;
    }

    private void initView() {
        lv = view.findViewById(R.id.jianjie_gridview);
        stretchyTextView = view.findViewById(R.id.jianjie_textview);
        stretchyTextView.setMaxLineCount(3);
        stretchyTextView.setBottomTextGravity(Gravity.CENTER_VERTICAL);
        stretchyTextView.setContentTextColor(Color.WHITE);
    }

    @Override
    public void onSuccess(String json) {
        childListBeans.clear();
        VideoBean bean = new Gson().fromJson(json, VideoBean.class);
        List<VideoBean.RetBean.ListBean> list = bean.getRet().getList();

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getShowType().equals("vertical")) {
                for (int j = 0; j < list.get(i).getChildList().size(); j++) {
                    childListBeans.add(list.get(i).getChildList().get(j));
                }
            }
        }


        titleBarCallBack.onTitleDataCallBackSuccess(bean.getRet().getTitle().toString());

        stretchyTextView.setContent(bean.getRet().getDescription());
        VideoGridViewAdapter adapter = new VideoGridViewAdapter(getActivity(), childListBeans);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                intent.putExtra("loadUrl", childListBeans.get(position).getLoadURL().toString());
                intent.putExtra("shareUrl", childListBeans.get(position).getShareURL().toString());
                intent.putExtra("slt", childListBeans.get(position).getPic().toString());
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
    }

    @Override
    public void onErr(int code, String errMsg) {

    }

    public void setTitleBarString(TitleBarCallBack titleBarString){
        this.titleBarCallBack = titleBarString;
    }

}
