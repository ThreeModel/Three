package com.example.asus.threemodel.view.activity;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.BoonBean;
import com.example.asus.threemodel.presenter.presenter.BasePresenter;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.adapter.BoonRecyclerAdapter;
import com.example.asus.threemodel.view.inter.IMainView;
import com.example.asus.threemodel.view.tools.BaseUrl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 福利页面
 */
public class BoonActivity extends BaseActivity implements IMainView {

    private RecyclerView recyclerView;
    private String boon = "福利/10/1";
    private List<String> imgs = new ArrayList<>();

    @Override
    void initView() {
        tv.setText("福利");
        recyclerView = findViewById(R.id.boon_recycler);
        RecyclerView.LayoutManager manager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    void initData() {
        // 去网络请求数据
        MainPresenter mainPresenter = (MainPresenter) getPresenter();
        mainPresenter.getJson(BaseUrl.BOON + boon);
    }

    @Override
    View getChildView() {
        return View.inflate(BoonActivity.this, R.layout.activity_boon, null);
    }

    @Override
    BasePresenter setPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void onSuccess(String data) {
        BoonBean boonBean = new Gson().fromJson(data, BoonBean.class);
        for (int i = 0; i < boonBean.getResults().size(); i++) {
            if (!TextUtils.isEmpty(boonBean.getResults().get(i).getUrl().toString())) {
                imgs.add(boonBean.getResults().get(i).getUrl().toString());
            }
        }
        BoonRecyclerAdapter adapter = new BoonRecyclerAdapter(BoonActivity.this, imgs);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onErr(int code, String err) {
        Toast.makeText(BoonActivity.this, "" + err, Toast.LENGTH_SHORT).show();
    }
}
