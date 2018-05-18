package com.example.asus.threemodel.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.ResultBean;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.adapter.MainRecyclerAdapter;
import com.example.asus.threemodel.view.adapter.RecyclerViewItemClickListener;
import com.example.asus.threemodel.view.costom.BannerImageLoder;
import com.example.asus.threemodel.view.costom.ObservableScrollView;
import com.example.asus.threemodel.view.inter.IMainView;
import com.gongwen.marqueen.SimpleMF;
import com.gongwen.marqueen.SimpleMarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.inflate;

public class ChoicenessFragment extends Fragment implements View.OnClickListener, IMainView<ResultBean> {


    private View view;
    private Banner banner;
    private RecyclerView recyclerView;
    private List<String> datas = new ArrayList<>();
    private List<String> banners = new ArrayList<>();
    private SimpleMarqueeView<String> stringSimpleMarqueeView;
    private List<ResultBean.RetBean.ListBean.ChildListBean> recyclers = new ArrayList<>();
    private RelativeLayout relativeLayout;
    private ObservableScrollView observableScrollView;
    private final String TAG_MARGIN_ADDED = "marginAdded";
    private TextView jingxuan_title;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflate(getActivity(), R.layout.jingxuan, null);
        initView();
        initData();
        initTabBar();
        return view;
    }

    private void initData() {
        MainPresenter presenter = new MainPresenter();
        presenter.getJson("front/homePageApi/homePage.do");
    }

    private void initView() {
        relativeLayout = view.findViewById(R.id.jingxuan_titleLayout); //标题
        relativeLayout.setBackgroundColor(Color.argb((int) 0, 144, 151, 166));  //设置标题栏为透明的

        jingxuan_title = view.findViewById(R.id.jingxuan_title);
        jingxuan_title.setText("");

        observableScrollView = view.findViewById(R.id.jingxuan_scroll); //自定义ScrollView
        banner = view.findViewById(R.id.banner);  //轮播图
        stringSimpleMarqueeView = view.findViewById(R.id.simpleMarqueeView); //跑马灯
        recyclerView = view.findViewById(R.id.recycler);  //数据展示
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), OrientationHelper.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setNestedScrollingEnabled(false);
        banner.setBannerStyle(BannerConfig.NUM_INDICATOR);
        banner.setImageLoader(new BannerImageLoder());
        banner.isAutoPlay(true);
    }

    /**
     * 状态栏
     */
    private void initTabBar() {
        ViewTreeObserver viewTreeObserver = relativeLayout.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                relativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                final int height = relativeLayout.getHeight();
                observableScrollView.setScrollViewListener(new ObservableScrollView.IScrollViewListener() {
                    @Override
                    public void onScrollChanged(int l, int t, int oldl, int oldt) {
                        float scale = (float) t / height;
                        float alpha = (255 * scale);
                        if (t <= 0) {
                            jingxuan_title.setText("");
                        } else if (t > 0 && t < height) {
                            // 只是layout背景透明(仿知乎滑动效果)
                            relativeLayout.setBackgroundColor(Color.argb((int) alpha, 144, (int) alpha, 166));
                        } else {
                            jingxuan_title.setText("精选");
                            relativeLayout.setBackgroundColor(Color.argb(255, 144, 255, 166));
                        }
                    }
                });
            }
        });
    }


    /**
     * @param resultBean
     */
    @Override
    public void onSuccess(ResultBean resultBean) {
        /**
         * 搜索展示  还要进行点击进行搜索任务
         */
        List<ResultBean.RetBean.ListBean> list = resultBean.getRet().getList();
        for (int i = 0; i < list.size(); i++) {
            List<ResultBean.RetBean.ListBean.ChildListBean> childList = resultBean.getRet().getList().get(i).getChildList();
            for (int j = 0; j < childList.size(); j++) {

                if (resultBean.getRet().getList().get(i).getShowType().toString().equals("banner")) {
                    if (!TextUtils.isEmpty(childList.get(j).getPic().toString())) {
                        banners.add(childList.get(j).getPic().toString());
                        datas.add(childList.get(j).getTitle().toString());
                    }
                }

                if (resultBean.getRet().getList().get(i).getShowType().equals("IN")) {
                    recyclers.add(list.get(i).getChildList().get(j));
                }
            }
        }
        // 轮播图  开始轮播图片
        banner.setImages(banners);
        banner.start();

        // 跑马灯  开始循环文字
        SimpleMF<String> marqueeFactory = new SimpleMF(getActivity());
        marqueeFactory.setData(datas);
        stringSimpleMarqueeView.setMarqueeFactory(marqueeFactory);
        stringSimpleMarqueeView.startFlipping();

        //  列表展示数据
        MainRecyclerAdapter adapter = new MainRecyclerAdapter(getActivity(), recyclers);
        recyclerView.setAdapter(adapter);
        adapter.setRecyclerClickListener(new RecyclerViewItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
            }
        });
    }


    @Override
    public void onClick(View v) {
    }

    @Override
    public void onErr(int code, String errMsg) {
        Toast.makeText(getActivity(),errMsg,Toast.LENGTH_SHORT).show();
    }
}
