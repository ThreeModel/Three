package com.example.asus.threemodel.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.asus.threemodel.R;
import com.facebook.drawee.view.SimpleDraweeView;


public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {


    public SimpleDraweeView img;
    public TextView name;

    public MainRecyclerViewHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.main_recycler_img);
        name = itemView.findViewById(R.id.main_recycler_Videoname);
    }
}
