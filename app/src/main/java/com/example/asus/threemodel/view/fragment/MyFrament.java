package com.example.asus.threemodel.view.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.application.CommonUtil;
import com.example.asus.threemodel.view.activity.CollectActivity;
import com.example.asus.threemodel.view.activity.FitActivity;
import com.example.asus.threemodel.view.activity.MainActivity;
import com.example.asus.threemodel.view.adapter.ThemeAdapter;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

public class MyFrament extends Fragment implements View.OnClickListener{

    private View view;
    private ImageView my_shezhi;
    private RelativeLayout my_shezhi_lishi;
    private RelativeLayout my_shezhi_huancun;
    private RelativeLayout my_shezhi_shoucang;
    private RelativeLayout my_shezhi_zhuti;
    private View view1;

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

    public ArrayList<Integer> getColorData() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.color.colorBluePrimaryDark);
        integers.add(R.color.colorAccent);
        integers.add(R.color.colorTealPrimary);
        integers.add(R.color.colorDeepOrangePrimary);
        integers.add(R.color.colorRedPrimaryCenter);
        integers.add(R.color.colorRedPrimary);
        integers.add(R.color.colorPrimaryDark);
        integers.add(R.color.colorPrimary);
        integers.add(R.color.colorLimePrimaryCenter);
        integers.add(R.color.colorOrangePrimary);
        integers.add(R.color.colorSecondText);
        integers.add(R.color.colorLimePrimaryDark);
        integers.add(R.color.colorDeepPurplePrimaryCenter);
        integers.add(R.color.colorHint);
        integers.add(R.color.colorDeepOrangePrimaryCenter);
        integers.add(R.color.colorSecondText);

        return integers;
    }

    private int clickPosition = 0;

    public void showSelectThemes() {
        clickPosition = 0;
        final ArrayList<Integer> colorData = getColorData();
        view1 = View.inflate(getActivity(), R.layout.theme_view, null);
        GridView gridView = view1.findViewById(R.id.theme_gridView);
        final ThemeAdapter themeAdapter = new ThemeAdapter(getColorData(), 0, getActivity());
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                themeAdapter.setPosition(position);
                clickPosition = position;
                themeAdapter.notifyDataSetChanged();
            }
        });
        gridView.setAdapter(themeAdapter);

        new AlertDialog.Builder(getActivity())
                .setView(view1)
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int color = getResources().getColor(colorData.get(clickPosition));
                        StatusBarUtil.setColor(getActivity(), color);
                        if (view1 != null)
                            view1.setBackgroundColor(color);
                        CommonUtil.saveColorValue(color);
                    }
                })
                .create()
                .show();
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
                showSelectThemes();
                break;
        }
    }
}
