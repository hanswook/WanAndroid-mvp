package com.han.wanandroid.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.han.wanandroid.R;
import com.han.wanandroid.adapter.BannerPagerAdapter;
import com.han.wanandroid.interfaces.OnItemClickListener;
import com.han.wanandroid.utils.DensityUtils;
import com.han.wanandroid.utils.LogUtils;
import com.han.wanandroid.utils.ToastUtils;
import com.han.wanandroid.utils.image.GlideApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hans
 * date: 2018/3/14 19:03.
 * e-mail: hxxx1992@163.com
 */

public class SimpleBanner extends FrameLayout {


    private List<ImageView> imageList;

    private ViewPager viewPager;

    private TextView textView;

    private LinearLayout linearLayout;


    public SimpleBanner(@NonNull Context context) {
        this(context, null);
    }

    public SimpleBanner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleBanner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        initView();
        initData();
    }

    private void initView() {
        if (getChildCount() <= 0) {
            View.inflate(getContext(), R.layout.simple_banner_layout, this);
            viewPager = findViewById(R.id.sb_viewpager);
            textView = findViewById(R.id.sb_title);
            linearLayout = findViewById(R.id.sb_point_layout);
        }
    }

    private int lastPosition = 0;

    private void initData() {
        imageList = new ArrayList<>();


        for (int i = 0; i < 15; i++) {

            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            GlideApp.with(getContext()).load("https://ws1.sinaimg.cn/large/610dc034ly1fj3w0emfcbj20u011iabm.jpg").into(imageView);
            imageList.add(imageView);

            PointView pv = new PointView(getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(DensityUtils
                    .dp2px(getContext(), 10), DensityUtils.dp2px(getContext(),
                    10));

            //除第一个以外，其他小白点都需要设置左边距
            if (i != 0) {
                layoutParams.leftMargin = DensityUtils.dp2px(getContext(), 10 / 2);
                pv.setEnabled(false);
            }

            pv.setLayoutParams(layoutParams);
            linearLayout.addView(pv);
        }

        BannerPagerAdapter bannerPagerAdapter = new BannerPagerAdapter(imageList);
        bannerPagerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ToastUtils.show("position:" + position);
            }
        });
        viewPager.setAdapter(bannerPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.e("sb1", "position:" + position % imageList.size() + ",llayout.size:" + linearLayout.getChildCount());
                ((PointView) (linearLayout.getChildAt(lastPosition % imageList.size()))).setDefaultColor();
                ((PointView) (linearLayout.getChildAt(position % imageList.size()))).setChangeColor();
                lastPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageList.size());

    }


}
