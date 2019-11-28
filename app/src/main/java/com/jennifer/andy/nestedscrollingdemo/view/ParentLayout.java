package com.jennifer.andy.nestedscrollingdemo.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingChild2;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent2;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jennifer.andy.nestedscrollingdemo.R;

public class ParentLayout extends CoordinatorLayout implements NestedScrollingParent2, NestedScrollingChild2 {

    private View mTopView;//头部view
    private View mNavView;//导航view
    private ViewPager mViewPager;//Viewpager
    private NestedScrollingChildHelper mChildHelper;

    public ParentLayout(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mChildHelper = new NestedScrollingChildHelper(this);
    }

    public ParentLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ParentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTopView = findViewById(R.id.banner);
        mNavView = findViewById(R.id.sl_tab);
        mViewPager = findViewById(R.id.sl_viewpager);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先测量一次
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //ViewPager修改后的高度= 总高度-TabLayout高度
        ViewGroup.LayoutParams lp = mViewPager.getLayoutParams();
        lp.height = getMeasuredHeight() - mNavView.getMeasuredHeight();
        mViewPager.setLayoutParams(lp);
        //因为ViewPager修改了高度，所以需要重新测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 嵌套滑动时，如果父View处理了fling,那子view就没有办法处理fling了，所以这里要返回为false
     */
    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d("xbase", "-------------------onStartNestedScroll: ");
        return startNestedScroll(axes,type);
    }

    /**
     * 子类同意父类进行滑动事件的分发
     *
     * @param child
     * @param target
     * @param axes
     * @param type
     */
    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d("xbase", "滚动后:dxUnconsumed " + dxUnconsumed+ ",dyUnconsumed:" + dyUnconsumed);
        mTopView.scrollBy(dxUnconsumed,dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d("xbase", "滚动前: " + target.toString() + ",type:" + type);
        dispatchNestedPreScroll(dx, dy, consumed,consumed, type);
    }

    ///////////////////////////child处理逻辑/////////////////////////////////////////

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return mChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mChildHelper.startNestedScroll(axes);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        return mChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed,
                offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed,
                                           int[] offsetInWindow) {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return mChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return false;
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return false;
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return mChildHelper.startNestedScroll(axes,type);
    }

    @Override
    public void stopNestedScroll(int type) {
        mChildHelper.stopNestedScroll();
    }
}