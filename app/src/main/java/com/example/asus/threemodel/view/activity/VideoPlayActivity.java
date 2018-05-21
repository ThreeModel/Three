package com.example.asus.threemodel.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;

import com.dl7.player.media.IjkPlayerView;
import com.example.asus.threemodel.R;
import com.example.asus.threemodel.presenter.presenter.BasePresenter;
import com.example.asus.threemodel.view.adapter.Main2VPAdapter;
import com.example.asus.threemodel.view.fragment.JianJieFragment;
import com.example.asus.threemodel.view.fragment.PingLunFragment;
import com.example.asus.threemodel.view.inter.TitleBarCallBack;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayActivity extends BaseActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();
    private List<String> tabs = new ArrayList<>();
    private String shareUrl;
    private String loadUrl;
    private String slt;
    private IjkPlayerView ijkPlayerView;
    private Uri mUri;

    @Override
    void initView() {
        tabLayout = findViewById(R.id.video_tab);
        viewPager = findViewById(R.id.video_viewpager);
        Intent intent = getIntent();
        shareUrl = intent.getStringExtra("shareUrl");
        loadUrl = intent.getStringExtra("loadUrl");
        slt = intent.getStringExtra("slt");
        if(!TextUtils.isEmpty(shareUrl) && !TextUtils.isEmpty(loadUrl)){
            initIJK();
        }
        return;
    }




    /**
     * 设置IJK播放器
     */
    private void initIJK() {
        ijkPlayerView = findViewById(R.id.video_ijk);
        mUri = Uri.parse(shareUrl);
        ijkPlayerView.init()
                .setVideoPath(mUri)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                .enableDanmaku()
                .start();
    }

    @Override
    void initData() {
        tv.setText("");
        JianJieFragment jianJieFragment = new JianJieFragment();
        PingLunFragment pingLunFragment = new PingLunFragment();
        fragments.add(jianJieFragment);
        fragments.add(pingLunFragment);
        tabs.add("简介");
        tabs.add("评论");
        Main2VPAdapter adapter = new Main2VPAdapter(getSupportFragmentManager(), VideoPlayActivity.this, fragments, tabs);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        /**
         * activity 与 fragment  传值
         */
        Bundle bundle = new Bundle();
        bundle.putString("loagUrl", loadUrl);
        jianJieFragment.setArguments(bundle);
        pingLunFragment.setArguments(bundle);
        jianJieFragment.setTitleBarString(new TitleBarCallBack() {
            @Override
            public void onTitleDataCallBackSuccess(String titleStr) {
                tv.setText(titleStr);
            }
        });
    }

    @Override
    View getChildView() {
        return View.inflate(this, R.layout.activity_video_play, null);
    }

    @Override
    BasePresenter setPresenter() {
        return null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        ijkPlayerView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ijkPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkPlayerView.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ijkPlayerView.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (ijkPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (ijkPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

}
