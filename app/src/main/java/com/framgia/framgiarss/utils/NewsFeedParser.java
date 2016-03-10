package com.framgia.framgiarss.utils;

import com.framgia.framgiarss.data.RSSFeed;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsFeedParser {
    private InputStream urlStream;
    private XmlPullParserFactory factory;
    private XmlPullParser parser;
    private List<RSSFeed> rssFeedList;
    private RSSFeed rssFeed;
    private String urlString;
    private String tagName;
    private String title;
    private String link;
    private String description;
    private String category;
    private String author;
    private String comments;
    private String enclosure;
    private String pubDate;
    private String guid;

    public NewsFeedParser(String urlString) {
        this.urlString = urlString;
    }

    public static InputStream downloadStream(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        InputStream stream = conn.getInputStream();
        return stream;
    }

    public List<RSSFeed> parseXmlData() {
        try {
            factory = XmlPullParserFactory.newInstance();
            parser = factory.newPullParser();
            urlStream = downloadStream(urlString);
            parser.setInput(urlStream, null);
            int eventType = parser.getEventType();
            boolean done = false;
            rssFeed = new RSSFeed();
            rssFeedList = new ArrayList<RSSFeed>();
            while (eventType != XmlPullParser.END_DOCUMENT && !done) {
                tagName = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        switch (tagName) {
                            case Constants.ConstantKeys.ITEM:
                                rssFeed = new RSSFeed();
                                break;
                            case Constants.ConstantKeys.TITILE:
                                title = parser.nextText();
                                break;
                            case Constants.ConstantKeys.DESCRIPTION:
                                description = parser.nextText();
                                break;
                            case Constants.ConstantKeys.LINK:
                                link = parser.nextText();
                                break;
                            case Constants.ConstantKeys.GUID:
                                guid = parser.nextText();
                                break;
                            case Constants.ConstantKeys.PUBLISHEDDATE:
                                pubDate = parser.nextText();
                                break;
                            case Constants.ConstantKeys.CATEGORY:
                                category = parser.nextText();
                                break;
                            case Constants.ConstantKeys.AUTHOR:
                                author = parser.nextText();
                                break;
                            case Constants.ConstantKeys.ENCLOSURE:
                                enclosure = parser.getAttributeValue(0);
                                break;
                            case Constants.ConstantKeys.COMMENTS:
                                comments = parser.nextText();
                                break;
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagName.equals(Constants.ConstantKeys.CHANNEL)) {
                            done = true;
                        } else if (tagName.equals(Constants.ConstantKeys.ITEM)) {
                            rssFeed = new RSSFeed(guid, link, title, description, category, author,
                                    pubDate, enclosure, comments);
                            rssFeedList.add(rssFeed);
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rssFeedList;
    }

}