package com.example.asus.threemodel.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.threemodel.R;


public class MainRecyclerViewHolder extends RecyclerView.ViewHolder {


    public ImageView img;
    public TextView name;

    public MainRecyclerViewHolder(View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.main_recycler_img);
        name = itemView.findViewById(R.id.main_recycler_Videoname);
    }
}
