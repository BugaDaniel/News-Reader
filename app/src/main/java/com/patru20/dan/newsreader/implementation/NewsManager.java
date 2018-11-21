package com.patru20.dan.newsreader.implementation;

import android.support.annotation.NonNull;
import com.patru20.dan.newsreader.core.NewsItem;
import java.util.ArrayList;
import java.util.List;

public class NewsManager implements StorageManagerInterface {

    private List<NewsItem> newsList = new ArrayList<>();
    private NewsDownloaderTask newsDownloaderTask;

    @Override
    public void initialize(@NonNull InitializeCallback callback) {
        newsDownloaderTask = new NewsDownloaderTask(callback);
        newsDownloaderTask.execute("https://rss.stirileprotv.ro/");
    }

    @Override
    public NewsItem getNewsItem(int position) {
        return newsList.get(position);
    }

    @Override
    public List<NewsItem> getNewsList() {
        return newsList;
    }

    @Override
    public void setNewsList(){
        try {
            this.newsList = newsDownloaderTask.get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
