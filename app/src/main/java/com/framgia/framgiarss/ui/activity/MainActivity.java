package com.framgia.framgiarss.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.framgia.framgiarss.R;
import com.framgia.framgiarss.anim.AnimationUtils;
import com.framgia.framgiarss.ui.fragment.ProfileFragment;
import com.framgia.framgiarss.ui.fragment.TabFragment;
import com.framgia.framgiarss.ui.fragment.BaseFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    private ViewGroup mContainerToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_lay) ;
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.containerView,new TabFragment()).commit();
        mContainerToolbar = (ViewGroup) findViewById(R.id.container_app_bar);
        AnimationUtils.animateToolbarDroppingDown(mContainerToolbar);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
            mDrawerLayout.closeDrawers();
            selectDrawerItem(menuItem);
                        return true;
            }
        });
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_item_usa:
                fragmentClass = BaseFragment.class;
                break;
            case R.id.nav_item_asia:
                fragmentClass = BaseFragment.class;
                break;
            case R.id.nav_item_africa:
                fragmentClass = BaseFragment.class;
                break;
            case R.id.nav_item_middle_east:
                fragmentClass = BaseFragment.class;
                break;
            case R.id.nav_item_europe:
                fragmentClass = BaseFragment.class;
                break;
            case R.id.nav_item_america:
                fragmentClass = BaseFragment.class;
                break;
            case R.id.nav_item_profile:
                fragmentClass = ProfileFragment.class;
                break;
            default:
                fragmentClass = BaseFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.containerView, fragment).commit();
        setTitle(menuItem.getTitle());
        mDrawerLayout.closeDrawers();
    }
}