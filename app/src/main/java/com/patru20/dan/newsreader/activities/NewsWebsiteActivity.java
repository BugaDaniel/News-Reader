package com.patru20.dan.newsreader.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.patru20.dan.newsreader.NewsApplication;
import com.patru20.dan.newsreader.R;

public class NewsWebsiteActivity extends CustomToolbarActivity {

    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_website);

        webView = findViewById(R.id.web_view);
        progressBar = findViewById(R.id.progressBar);
        setToolbar(R.id.toolbar_web);

        Intent intent = getIntent();
        final int newsPosition = intent.getIntExtra("position", -1);
        newsItem = NewsApplication.getNewsManager().getNewsItem(newsPosition);

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setMax(100);
                progressBar.setProgress(1);
                progressBar.setProgress(progress);
                if(progress == 100){
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        // by default setWebViewClient(new WebViewClient()) return false
        // false = webView handles url itself
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(newsItem.getLinkToWebsite());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
