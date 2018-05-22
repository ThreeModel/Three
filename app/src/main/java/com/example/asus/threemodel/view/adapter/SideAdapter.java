package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.threemodel.R;

import java.util.List;

public class SideAdapter extends BaseAdapter {

    private List<Integer> imgs;
    private List<String> texts;
    private Context context;

    public SideAdapter(List<Integer> imgs, List<String> texts, Context context) {
        this.imgs = imgs;
        this.texts = texts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return texts.size();
    }

    @Override
    public Object getItem(int position) {
        return texts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null){
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.side_listview_item,null);
            vh.imageView = convertView.findViewById(R.id.side_lv_item_img);
            vh.textView = convertView.findViewById(R.id.side_lv_item_text);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(imgs.get(position)).into(vh.imageView);
        vh.textView.setText(texts.get(position).toString());
        return convertView;
    }

}

class ViewHolder {
    ImageView imageView;
    TextView textView;
}
