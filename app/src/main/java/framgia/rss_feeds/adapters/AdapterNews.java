package framgia.rss_feeds.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import framgia.rss_feeds.R;
import framgia.rss_feeds.anim.AnimationUtils;
import framgia.rss_feeds.extras.Constants;
import framgia.rss_feeds.network.VolleySingleton;
import framgia.rss_feeds.pojo.NewsEntity;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolderNews> {
    private ArrayList<NewsEntity> mListNews = new ArrayList<>();
    private LayoutInflater mInflater;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    private DateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private int mPreviousPosition = 0;

    public AdapterNews(Context context) {
        mInflater = LayoutInflater.from(context);
        mVolleySingleton = VolleySingleton.getInstance();
        mImageLoader = mVolleySingleton.getImageLoader();
    }

    public void setNews(ArrayList<NewsEntity> listNews) {
        this.mListNews = listNews;
        //update the adapter to reflect the new set of news
        notifyDataSetChanged();
    }

    @Override
    public ViewHolderNews onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.custom_home_news, parent, false);
        ViewHolderNews viewHolder = new ViewHolderNews(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderNews holder, int position) {
        NewsEntity currentNews = mListNews.get(position);
        if (currentNews.getTitle()!=null){ holder.newsTitle.setText(currentNews.getTitle());}
        else{
            holder.newsTitle.setText("unavailable");
        }
        if (currentNews.getAbstracts()!=null){ holder.newsAbstract.setText(currentNews.getAbstracts());}
        else{
            holder.newsAbstract.setText("unavailable");
        }
        Date newsReleaseDate = currentNews.getPublished_date();
        if (newsReleaseDate != null) {
            String formattedDate = mFormatter.format(newsReleaseDate);
            holder.newsPublishedDate.setText(formattedDate);
        } else {
            holder.newsPublishedDate.setText(Constants.NA);
        }
        if (position > mPreviousPosition) {AnimationUtils.animateSunblind(holder, true);}
        else {
            AnimationUtils.animateSunblind(holder, false);
        }
        mPreviousPosition = position;
        String urlThumnail = currentNews.getImageUrl();
        loadImages(urlThumnail, holder);
    }

    private void loadImages(String urlThumbnail, final ViewHolderNews holder) {
        if (!urlThumbnail.equals(Constants.NA)) {
            mImageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    holder.newsThumbnail.setImageBitmap(response.getBitmap());
                }
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });}
    }

    @Override
    public int getItemCount() {
        return mListNews.size();
    }

    static class ViewHolderNews extends RecyclerView.ViewHolder {
        ImageView newsThumbnail;
        TextView newsTitle;
        TextView newsPublishedDate;
        TextView newsAbstract;
        TextView newsUrl;
        public ViewHolderNews(View itemView) {
            super(itemView);
            newsThumbnail = (ImageView) itemView.findViewById(R.id.newsThumbnail);
            newsTitle = (TextView) itemView.findViewById(R.id.textViewtitle);
            newsPublishedDate = (TextView) itemView.findViewById(R.id.textViewdate);
            newsAbstract = (TextView) itemView.findViewById(R.id.textViewabstract);
            newsUrl = (TextView) itemView.findViewById(R.id.textviewurl);
        }
    }
}