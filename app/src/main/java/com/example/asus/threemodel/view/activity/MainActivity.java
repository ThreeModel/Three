package com.example.asus.threemodel.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.application.CommonUtil;
import com.example.asus.threemodel.presenter.presenter.MainPresenter;
import com.example.asus.threemodel.view.adapter.SideAdapter;
import com.example.asus.threemodel.view.adapter.ThemeAdapter;
import com.example.asus.threemodel.view.fragment.ChoicenessFragment;
import com.example.asus.threemodel.view.fragment.DiscoverFrament;
import com.example.asus.threemodel.view.fragment.MyFrament;
import com.example.asus.threemodel.view.fragment.SpecialFragment;
import com.example.asus.threemodel.view.inter.IMainView;
import com.hjm.bottomtabbar.BottomTabBar;
import com.jaeger.library.StatusBarUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> implements IMainView, AdapterView.OnItemClickListener,View.OnClickListener {


    private List<Integer> sideImgs = new ArrayList<>();
    private List<String> sideTexts = new ArrayList<>();
    private RoundedImageView roundedImageView;
    private TextView photoText;
    private ListView photoListView;
    private LinearLayout guanyu;
    private LinearLayout zhuti;
    private View inflate;

    @Override
    void initView() {
        // 侧滑菜单的圆形头像图，个性签名，列表，关于，主题
        roundedImageView = findViewById(R.id.photo);
        photoText = findViewById(R.id.photo_text);
        photoListView = findViewById(R.id.photo_listview);
        guanyu = findViewById(R.id.guanyu);
        guanyu.setOnClickListener(new View.OnClickListener() {

            private AlertDialog alertDialog;
            private TextView text_guanbi;

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View view = View.inflate(MainActivity.this, R.layout.shezhi_guanyu, null);
                builder.setView(view);
                text_guanbi = view.findViewById(R.id.text_guanbi);
                text_guanbi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                        Toast.makeText(MainActivity.this, "关于",Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();

            }
        });
        zhuti = findViewById(R.id.zhuti);
        zhuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectThemes();
            }
        });
        back.setVisibility(View.GONE); // 基类中的返回键   在主页面不需要  所以设置成不可见状态
        BottomTabBar btb = findViewById(R.id.btb);
        btb.init(getSupportFragmentManager())
                .setImgSize(60, 60)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.GRAY)
                .addTabItem("精选", R.drawable.found_select, R.drawable.found, ChoicenessFragment.class)
                .addTabItem("专题", R.drawable.special_select, R.drawable.special, SpecialFragment.class)
                .addTabItem("发现", R.drawable.fancy_select, R.drawable.fancy, DiscoverFrament.class)
                .addTabItem("我的", R.drawable.my_select, R.drawable.my, MyFrament.class)
                .isShowDivider(true)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                        tv.setText(name);
                    }
                });
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
        View view = View.inflate(this, R.layout.theme_view, null);
        GridView gridView = view.findViewById(R.id.theme_gridView);
        final ThemeAdapter themeAdapter = new ThemeAdapter(getColorData(), 0, this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                themeAdapter.setPosition(position);
                clickPosition = position;
                themeAdapter.notifyDataSetChanged();
            }
        });
        gridView.setAdapter(themeAdapter);

        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int color = getResources().getColor(colorData.get(clickPosition));
                        StatusBarUtil.setColor(MainActivity.this, color);
                        if (inflate != null)
                            inflate.setBackgroundColor(color);
                        CommonUtil.saveColorValue(color);
                    }
                })
                .create()
                .show();
    }


    @Override
    void initData() {
        initSideLvData();
    }

    /**
     * 设置  侧滑布局 listview 的数据
     */
    private void initSideLvData() {
        // 初始化侧滑菜单
        sideImgs.add(R.mipmap.enshrine);
        sideImgs.add(R.mipmap.download);
        sideImgs.add(R.mipmap.boon);
        sideImgs.add(R.mipmap.share);
        sideImgs.add(R.mipmap.feedback);
        sideImgs.add(R.mipmap.settings);
        sideTexts.add("我的收藏");
        sideTexts.add("我的下载");
        sideTexts.add("福利");
        sideTexts.add("分享");
        sideTexts.add("建议反馈");
        sideTexts.add("设置");
        SideAdapter adapter = new SideAdapter(sideImgs, sideTexts, MainActivity.this);
        photoListView.setAdapter(adapter);
        photoListView.setOnItemClickListener(this);
    }

    @Override
    View getChildView() {
        inflate = View.inflate(this, R.layout.activity_main, null);
        return inflate;
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        if ("我的收藏".equals(sideTexts.get(position))) {
            intent.setClass(this, CollectActivity.class);
            startActivity(intent);
        } else if ("我的下载".equals(sideTexts.get(position))) {
            Toast.makeText(MainActivity.this, "敬请期待", Toast.LENGTH_SHORT).show();
        } else if ("福利".equals(sideTexts.get(position))) {
            intent.setClass(this, BoonActivity.class);
            startActivity(intent);
        } else if ("分享".equals(sideTexts.get(position))) {
            Toast.makeText(MainActivity.this, "开发中，敬请期待", Toast.LENGTH_SHORT).show();
        } else if ("建议反馈".equals(sideTexts.get(position))) {
            Toast.makeText(MainActivity.this, "开发中，敬请期待", Toast.LENGTH_SHORT).show();
        } else if ("设置".equals(sideTexts.get(position))) {
            Toast.makeText(MainActivity.this, "开发中，敬请期待", Toast.LENGTH_SHORT).show();
        }
    }
}
