package com.example.asus.threemodel.view.activity;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.fragment.ChoicenessFragment;
import com.example.asus.threemodel.view.fragment.DiscoverFrament;
import com.example.asus.threemodel.view.fragment.MyFrament;
import com.example.asus.threemodel.view.fragment.SpecialFragment;
import com.example.asus.threemodel.view.inter.IMainView;
import com.hjm.bottomtabbar.BottomTabBar;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView {


    @Override
    void initView() {
        BottomTabBar btb = findViewById(R.id.btb);
        btb.init(getSupportFragmentManager())
                .setImgSize(60, 60)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.GRAY)
                .addTabItem("精选", R.drawable.found_select,R.drawable.found,ChoicenessFragment.class)
                .addTabItem("专题",R.drawable.special_select,R.drawable.special,SpecialFragment.class)
                .addTabItem("发现",R.drawable.fancy_select,R.drawable.fancy,DiscoverFrament.class)
                .addTabItem("我的",R.drawable.my_select,R.drawable.my,MyFrament.class)
                .isShowDivider(true)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                            tv.setText(name);
                    }
                });
    }





    @Override
    void initData() {
    }

    @Override
    View getChildView() {
        Log.e( "getChildView: ","hahaha" );
        return View.inflate(this,R.layout.activity_main,null);
    }

    @Override
    MainPresenter setPresenter() {
        return new MainPresenter(this);
    }




    @Override
    public void onSuccess(String mainBean) {

    }

    @Override
    public void onErr(int code, String errMsg) {

    }

}
