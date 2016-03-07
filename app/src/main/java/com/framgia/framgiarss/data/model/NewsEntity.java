package com.framgia.framgiarss.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsEntity implements Parcelable {
    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        public NewsEntity createFromParcel(Parcel in) {
            return new NewsEntity(in);
        }

        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    private String guid;
    private String link;
    private String title;
    private String description;
    private String category;
    private String author;
    private String published_date;
    private String comments;
    private String enclosure;

    public NewsEntity() {
    }

    public NewsEntity(Parcel in) {
        guid = in.readString();
        link = in.readString();
        title = in.readString();
        description = in.readString();
        category = in.readString();
        author = in.readString();
        comments = in.readString();
        enclosure = in.readString();
        published_date = in.readString();
    }

    public NewsEntity(String guid, String link, String title, String description, String url, String category, String author, String published_date, String comments, String enclosure)
    {
        this.guid = guid;
        this.link = link;
        this.title = title;
        this.description = description;
        this.category = category;
        this.author = author;
        this.published_date = published_date;
        this.comments = comments;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
            "\ncomments " + comments +
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
        dest.writeString(comments);
        dest.writeString(enclosure);
        dest.writeString(published_date);
    }
}