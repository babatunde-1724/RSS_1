package com.framgia.framgiarss.utils;

import com.framgia.framgiarss.ui.views.SlidingTabLayout;

public class SimpleTabColorizer implements SlidingTabLayout.TabColorizer {
    private int[] mIndicatorColors;
    private int[] mDividerColors;

    @Override
    public final int getIndicatorColor(int position) {
        return mIndicatorColors[position % mIndicatorColors.length];
    }

    @Override
    public final int getDividerColor(int position) {
        return mDividerColors[position % mDividerColors.length];
    }

    public void setIndicatorColors(int... colors) {
        mIndicatorColors = colors;
    }

    public void setDividerColors(int... colors) {
        mDividerColors = colors;
    }
}