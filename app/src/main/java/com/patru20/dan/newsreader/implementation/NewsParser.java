package com.patru20.dan.newsreader.implementation;

import android.net.Uri;
import com.patru20.dan.newsreader.core.NewsItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NewsParser {

    private InputStream inputStream;

    public NewsParser(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List<NewsItem> createNewsObjectList(Document document){
        List<NewsItem> newsList = new ArrayList<>();
        Element xmlMainTag = document.getDocumentElement();
        xmlMainTag.normalize();

        NodeList nList = document.getElementsByTagName("item");

        for (int i=0; i<nList.getLength(); i++) {

            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element xmlNewsItemTag = (Element) node;
                NewsItem newsItem = new NewsItem(getValue("title", xmlNewsItemTag),
                        getValue("description", xmlNewsItemTag),
                        Uri.parse(getValue("enclosure", xmlNewsItemTag)),
                        getValue("pubDate", xmlNewsItemTag).substring(0,16),
                        getValue("link", xmlNewsItemTag));
                newsList.add(newsItem);
            }
        }
        return newsList;
    }

    private String getValue(String tag, Element element) {
        if(tag.equals("enclosure")){
            Node node = element.getElementsByTagName(tag).item(0);
            String urlAttr = null;
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                urlAttr = ((Element)node).getAttribute("url");
            }
            return urlAttr;
        }
        else{
            NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
            Node node = nodeList.item(0);
            return node.getNodeValue();
        }

    }
}
