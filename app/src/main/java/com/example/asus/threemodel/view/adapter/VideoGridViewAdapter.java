package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class VideoGridViewAdapter extends BaseAdapter {

    Context context;
    List<VideoBean.RetBean.ListBean.ChildListBean> childList;

    public VideoGridViewAdapter(Context context, List<VideoBean.RetBean.ListBean.ChildListBean> childList) {
        this.context = context;
        this.childList = childList;
    }


    @Override
    public int getCount() {
        return childList.size();
    }

    @Override
    public Object getItem(int position) {
        return childList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GridViewViewHolder vh ;
        if(convertView == null){
            vh = new GridViewViewHolder();
            convertView = View.inflate(context, R.layout.video_gridview_item,null);
            vh.imageView = convertView.findViewById(R.id.video_gridview_fresco);
            vh.textView = convertView.findViewById(R.id.gridview_text_item);
            convertView.setTag(vh);
        }else{
            vh = (GridViewViewHolder) convertView.getTag();
        }
        vh.textView.setText(childList.get(position).getTitle());
        vh.imageView.setImageURI(Uri.parse(childList.get(position).getPic()));
        return convertView;
    }

    class GridViewViewHolder{
        SimpleDraweeView imageView;
        TextView textView;
    }

}
