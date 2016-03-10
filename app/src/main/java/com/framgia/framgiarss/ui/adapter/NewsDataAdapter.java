package com.framgia.framgiarss.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framgia.framgiarss.R;
import com.framgia.framgiarss.data.RSSFeed;
import com.framgia.framgiarss.utils.Constants;

import java.util.List;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.FeedListRowHolder> {

    private List<RSSFeed> mfeedItemList;
    private Context mContext;

    public NewsDataAdapter(Context context, List<RSSFeed> mfeedItemList) {
        this.mfeedItemList = mfeedItemList;
        this.mContext = context;
    }

    @Override
    public FeedListRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_row, null);
        FeedListRowHolder mh = new FeedListRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(FeedListRowHolder feedListRowHolder, int i) {
        RSSFeed feedItem = mfeedItemList.get(i);
        feedListRowHolder.rssFeed = feedItem;
        feedListRowHolder.title.setText(feedItem.getTitle());
        feedListRowHolder.pubDate.setText(feedItem.getPublished_date());
    }

    @Override
    public int getItemCount() {
        return (null != mfeedItemList ? mfeedItemList.size() : Constants.ConstantKeys.ZERO);
    }

    public class FeedListRowHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView pubDate;
        protected RSSFeed rssFeed;

        public FeedListRowHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.tvtitle);
            this.pubDate = (TextView) view.findViewById(R.id.tvpubdate);
        }
    }

}