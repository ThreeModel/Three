package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;

import java.util.List;

public class CollertRecyclerAdapter extends RecyclerView.Adapter<CollertCollertViewHolder> {

    private Context context;
    private List<VideoBean.RetBean.ListBean.ChildListBean> childListBeans;

    public CollertRecyclerAdapter(Context context, List<VideoBean.RetBean.ListBean.ChildListBean> childListBeans) {
        this.context = context;
        this.childListBeans = childListBeans;
    }

    @NonNull
    @Override
    public CollertCollertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CollertCollertViewHolder viewHolder = new CollertCollertViewHolder(View.inflate(context, R.layout.collert_item, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollertCollertViewHolder holder, int position) {
        Glide.with(context).load(childListBeans.get(position).getPic().toString()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return childListBeans == null ? 0 : childListBeans.size();
    }
}
