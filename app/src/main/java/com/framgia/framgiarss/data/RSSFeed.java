package com.framgia.framgiarss.data;

import android.os.Parcel;
import android.os.Parcelable;

public class RSSFeed implements Parcelable {
    public static final Creator<RSSFeed> CREATOR = new Creator<RSSFeed>() {
        public RSSFeed createFromParcel(Parcel in) {
            return new RSSFeed(in);
        }

        public RSSFeed[] newArray(int size) {
            return new RSSFeed[size];
        }
    };

    private String guid;
    private String link;
    private String title;
    private String description;
    private String category;
    private String author;
    private String published_date;
    private String enclosure;

    public RSSFeed() {
    }

    public RSSFeed(Parcel in) {
        guid = in.readString();
        link = in.readString();
        title = in.readString();
        description = in.readString();
        category = in.readString();
        author = in.readString();
        enclosure = in.readString();
        published_date = in.readString();
    }

    public RSSFeed(String guid, String link, String title, String description, String category, String author, String published_date, String enclosure) {
        this.guid = guid;
        this.link = link;
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
        this.published_date = published_date;
        this.enclosure = enclosure;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLink() {
        return link;
    }

    public String getPublished_date() {
        return published_date;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    @Override
    public String toString() {
        return
                "\nguid: " + guid +
                        "\nlink " + link +
                        "\ntitle " + title +
                        "\nabstract " + description +
                        "\ncategory " + category +
                        "\nitemType " + author +
                        "\npublishedDate " + published_date +
                        "\nenclosure " + enclosure +
                        "\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(guid);
        dest.writeString(link);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(category);
        dest.writeString(author);
        dest.writeString(enclosure);
        dest.writeString(published_date);
    }
}