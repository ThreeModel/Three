package com.example.asus.threemodel.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;
import com.example.asus.threemodel.presenter.presenter.BasePresenter;
import com.example.asus.threemodel.view.adapter.CollertRecyclerAdapter;

import java.util.List;

public class MoreActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<VideoBean.RetBean.ListBean.ChildListBean> childListBeans;

    @Override
    void initView() {
        recyclerView = findViewById(R.id.more_recycler);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    void initData() {
        // 拿到值做展示  不需要获取
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        Bundle bundle = intent.getBundleExtra("bundle");
        childListBeans = (List<VideoBean.RetBean.ListBean.ChildListBean>) bundle.getSerializable("list");
        tv.setText(title);
        CollertRecyclerAdapter adapter = new CollertRecyclerAdapter(this,childListBeans);
        recyclerView.setAdapter(adapter);
    }

    @Override
    View getChildView() {
        return View.inflate(this, R.layout.activity_more, null);
    }

    @Override
    BasePresenter setPresenter() {
        return null;
    }

}
