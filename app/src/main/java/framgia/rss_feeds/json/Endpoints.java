package framgia.rss_feeds.json;

import static framgia.rss_feeds.extras.UrlEndpoints.API_KEY_HOME_NEWS;
import static framgia.rss_feeds.extras.UrlEndpoints.URL_CHAR_QUESTION;
import static framgia.rss_feeds.extras.UrlEndpoints.URL_HOME_NEWS;
import static framgia.rss_feeds.extras.UrlEndpoints.URL_MAGAZINE_NEWS;
import static framgia.rss_feeds.extras.UrlEndpoints.URL_PARAM_API_KEY2;
import static framgia.rss_feeds.extras.UrlEndpoints.URL_SPORTS_NEWS;
import static framgia.rss_feeds.extras.UrlEndpoints.URL_TECHNOLOGY_NEWS;

public class Endpoints {
    public static String getRequestUrlHomeNews() {
        return URL_HOME_NEWS
                + URL_CHAR_QUESTION
                + URL_PARAM_API_KEY2 + API_KEY_HOME_NEWS;
    }

    public static String getRequestUrlTechnologyNews() {
        return URL_TECHNOLOGY_NEWS
                + URL_CHAR_QUESTION
                + URL_PARAM_API_KEY2 + API_KEY_HOME_NEWS;
    }

    public static String getRequestUrlSportsNews() {
        return URL_SPORTS_NEWS
                + URL_CHAR_QUESTION
                + URL_PARAM_API_KEY2 + API_KEY_HOME_NEWS;
    }

    public static String getRequestUrlMagazineNews() {
        return URL_MAGAZINE_NEWS
                + URL_CHAR_QUESTION
                + URL_PARAM_API_KEY2 + API_KEY_HOME_NEWS;
    }
}