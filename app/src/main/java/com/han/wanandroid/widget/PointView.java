package com.han.wanandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.han.wanandroid.R;
import com.han.wanandroid.utils.LogUtils;

/**
 * Created by hans
 * date: 2018/2/28 18:12.
 * e-mail: hxxx1992@163.com
 */

public class PointView extends View {

    private int unSelectedColor = getResources().getColor(R.color.gray_66);
    private int selectedColor = Color.WHITE;

    private int paintColor = 0;

    public PointView(Context context) {
        this(context, null);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //第二个参数就是我们在styles.xml文件中的<declare-styleable>标签
        //即属性集合的标签，在R文件中名称为R.styleable+name
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PointView);
        //第一个参数为属性集合里面的属性，R文件名称：R.styleable+属性集合名称+下划线+属性名称
        //第二个参数为，如果没有设置这个属性，则设置的默认的值
        unSelectedColor = a.getColor(R.styleable.PointView_p_color, getResources().getColor(R.color.gray_66));
        paintColor = unSelectedColor;
        //最后记得将TypedArray对象回收
        a.recycle();

    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        int circle = getMeasuredHeight() / 2;
        int centerX = circle;
        int centerY = circle;
        Paint p = new Paint();
        p.setColor(paintColor);
        LogUtils.v("pointview", " onDraw:centerX:" + centerX + ",centerY:" + centerY + ",p:color:" + p.getColor());
        canvas.drawCircle(centerX, centerY, circle, p);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.v("pointview", "onMeasure:widthMeasureSpec" + MeasureSpec.getSize(widthMeasureSpec) + "heightMeasureSpec:" + MeasureSpec.getSize(heightMeasureSpec));

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.v("pointview", "changed:" + changed + "left:" + left + "top:" + top + "right:" + right + "bottom:" + bottom);
    }

    public void setChangeColor() {
        paintColor = selectedColor;
        invalidate();
    }

    public void setDefaultColor() {
        paintColor = unSelectedColor;
        invalidate();
    }
}
