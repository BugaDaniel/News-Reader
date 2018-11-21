package com.patru20.dan.newsreader.implementation;

import android.os.AsyncTask;
import com.patru20.dan.newsreader.core.NewsItem;

import org.w3c.dom.Document;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class NewsDownloaderTask extends AsyncTask<String,Void,List<NewsItem>>  {

    private InputStream inputStream;
    private StorageManagerInterface.InitializeCallback callback;

    public NewsDownloaderTask(StorageManagerInterface.InitializeCallback callback){
        this.callback = callback;
    }

    @Override
    protected List<NewsItem> doInBackground(String... strings) {
        Document document = getDOMFromUrl(strings[0]);
        NewsParser newsParser = new NewsParser(inputStream);
        return newsParser.createNewsObjectList(document);
    }

    @Override
    protected void onPostExecute(List<NewsItem> newsItemList) {
        callback.onFinished();
    }

    private Document getDOMFromUrl(final String url) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection)(new URL(url)).openConnection();
            inputStream = httpURLConnection.getInputStream();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            return documentBuilder.parse(inputStream);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
