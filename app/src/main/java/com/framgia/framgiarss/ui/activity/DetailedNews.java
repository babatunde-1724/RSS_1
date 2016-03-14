package com.framgia.framgiarss.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.framgiarss.R;
import com.framgia.framgiarss.anim.AnimationUtils;
import com.framgia.framgiarss.utils.Constants;
import com.framgia.framgiarss.utils.ImageDownloaderTask;

public class DetailedNews extends ActionBarActivity {
    private String title, url, image, description, author, pub_date;
    private Toolbar mtoolbar;
    private TextView tv_description, tv_author, tv_pubdate;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        Intent getdata = new Intent(getIntent());
        title = getdata.getStringExtra(Constants.ConstantKeys.TITILE);
        url = getdata.getStringExtra(Constants.ConstantKeys.URL);
        image = getdata.getStringExtra(Constants.ConstantKeys.ENCLOSURE);
        description = getdata.getStringExtra(Constants.ConstantKeys.DESCRIPTION).trim();
        author = getdata.getStringExtra(Constants.ConstantKeys.AUTHOR);
        pub_date = getdata.getStringExtra(Constants.ConstantKeys.PUBLISHEDDATE);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle(title);

        tv_description = (TextView) findViewById(R.id.description);
        tv_author = (TextView) findViewById(R.id.tvauthor);
        tv_pubdate = (TextView) findViewById(R.id.tvpubdate);

        imageView = (ImageView) findViewById(R.id.news_image);
        tv_description.setText(description);
        tv_author.setText(author);
        tv_pubdate.setText(pub_date);
        new ImageDownloaderTask(imageView).execute(image);
        AnimationUtils.animateToolbarDroppingDown(imageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.goto_link) {
            Intent webIntent = new Intent(this, WebViewActivity.class);
            webIntent.putExtra(Constants.ConstantKeys.TITILE, title);
            webIntent.putExtra(Constants.ConstantKeys.URL, url);
            startActivity(webIntent);
            return true;
        } else if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, url);
            sendIntent.setType(Constants.ConstantKeys.TEXT_PLAIN);
            startActivity(sendIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}