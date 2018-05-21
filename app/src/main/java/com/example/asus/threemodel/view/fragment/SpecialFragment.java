package com.example.asus.threemodel.view.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.asus.threemodel.R;
import com.example.asus.threemodel.application.MyApplication;
import com.example.asus.threemodel.model.bean.SpecialBean;
import com.example.asus.threemodel.presenter.presenter.SpecialPresenter;
import com.example.asus.threemodel.view.inter.ISpecialView;

public class SpecialFragment extends BaseFragment<SpecialBean> implements ISpecialView{

    private RecyclerView rv;

    @Override
    int setChildView() {
        return R.layout.special_frament;
    }

    @Override
    void initView() {
        rv = getChildView().findViewById(R.id.special_rv);
        rv.setLayoutManager(new GridLayoutManager(MyApplication.getAppContext(),2));
    }

    @Override
    void initData() {
        new SpecialPresenter(this);
    }

    @Override
    void onSuccess(SpecialBean specialBean) {}


    @Override
    void onErr(int code, String errMsg) {}
}
