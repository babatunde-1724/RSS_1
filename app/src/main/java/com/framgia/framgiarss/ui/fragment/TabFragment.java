package com.framgia.framgiarss.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.framgia.framgiarss.R;
import com.framgia.framgiarss.data.constant.AppConstant;

public class TabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 6 ;
    private static final String USA = "Usa";
    private static final String ASIA = "Asia";
    private static final String AFRICA = "Africa";
    private static final String MIDDLE_EAST = "Middle East";
    private static final String EUROPE = "Europe";
    private static final String AMERICA = "America";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x =  inflater.inflate(R.layout.tab_layout,null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
            tabLayout.setupWithViewPager(viewPager);
                   }
        });
        return x;
    }

    class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
          switch (position){
              case AppConstant.TAB_POS_USA : return new BaseFragment();
              case AppConstant.TAB_POS_ASIA : return new BaseFragment();
              case AppConstant.TAB_POS_AFRICA : return new BaseFragment();
              case AppConstant.TAB_POS_MIDDLE_EAST : return new BaseFragment();
              case AppConstant.TAB_POS_EUROPE : return new BaseFragment();
              case AppConstant.TAB_POS_AMERICA :return new BaseFragment();
          }
        return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case AppConstant.TAB_POS_USA :
                    return USA;
                case AppConstant.TAB_POS_ASIA :
                    return ASIA;
                case AppConstant.TAB_POS_AFRICA :
                    return AFRICA;
                case AppConstant.TAB_POS_MIDDLE_EAST :
                    return MIDDLE_EAST;
                case AppConstant.TAB_POS_EUROPE :
                    return EUROPE;
                case AppConstant.TAB_POS_AMERICA :
                    return AMERICA;
            }
                return null;
        }}
}