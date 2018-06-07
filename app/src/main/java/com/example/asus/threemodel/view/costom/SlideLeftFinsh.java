package com.example.asus.threemodel.view.costom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class SlideLeftFinsh extends FrameLayout {

    // 滑动关闭activity  的帮助类
    private ViewDragHelper mViewDragHelper;
    //初始化画笔
    private Paint mPaint;
    private Activity mActivity;
    private ViewGroup mDecorView;
    private View mRootView;
    private int mScreenWidth;
    private int mScreenHeight;
    private float mSlideWidth;
    private int curSlideX;

    public SlideLeftFinsh(@NonNull Context context) {
        this(context,null);
    }

    public SlideLeftFinsh(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }


    public SlideLeftFinsh(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mActivity = (Activity) context;
        //滑动关闭视图帮助类
        mViewDragHelper = ViewDragHelper.create(this, new DragCallback());
        //设置从左边关闭activity
        mViewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
        //设置画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//是否支持刚锯齿
        mPaint.setStrokeWidth(2);//刚锯齿宽度
        mPaint.setColor(Color.GRAY);//设置颜色
    }

    /**
     * 绑定activity --- 你想要关闭的activity
     */
    public void bind() {
        //activity是设置在屏幕上的  也是有视图布局的   这一步就是拿到这个布局 LinearLayout
        mDecorView = (ViewGroup) mActivity.getWindow().getDecorView();
        //拿到顶层视图下的第一个字视图
        mRootView = mDecorView.getChildAt(0);
        //将第一个视图从顶级视图移除掉
        mDecorView.removeView(mRootView);
        //将这个activity添加到viewgroup
        this.addView(mRootView);
        //再将当前viewgroup添加到顶级视图
        mDecorView.addView(this);

        DisplayMetrics dm = new DisplayMetrics();
        mScreenWidth = dm.widthPixels;
        mScreenHeight = dm.heightPixels;
        mSlideWidth = dm.widthPixels * 028f;
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        return mViewDragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    class DragCallback extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return false;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            //当前回调，松开手时触发，比较触发条件和当前的滑动距离
            int left = releasedChild.getLeft();
            if (left <= mSlideWidth) {
                //缓慢滑动的方法,小于触发条件，滚回去
                mViewDragHelper.settleCapturedViewAt(0, 0);
            } else {
                //大于触发条件，滚出去...
                mViewDragHelper.settleCapturedViewAt(mScreenWidth, 0);
            }
            //需要手动调用更新界面的方法
            invalidate();

        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            curSlideX = left;
            //当滑动位置改变时，刷新View,绘制新的阴影位置
            invalidate();
            //当滚动位置到达屏幕最右边，则关掉Activity
            if (changedView == mRootView && left >= mScreenWidth) {
                mActivity.finish();
            }
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //限制左右拖拽的位移
            left = left >= 0 ? left : 0;
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            //上下不能移动，返回0
            return 0;
        }

        @Override
        public void onEdgeDragStarted(int edgeFlags, int pointerId) {
            //触发边缘时，主动捕捉mRootView
            mViewDragHelper.captureChildView(mRootView, pointerId);
        }
    }


    @Override
    public void computeScroll() {
        //使用settleCapturedViewAt方法是，必须重写computeScroll方法，传入true
        //持续滚动期间，不断刷新ViewGroup
        if (mViewDragHelper.continueSettling(true))
            invalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        //进行阴影绘制,onDraw（）方法在ViewGroup中不一定会执行
        drawShadow(canvas);
        super.dispatchDraw(canvas);
    }


    private void drawShadow(Canvas canvas) {
        canvas.save();
        //构造一个渐变
        Shader mShader = new LinearGradient(curSlideX - 40, 0, curSlideX, 0, new int[]{Color.parseColor("#1edddddd"), Color.parseColor("#6e666666"), Color.parseColor("#9e666666")}, null, Shader.TileMode.REPEAT);
        //设置着色器
        mPaint.setShader(mShader);
        //绘制时，注意向左边偏移
        RectF rectF = new RectF(curSlideX - 40, 0, curSlideX, mScreenHeight);
        canvas.drawRect(rectF, mPaint);
        canvas.restore();
    }
}
