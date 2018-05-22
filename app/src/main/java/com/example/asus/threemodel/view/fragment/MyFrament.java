package com.example.asus.threemodel.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.view.activity.CollectActivity;
import com.example.asus.threemodel.view.activity.FitActivity;

public class MyFrament extends Fragment implements View.OnClickListener{

    private View view;
    private ImageView my_shezhi;
    private RelativeLayout my_shezhi_lishi;
    private RelativeLayout my_shezhi_huancun;
    private RelativeLayout my_shezhi_shoucang;
    private RelativeLayout my_shezhi_zhuti;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.my_frament, null);
        initview();
        return view;

    }

    private void initview() {
        my_shezhi = view.findViewById(R.id.my_shezhi);
        my_shezhi_lishi = view.findViewById(R.id.my_shezhi_lishi);
        my_shezhi_huancun = view.findViewById(R.id.my_shezhi_huancun);
        my_shezhi_shoucang = view.findViewById(R.id.my_shezhi_shoucang);
        my_shezhi_zhuti = view.findViewById(R.id.my_shezhi_zhuti);
        my_shezhi.setOnClickListener(this);
        my_shezhi_lishi.setOnClickListener(this);
        my_shezhi_huancun.setOnClickListener(this);
        my_shezhi_shoucang.setOnClickListener(this);
        my_shezhi_zhuti.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {

            case R.id.my_shezhi:
                Toast.makeText(getContext(), "设置",Toast.LENGTH_LONG).show();
                //点击设置的时候跳转到一个设置的acivity
                Intent intent=new Intent(getContext(), FitActivity.class);
                startActivity(intent);
                break;
            case R.id.my_shezhi_lishi:
                Toast.makeText(getContext(), "历史",Toast.LENGTH_LONG).show();
                break;
            case R.id.my_shezhi_huancun  :
                Toast.makeText(getContext(), "静请期待",Toast.LENGTH_LONG).show();
                break;
            case R.id.my_shezhi_shoucang:
               //点击跳转，跳转到一个收藏界面
                Toast.makeText(getContext(), "aaaaa",Toast.LENGTH_LONG).show();
Intent intent1=new Intent(getActivity(),CollectActivity.class);
startActivity(intent1);
                break;
            case R.id.my_shezhi_zhuti:
                Toast.makeText(getContext(), "主题",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
