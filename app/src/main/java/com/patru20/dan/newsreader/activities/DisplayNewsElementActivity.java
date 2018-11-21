package com.patru20.dan.newsreader.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.patru20.dan.newsreader.NewsApplication;
import com.patru20.dan.newsreader.R;

public class DisplayNewsElementActivity extends CustomToolbarActivity {

    TextView title;
    ImageView newsImage;
    TextView description;
    TextView date;
    Button visitWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news_element);

        title = findViewById(R.id.title);
        newsImage = findViewById(R.id.image);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        visitWeb = findViewById(R.id.visit_website);

        setToolbar(R.id.toolbar);

        Intent intent = getIntent();
        final int newsPosition = intent.getIntExtra("position", -1);
        int width = intent.getIntExtra("width", -1);

        if(newsPosition != -1){
            newsItem = NewsApplication.getNewsManager().getNewsItem(newsPosition);

            title.setText(newsItem.getTitle());
            Glide
                    .with(this)
                    .load(newsItem.getImageUrl())
                    .apply(new RequestOptions().override(width, (width * 9) / 16))
                    .into(newsImage);
            description.setText(newsItem.getDescription());
            date.setText(newsItem.getPubDate());
        }
        visitWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visitWebsiteIntent = new Intent(getApplicationContext(), NewsWebsiteActivity.class);
                visitWebsiteIntent.putExtra("position", newsPosition);
                startActivity(visitWebsiteIntent);
            }
        });
    }
}
