package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.ResultBean;

import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerViewHolder> {

    Context context;

    List<ResultBean.RetBean.ListBean.ChildListBean> list;

    public MainRecyclerAdapter(Context context, List<ResultBean.RetBean.ListBean.ChildListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.mainrecycler_item, null);
        MainRecyclerViewHolder viewHolder = new MainRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainRecyclerViewHolder holder, final int position) {
        holder.name.setText(list.get(position).getTitle());
        holder.img.setImageURI(Uri.parse(list.get(position).getPic()));
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewItemClickListener.onRecyclerItemClick(position);
            }
        });
    }

    RecyclerViewItemClickListener recyclerViewItemClickListener;

    public void setRecyclerClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
