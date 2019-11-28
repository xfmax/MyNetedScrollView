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
        Log.d("xbase", "onNestedScroll: " + target.toString() + ",type:" + type);

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        //TODO 执行横向列表的隐藏上面图片的操作
        //        如果子view欲向上滑动，则先交给父view滑动
        boolean hideTop = dy > 0 && getScrollY() < 200;
        //如果子view欲向下滑动，必须要子view不能向下滑动后，才能交给父view滑动
        boolean showTop = dy < 0 && getScrollY() >= 0 && !target.canScrollVertically(-1);
        if (hideTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;// consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离
        }

    }
}
