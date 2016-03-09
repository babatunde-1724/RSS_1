package com.framgia.framgiarss.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.framgia.framgiarss.ui.views.SlidingTabStrip;

public class TabClickListener implements View.OnClickListener {
    public SlidingTabStrip mTabStrip;
    private ViewPager mViewPager;

    @Override
    public void onClick(View v) {
        for (int i = Constants.ConstantKeys.ZERO; i < mTabStrip.getChildCount(); i++) {
            if (v == mTabStrip.getChildAt(i)) {
                mViewPager.setCurrentItem(i);
                return;
            }
        }
    }
}