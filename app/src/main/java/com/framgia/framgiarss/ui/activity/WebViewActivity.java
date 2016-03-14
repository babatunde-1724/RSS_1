package com.framgia.framgiarss.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.framgia.framgiarss.R;
import com.framgia.framgiarss.utils.Constants;

public class WebViewActivity extends ActionBarActivity {
    private WebView mwebView;
    private Toolbar mtoolbar;
    private String mtitle;
    private String murl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        mtitle = getIntent().getExtras().getString(Constants.ConstantKeys.TITILE);
        murl = getIntent().getExtras().getString(Constants.ConstantKeys.URL);
        getSupportActionBar().setTitle(mtitle);
        if (savedInstanceState != null) {
            ((WebView) findViewById(R.id.webView1)).restoreState(savedInstanceState);
        } else {
            openWeb();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.goto_home) {
            finish();
            return true;
        } else if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, murl);
            sendIntent.setType(Constants.ConstantKeys.TEXT_PLAIN);
            startActivity(sendIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        ((WebView) findViewById(R.id.webView1)).saveState(outState);
    }

    public void openWeb() {
        mwebView = (WebView) findViewById(R.id.webView1);
        mwebView.getSettings().setJavaScriptEnabled(true);
        mwebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        final Activity activity = this;
        mwebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mwebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                activity.setProgress(progress * Constants.ConstantKeys.LOAD_TIME);
            }
        });

        mwebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, Constants.ConstantKeys.FAILED_LOADING + description, Toast.LENGTH_SHORT).show();
            }
        });
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mwebView.loadUrl(murl);
            }
        });
    }

}