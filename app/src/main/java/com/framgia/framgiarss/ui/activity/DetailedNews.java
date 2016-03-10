package com.framgia.framgiarss.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.framgia.framgiarss.R;
import com.framgia.framgiarss.utils.Constants;

public class DetailedNews extends ActionBarActivity {
    private String title, url, image, des;
    private Toolbar mtoolbar;
    private TextView description;

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
        description.setText(des);

    }
}