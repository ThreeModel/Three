package com.example.asus.threemodel.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.threemodel.R;

public class BoonViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public BoonViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.boon_item);
    }
}
