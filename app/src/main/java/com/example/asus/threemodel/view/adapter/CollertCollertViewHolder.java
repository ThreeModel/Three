package com.example.asus.threemodel.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.asus.threemodel.R;


/**
 *
 * 收藏页面 ViewHolder
 *
 */
public class CollertCollertViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;

    public CollertCollertViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.collert_item_img);
    }
}
