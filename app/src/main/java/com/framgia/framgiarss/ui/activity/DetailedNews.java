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
    private String title, url, image, des;
    private Toolbar mtoolbar;
    private TextView description;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        Intent getdata = new Intent(getIntent());
        title = getdata.getStringExtra(Constants.ConstantKeys.TITILE);
        url = getdata.getStringExtra(Constants.ConstantKeys.URL);
        image = getdata.getStringExtra(Constants.ConstantKeys.ENCLOSURE);
        des = getdata.getStringExtra(Constants.ConstantKeys.DESCRIPTION);

        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(title);

        description = (TextView) findViewById(R.id.description);
        imageView = (ImageView) findViewById(R.id.news_image);
        description.setText(des);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}