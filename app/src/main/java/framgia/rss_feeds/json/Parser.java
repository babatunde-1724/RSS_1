package framgia.rss_feeds.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import framgia.rss_feeds.extras.Constants;
import framgia.rss_feeds.extras.Keys;
import framgia.rss_feeds.pojo.NewsEntity;

import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_ABSTRACT;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_BYLINE;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_CREATED_DATE;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_IMAGEURL;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_ITEM_TYPE;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_KICKER;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_MATERIAL_TYPE_FACET;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_PUBLISHED_DATE;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_RESULTS;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_SECTION;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_SUBSECTION;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_TITLE;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_UPDATED_DATE;
import static framgia.rss_feeds.extras.Keys.EndpointNeyYorkTimes.KEY_URL;

public class Parser {
    public static ArrayList<NewsEntity> parseNewsJSON(JSONObject response) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<NewsEntity> listNews = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {
                JSONArray arrayNews = response.getJSONArray(KEY_RESULTS);
                for (int i = 0; i < arrayNews.length(); i++) {
                    String section = Constants.NA;
                    String subSection = Constants.NA;
                    String title = Constants.NA;
                    String abstracts = Constants.NA;
                    String url = Constants.NA;
                    String byline = Constants.NA;
                    String item_type = Constants.NA;
                    String updated_date = Constants.NA;
                    String created_date = Constants.NA;
                    String published_date = Constants.NA;
                    String material_type_facet = Constants.NA;
                    String kicker = Constants.NA;
                    String imageUrl = Constants.NA;
                    JSONObject currentNews = arrayNews.getJSONObject(i);
                    if (Utils.contains(currentNews, KEY_SECTION)) {
                        section = currentNews.getString(KEY_SECTION);
                    }
                    if (Utils.contains(currentNews, KEY_SUBSECTION)) {
                        subSection = currentNews.getString(KEY_SUBSECTION);
                    }
                    if (Utils.contains(currentNews, Keys.EndpointNeyYorkTimes.KEY_TITLE)) {
                        title = currentNews.getString(KEY_TITLE);
                    }
                    if (Utils.contains(currentNews, KEY_ABSTRACT)) {
                        abstracts = currentNews.getString(KEY_ABSTRACT);
                    }
                    if (Utils.contains(currentNews, KEY_URL)) {
                        url = currentNews.getString(KEY_URL);
                    }
                    if (Utils.contains(currentNews, KEY_BYLINE)) {
                        byline = currentNews.getString(KEY_BYLINE);
                    }
                    if (Utils.contains(currentNews, KEY_ITEM_TYPE)) {
                        item_type = currentNews.getString(KEY_ITEM_TYPE);
                    }
                    if (Utils.contains(currentNews, KEY_UPDATED_DATE)) {
                        updated_date = currentNews.getString(KEY_UPDATED_DATE);
                    }
                    if (Utils.contains(currentNews, KEY_CREATED_DATE)) {
                        created_date = currentNews.getString(KEY_CREATED_DATE);
                    }
                    if (Utils.contains(currentNews, KEY_PUBLISHED_DATE)) {
                        published_date = currentNews.getString(KEY_PUBLISHED_DATE);
                    }
                    if (Utils.contains(currentNews, KEY_MATERIAL_TYPE_FACET)) {
                        material_type_facet = currentNews.getString(KEY_MATERIAL_TYPE_FACET);
                    }
                    if (Utils.contains(currentNews, KEY_KICKER)) {
                        kicker = currentNews.getString(KEY_KICKER);
                    }
                    JSONObject post = (JSONObject) arrayNews.get(i);
                    JSONArray multimedia = post.getJSONArray("multimedia");
                    JSONObject image = (JSONObject) multimedia.get(1);
                    imageUrl = image.getString("url");
                    if (Utils.contains(image, KEY_IMAGEURL)) {
                        imageUrl = currentNews.getString(KEY_IMAGEURL);
                    }
                    NewsEntity news = new NewsEntity();
                    news.setSection(section);
                    news.setSubSection(subSection);
                    news.setTitle(title);
                    news.setAbstracts(abstracts);
                    news.setUrl(url);
                    news.setByline(byline);
                    news.setItem_type(item_type);
                    Date dateupdated = null;
                    try {
                        dateupdated = dateFormat.parse(updated_date);
                    } catch (ParseException e) {
                    }
                    news.setUpdated_date(dateupdated);
                    Date datepublished = null;
                    try {
                        datepublished = dateFormat.parse(published_date);
                    } catch (ParseException e) {
                    }
                    news.setPublished_date(datepublished);
                    Date datecreated = null;
                    try {
                        datecreated = dateFormat.parse(created_date);
                    } catch (ParseException e) {
                    }
                    news.setCreated_date(datecreated);
                    news.setMaterial_type_facet(material_type_facet);
                    news.setKicker(kicker);
                    news.setImageUrl(imageUrl);
                    listNews.add(news);
                }
            } catch (JSONException e) {
            }
        }
        return listNews;
    }
}