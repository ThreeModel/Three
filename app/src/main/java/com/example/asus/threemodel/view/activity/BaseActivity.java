package com.example.asus.threemodel.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.presenter.presenter.BasePresenter;
import com.example.asus.threemodel.view.inter.IBaseView;
import com.example.asus.threemodel.view.tools.CommUtils;
import com.gyf.barlibrary.ImmersionBar;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView, View.OnClickListener {

    private P presenter;
    private FrameLayout fl;

    public RelativeLayout base_title;  //标题名称
    public ImageView back; //返回键
    public TextView caler;  //清空收藏键
    public ImageView collert; //收藏按钮
    public TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBaseActivityView());
        getSupportActionBar().hide();
        ImmersionBar.with(this).init();
        initSelfView();
        presenter = setPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        initView();
        initData();
    }

    /**
     * @return
     */
    public View getBaseActivityView() {
        return View.inflate(this, R.layout.activity_base, null);
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
        base_title = findViewById(R.id.base_title);
        tv = findViewById(R.id.base_text);
        back = findViewById(R.id.base_back);
        back.setOnClickListener(this);
        caler = findViewById(R.id.base_caler);
        collert = findViewById(R.id.base_collert);
        collert.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_back:
                finish();
                CommUtils.backAnim(BaseActivity.this);
                break;
            case R.id.base_collert:
                // 收藏按钮
                Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        ImmersionBar.with(this).destroy(); //必须调用该方法，防止内存泄漏
    }

}
