package com.example.asus.threemodel.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.model.bean.VideoBean;
import com.example.asus.threemodel.view.activity.MoreActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SpecialAdapter extends BaseAdapter {

    List<VideoBean.RetBean.ListBean> listBeans;
    Context context;

    public SpecialAdapter(List<VideoBean.RetBean.ListBean> listBeans, Context context) {
        this.listBeans = listBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private List<VideoBean.RetBean.ListBean.ChildListBean> childListBeans = new ArrayList<>();

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        for (int i = 0; i < listBeans.get(position).getChildList().size(); i++) {
            childListBeans.add(listBeans.get(position).getChildList().get(i));
        }
        ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.special_gridview_item, null);
            vh.simpleDraweeView = convertView.findViewById(R.id.special_fresco);
            vh.textView = convertView.findViewById(R.id.special_text);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (childListBeans != null && !TextUtils.isEmpty(listBeans.get(position).getTitle())) {
            vh.simpleDraweeView.setImageURI(Uri.parse(childListBeans.get(position).getPic().toString()));
            vh.textView.setText(listBeans.get(position).getTitle().toString());
        } else {
            vh.simpleDraweeView.setImageURI(Uri.parse(childListBeans.get(position).getPic().toString()));
            vh.textView.setText("没有标题");
        }

        // 条目点击  需要走一个集合 果果穿不过去  那就麻烦了  卧槽
        vh.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                childListBeans.clear();
                for (int i = 0; i < listBeans.get(position).getChildList().size(); i++) {
                    childListBeans.add(listBeans.get(position).getChildList().get(i));
                }

                // 使用 Bundle  传递集合
                Bundle bundle = new Bundle();
                // 必须实现序列化集合
                bundle.putSerializable("list", (Serializable) childListBeans);

                Intent intent = new Intent(context, MoreActivity.class);
                intent.putExtra("title", listBeans.get(position).getTitle().toString());
                intent.putExtra("bundle",bundle);
                context.startActivity(intent);

            }
        });

        return convertView;
    }

    /**
     * gridview 优化
     */
    class ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textView;
    }
}

