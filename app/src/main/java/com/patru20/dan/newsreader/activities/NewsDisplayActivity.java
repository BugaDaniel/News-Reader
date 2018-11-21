package com.patru20.dan.newsreader.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;

import com.patru20.dan.newsreader.core.NewsAdapter;
import com.patru20.dan.newsreader.NewsApplication;
import com.patru20.dan.newsreader.R;

public class NewsDisplayActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_display);

        recyclerView = findViewById(R.id.recycler_view);
        context = this;
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                adapter = new NewsAdapter(context,recyclerView.getMeasuredWidth());
                NewsApplication.setAdapter(adapter);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
