package com.example.asus.threemodel.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.presenter.presenter.BasePresenter;
import com.example.asus.threemodel.view.inter.IBaseView;
import com.example.asus.threemodel.view.tools.CommUtils;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView, View.OnClickListener {

    private P presenter;
    private FrameLayout fl;

    public TextView tv;  //标题名称
    public ImageView back; //返回键
    public TextView caler;  //清空收藏键
    public ImageView collert; //收藏按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initSelfView();
        presenter = setPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        initView();
        initData();
    }

    public P getPresenter() {
        return presenter;
    }

    abstract void initView();

    abstract void initData();

    abstract View getChildView();

    abstract P setPresenter();


    private void initSelfView() {
        fl = findViewById(R.id.base_frameLayout);
        fl.addView(getChildView());
        tv = findViewById(R.id.base_title);
        back = findViewById(R.id.base_back);
        back.setOnClickListener(this);
        caler = findViewById(R.id.base_caler);
        collert = findViewById(R.id.base_collert);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_back:
                finish();
                CommUtils.backAnim(BaseActivity.this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }

    }

}
