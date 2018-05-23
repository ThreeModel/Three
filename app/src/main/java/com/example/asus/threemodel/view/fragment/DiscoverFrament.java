package com.example.asus.threemodel.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.threemodel.R;
import com.example.asus.threemodel.view.costom.SwipeFlingAdapterView;
import com.example.asus.threemodel.view.inter.IMainView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class DiscoverFrament extends Fragment implements SwipeFlingAdapterView.onFlingListener,

        SwipeFlingAdapterView.OnItemClickListener, View.OnClickListener,IMainView {

    int[] headerIcons = {
            R.drawable.i1,
            R.drawable.i2,
            R.drawable.i3,
            R.drawable.i4,
            R.drawable.i5,
            R.drawable.i6
    };
    Random ran = new Random();
    private int cardWidth;
    private int cardHeight;
    private SwipeFlingAdapterView swipeView;
    private InnerAdapter adapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getContext(), R.layout.discover_fragment, null);
        initView();

        loadData();
        return view;
    }
    private void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 18 * density));
        cardHeight = (int) (dm.heightPixels - (338 * density));
        swipeView = (SwipeFlingAdapterView) view.findViewById(R.id.swipe_view);
        if (swipeView != null) {
            swipeView.setIsNeedSwipe(true);
            swipeView.setFlingListener(this);
            swipeView.setOnItemClickListener(this);
            adapter = new InnerAdapter();
            swipeView.setAdapter(adapter);
        }

       View v = view.findViewById(R.id.swipeRight);
        if (v != null) {
            v.setOnClickListener(this);
        }
    }
    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {

    }
    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }
    @Override
    public void onLeftCardExit(Object dataObject) {

    }
    @Override
    public void onRightCardExit(Object dataObject) {

    }
    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            loadData();
        }
    }
    @Override
    public void onScroll(float progress, float scrollXProgress) {

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.swipeRight:
                swipeView.swipeRight();
                //swipeView.swipeRight(250);
        }
    }



    private void loadData() {
        new AsyncTask<Void, Void, List<Talent>>() {
            @Override
            protected List<Talent> doInBackground(Void... params) {
                ArrayList<Talent> list = new ArrayList<>(10);
                Talent talent;
                for (int i = 0; i < 10; i++) {
                    talent = new Talent();
                    talent.headerIcon = headerIcons[i % headerIcons.length];
                    list.add(talent);
                }
                return list;
            }
            @Override
            protected void onPostExecute(List<Talent> list) {
                super.onPostExecute(list);
                adapter.addAll(list);
            }
        }.execute();
    }

    @Override
    public void onSuccess(String bean) {

    }

    @Override
    public void onErr(int code, String errMsg) {

    }

    private class InnerAdapter extends BaseAdapter {
        ArrayList<Talent> objs;
        public InnerAdapter() {
            objs = new ArrayList<>();
        }
        public void addAll(Collection<Talent> collection) {
            if (isEmpty()) {
                objs.addAll(collection);
                notifyDataSetChanged();
            } else {
                objs.addAll(collection);
            }
        }
        public void clear() {
            objs.clear();
            notifyDataSetChanged();
        }
        public boolean isEmpty() {
            return objs.isEmpty();
        }
        public void remove(int index) {
            if (index > -1 && index < objs.size()) {
                objs.remove(index);
                notifyDataSetChanged();
            }
        }
        @Override
        public int getCount() {
            return objs.size();
        }
        @Override
        public Talent getItem(int position) {
            if(objs==null ||objs.size()==0) return null;
            return objs.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        // TODO: getView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            Talent talent = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_new_item, parent, false);
                holder  = new ViewHolder();
                convertView.setTag(holder);
                convertView.getLayoutParams().width = cardWidth;
                holder.portraitView = (ImageView) convertView.findViewById(R.id.portrait);
                holder.portraitView.getLayoutParams().height = cardHeight;

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.portraitView.setImageResource(talent.headerIcon);
            //holder.jobView.setText(talent.jobName);
            final CharSequence no = "暂无";
            return convertView;
        }
    }
    private static class ViewHolder {
        ImageView portraitView;
    }
    public static class Talent {
        public int headerIcon;
    }
}
