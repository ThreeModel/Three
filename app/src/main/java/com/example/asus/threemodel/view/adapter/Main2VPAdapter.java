package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class Main2VPAdapter extends FragmentPagerAdapter {

    Context context;
    List<Fragment> fragments;
    List<String> titles;

    public Main2VPAdapter(FragmentManager fm, Context context, List<Fragment> list,List<String> titles) {
        super(fm);
        this.context = context;
        this.fragments = list;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
