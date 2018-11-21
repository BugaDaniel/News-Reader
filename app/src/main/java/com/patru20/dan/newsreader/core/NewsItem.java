package com.patru20.dan.newsreader.core;

import android.net.Uri;

public class NewsItem {

    private String title;
    private String description;
    private Uri imageUrl;
    private String pubDate;
    private String linkToWebsite;

    public NewsItem(String title, String description, Uri imageUrl, String pubDate, String linkToWebsite){
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.pubDate = pubDate;
        this.linkToWebsite = linkToWebsite;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Uri getImageUrl() {
        return imageUrl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLinkToWebsite() {
        return linkToWebsite;
    }

}
