package com.example.asus.threemodel.view.costom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {

    private static final String TAG = "ObservableScrollView";

    public interface IScrollViewListener{
        void  onScrollChanged(int l, int t, int oldl, int oldt);
    }
    IScrollViewListener mScrollViewListener;

    public void setScrollViewListener(IScrollViewListener listener){
        this.mScrollViewListener=listener;
    }


    public ObservableScrollView(Context context) {
        this(context, null);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mScrollViewListener != null) {
            mScrollViewListener.onScrollChanged(x, y, oldx, oldy);
        }
    }
}
