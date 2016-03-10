package com.framgia.framgiarss.ui.adapter;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.framgia.framgiarss.R;
import com.framgia.framgiarss.data.RSSFeed;
import com.framgia.framgiarss.utils.ConnectionDetector;
import com.framgia.framgiarss.utils.Constants;
import com.framgia.framgiarss.utils.FeedsLink;
import com.framgia.framgiarss.utils.NewsFeedParser;

import java.util.ArrayList;
import java.util.List;

public class TabsPagerAdapter extends PagerAdapter {
    String tabs[] = {Constants.ConstantKeys.AMERICA_TAB, Constants.ConstantKeys.USA_TAB, Constants.ConstantKeys.AFRICA_TAB,
            Constants.ConstantKeys.ASIA_TAB, Constants.ConstantKeys.MIDDLEEAST_TAB, Constants.ConstantKeys.EUROPE_TAB};
    Activity activity;
    private RecyclerView mRecyclerView;
    private ProgressBar mprogressBar;
    private List<RSSFeed> mRssFeedList = new ArrayList<RSSFeed>();

    public TabsPagerAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = activity.getLayoutInflater().inflate(R.layout.pager_item, container, false);
        view = activity.getLayoutInflater().inflate(R.layout.pager_item, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mprogressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        performAsyncTask(position, mRecyclerView);
        ((ViewPager) container).addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        ((ViewPager) container).removeView((View) view);
    }

    public void performAsyncTask(int pos, RecyclerView mrecyclerView) {
        boolean isNetworkAvailable = ConnectionDetector.isConnectingToInternet(activity.getApplicationContext());
        if (isNetworkAvailable) {
            new DoRssFeedTask(mrecyclerView, mprogressBar).execute(FeedsLink.VOALINKS[pos]);
        } else {
            Toast.makeText(activity, Constants.ConstantKeys.NO_INTERNET_ERROR, Toast.LENGTH_LONG).show();
        }
    }

    public class DoRssFeedTask extends AsyncTask<String, Void, List<RSSFeed>> {
        ProgressBar mpbar;
        NewsFeedParser mNewsFeeder;
        RecyclerView mrecyclerView;

        public DoRssFeedTask(RecyclerView recyclerView1, ProgressBar progBar) {
            super();
            this.mrecyclerView = recyclerView1;
            this.mpbar = progBar;
        }

        @Override
        protected void onPreExecute() {
            mpbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<RSSFeed> doInBackground(String... params) {
            for (String urlVal : params) {
                mNewsFeeder = new NewsFeedParser(urlVal);
            }
            mRssFeedList = mNewsFeeder.parseXmlData();
            return mRssFeedList;
        }

        @Override
        protected void onPostExecute(List<RSSFeed> result) {
            mpbar.setVisibility(View.GONE);
            settingAdapter(mrecyclerView);
        }
    }

    public void settingAdapter(RecyclerView rv) {
        NewsDataAdapter mAdapter = new NewsDataAdapter(activity, mRssFeedList);
        int count = mRssFeedList.size();
        if (count != 0 && mAdapter != null) {
            rv.setAdapter(mAdapter);
        }
    }

}