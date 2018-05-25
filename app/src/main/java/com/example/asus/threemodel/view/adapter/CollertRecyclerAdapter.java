package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;
import com.example.asus.threemodel.view.activity.VideoPlayActivity;

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
    public void onBindViewHolder(@NonNull CollertCollertViewHolder holder, final int position) {
        Glide.with(context).load(childListBeans.get(position).getPic().toString()).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlayActivity.class);
                intent.putExtra("loadUrl",childListBeans.get(position).getLoadURL().toString());
                intent.putExtra("shareUrl",childListBeans.get(position).getShareURL().toString());
                intent.putExtra("slt",childListBeans.get(position).getPic().toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return childListBeans == null ? 0 : childListBeans.size();
    }
}
