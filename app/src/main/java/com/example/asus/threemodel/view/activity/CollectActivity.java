package com.example.asus.threemodel.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;
import com.example.asus.threemodel.presenter.presenter.BasePresenter;
import com.example.asus.threemodel.view.adapter.CollertRecyclerAdapter;
import com.example.asus.threemodel.view.tools.CommUtils;

import java.util.List;

public class CollectActivity extends BaseActivity {

    private RecyclerView recycler;

    @Override
    void initView() {
        tv.setText("收藏"); //基类中的标题设置为   收藏
        caler.setVisibility(View.VISIBLE);
        recycler = findViewById(R.id.collert_recycler);
        // 收藏页面列表条目
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2, OrientationHelper.VERTICAL, false);
        recycler.setLayoutManager(manager);
        caler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommUtils.calerCollert();  //清除全部收藏的数据
            }
        });
        // 拿到收藏的视频
        List<VideoBean.RetBean.ListBean.ChildListBean> childListBeans = CommUtils.getCollectVideo();
        CollertRecyclerAdapter adapter = new CollertRecyclerAdapter(CollectActivity.this, childListBeans);
        recycler.setAdapter(adapter);
    }

    @Override
    void initData() {
    }

    @Override
    View getChildView() {
        return View.inflate(this, R.layout.activity_collert, null);
    }

    @Override
    BasePresenter setPresenter() {
        return null;
    }


}
