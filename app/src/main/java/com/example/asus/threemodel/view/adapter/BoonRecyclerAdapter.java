package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.asus.threemodel.R;

import java.util.List;

public class BoonRecyclerAdapter extends RecyclerView.Adapter<BoonViewHolder> {

    Context context;
    List<String> imgs;

    public BoonRecyclerAdapter(Context context, List<String> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @NonNull
    @Override
    public BoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BoonViewHolder viewHolder = new BoonViewHolder(View.inflate(context, R.layout.boon_item,null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoonViewHolder holder, int position) {
        Glide.with(context).load(imgs.get(position).toString()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imgs == null ? 0 :imgs.size();
    }
}
