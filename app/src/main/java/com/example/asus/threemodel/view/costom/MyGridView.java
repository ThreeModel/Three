package com.example.asus.threemodel.view.costom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {

    public boolean hasScrollBar = true;

    public MyGridView(Context context) {
        this(context,null);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = heightMeasureSpec;
        if (hasScrollBar) {
            expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);// 注意这里,这里的意思是直接测量出GridView的高度
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
