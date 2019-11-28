package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent2;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.jennifer.andy.nestedscrollingdemo.R;

public class GrandLayout extends LinearLayout implements NestedScrollingParent2 {
    private ImageView iv;
    public GrandLayout(Context context) {
        super(context);
    }

    public GrandLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public GrandLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        iv = findViewById(R.id.iv);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d("xbase", "=========================onStartNestedScroll: ");
        return true;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        //TODO 执行横向列表的隐藏上面图片的操作
        Log.d("xbase", "滚动前=======================: " + target.toString() + ",type:" + type);
        scrollBy(0,dy);
        consumed[0] = 0;
        consumed[1] = 200;

    }
}
