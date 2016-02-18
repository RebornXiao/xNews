package com.xnews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.xnews.base.BaseFragment;

import java.util.List;


/**
 * 主界面Fragment适配器
 * Created by 辰 on 2015-07-16.
 */
public class MainPagerAdapter extends FragmentStatePagerAdapter {
    List<Class> fragments = null;

    public MainPagerAdapter(FragmentManager fm, List<Class> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        if (fragments != null && fragments.size() > i) {
            try {
                return (Fragment) fragments.get(i).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0) {
            return fragments.size();
        }
        return 0;
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        BaseFragment fragment = (BaseFragment) super.instantiateItem(container, position);
        return fragment;
    }
}
