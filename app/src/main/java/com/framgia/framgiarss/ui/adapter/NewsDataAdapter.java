package com.framgia.framgiarss.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.framgiarss.R;
import com.framgia.framgiarss.data.RSSFeed;
import com.framgia.framgiarss.ui.activity.DetailedNews;
import com.framgia.framgiarss.utils.Constants;
import com.framgia.framgiarss.utils.ImageDownloaderTask;

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
        new ImageDownloaderTask(feedListRowHolder.imageView).execute(feedItem.getEnclosure());
    }

    @Override
    public int getItemCount() {
        return (null != mfeedItemList ? mfeedItemList.size() : Constants.ConstantKeys.ZERO);
    }

    public class FeedListRowHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView pubDate;
        protected RSSFeed rssFeed;
        protected ImageView imageView;

        public FeedListRowHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.tvtitle);
            this.pubDate = (TextView) view.findViewById(R.id.tvpubdate);
            this.imageView = (ImageView)view.findViewById(R.id.news_images);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent data = new Intent(v.getContext(), DetailedNews.class);
                    data.putExtra(Constants.ConstantKeys.TITILE, rssFeed.getTitle());
                    data.putExtra(Constants.ConstantKeys.URL, rssFeed.getLink());
                    data.putExtra(Constants.ConstantKeys.ENCLOSURE, rssFeed.getEnclosure());
                    data.putExtra(Constants.ConstantKeys.DESCRIPTION, rssFeed.getDescription());
                    v.getContext().startActivity(data);
                }
            });
        }
    }

}