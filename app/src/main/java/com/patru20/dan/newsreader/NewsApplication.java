package com.patru20.dan.newsreader;

import android.app.Application;
import com.patru20.dan.newsreader.core.NewsAdapter;
import com.patru20.dan.newsreader.implementation.NewsManager;
import com.patru20.dan.newsreader.implementation.StorageManagerInterface;

import java.lang.ref.WeakReference;

public class NewsApplication extends Application {

    static NewsManager newsManager;
    public static WeakReference<NewsAdapter> adapterWeakReference;

    @Override
    public void onCreate(){
        super.onCreate();
        newsManager = new NewsManager();
        newsManager.initialize(new StorageManagerInterface.InitializeCallback() {
            @Override
            public void onFinished() {
                newsManager.setNewsList();
                customAdapterNotify();
            }
        });
    }

    public static NewsManager getNewsManager(){
        return newsManager;
    }

    public static void setAdapter(NewsAdapter newsActivityAdapter){
        adapterWeakReference = new WeakReference<>(newsActivityAdapter);
    }

    public static void customAdapterNotify() {
        NewsAdapter adapter = adapterWeakReference != null ? adapterWeakReference.get() : null;
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }
}
