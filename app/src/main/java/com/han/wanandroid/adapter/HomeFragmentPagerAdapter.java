package com.han.wanandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hans
 * date: 2018/3/8 20:55.
 * e-mail: hxxx1992@163.com
 */

public class HomeFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> datas;

    public HomeFragmentPagerAdapter(FragmentManager fm, List<Fragment> datas) {
        super(fm);
        this.datas = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return datas.size();
    }
}
