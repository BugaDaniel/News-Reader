package com.patru20.dan.newsreader.implementation;

import android.support.annotation.NonNull;
import com.patru20.dan.newsreader.core.NewsItem;
import java.util.List;

public interface StorageManagerInterface {

    void initialize(@NonNull InitializeCallback callback);
    NewsItem getNewsItem(int position);
    List<NewsItem> getNewsList();
    void setNewsList();

    interface InitializeCallback{
        void onFinished();
    }
}
