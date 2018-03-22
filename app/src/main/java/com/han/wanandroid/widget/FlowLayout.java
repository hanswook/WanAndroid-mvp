package com.han.wanandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hans
 * date: 2018/3/15 20:50.
 * e-mail: hxxx1992@163.com
 */

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;
            int childHeight = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

            if (childWidth + lineWidth > widthSize) {

                width = Math.max(lineWidth, childWidth);
                lineWidth = childWidth;

                height += lineHeight;
                lineHeight = childHeight;

            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }

            if (i == childCount - 1) {
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);

    }

    private List<List<View>> childViews = new ArrayList<>();

    private List<Integer> lineHeights = new ArrayList<>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        childViews.clear();
        lineHeights.clear();

        int width = getWidth();

        List<View> lineViews = new ArrayList<>();

        int lineWidth = 0;
        int lineHeight = 0;

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (lineWidth + lp.leftMargin + lp.rightMargin + childWidth > width) {
                childViews.add(lineViews);
                lineViews = new ArrayList<>();
                lineHeights.add(lineHeight);
                lineWidth = 0;
            }
            lineViews.add(child);
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(childHeight + lp.topMargin + lp.bottomMargin, lineHeight);

        }
        childViews.add(lineViews);
        lineHeights.add(lineHeight);


        int left = 0;
        int top = 0;
        for (int i = 0; i < childViews.size(); i++) {
            lineViews = childViews.get(i);
            lineHeight = lineHeights.get(i);


            for (int j = 0; j < lineViews.size(); j++) {

                View view = lineViews.get(j);
                if (view.getVisibility() == GONE) {
                    continue;
                }

                MarginLayoutParams mlp = (MarginLayoutParams) view.getLayoutParams();
                int tc = top + mlp.topMargin;
                int lc = left + mlp.leftMargin;
                int rc = view.getMeasuredWidth() + lc;
                int bc = tc + view.getMeasuredHeight();


                view.layout(lc, tc, rc, bc);

                left += mlp.leftMargin + mlp.rightMargin + view.getWidth();
            }
            left = 0;
            top += lineHeight;
        }

    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }


}
