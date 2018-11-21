package com.patru20.dan.newsreader.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.patru20.dan.newsreader.R;
import com.patru20.dan.newsreader.core.NewsItem;

public abstract class CustomToolbarActivity extends AppCompatActivity {

    NewsItem newsItem;
    Toolbar toolbar;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item_share) {
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, newsItem.getTitle());
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, newsItem.getLinkToWebsite());
            startActivity(Intent.createChooser(sharingIntent, newsItem.getTitle()));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setToolbar(int id){
        toolbar = findViewById(id);
        setSupportActionBar(toolbar);
        toolbar.setTitle("News Reader");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
