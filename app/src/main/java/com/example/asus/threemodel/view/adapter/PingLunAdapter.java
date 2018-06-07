package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.PingLunBean;

import java.util.List;

public class PingLunAdapter extends BaseAdapter {

    List<PingLunBean.RetBean.ListBean> list;
    Context context;

    public PingLunAdapter(List<PingLunBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.pinglun_lv_item, null);
            vh.userName = convertView.findViewById(R.id.pl_name);
            vh.publishDate = convertView.findViewById(R.id.pl_date);
            vh.publishContent = convertView.findViewById(R.id.pl_content);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.userName.setText(list.get(position).getPhoneNumber());
        vh.publishDate.setText(list.get(position).getTime());
        vh.publishContent.setText(list.get(position).getMsg());
        return convertView;
    }

    class ViewHolder {
        TextView userName;
        TextView publishDate;
        TextView publishContent;
    }
}
