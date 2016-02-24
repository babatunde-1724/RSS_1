package framgia.rss_feeds.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class NewsEntity implements Parcelable {
    public static final Creator<NewsEntity> CREATOR = new Creator<NewsEntity>() {
        public NewsEntity createFromParcel(Parcel in) {
            return new NewsEntity(in);
        }

        public NewsEntity[] newArray(int size) {
            return new NewsEntity[size];
        }
    };

    private String section;
    private String subSection;
    private String title;
    private String abstracts;
    private String url;
    private String byline;
    private String item_type;
    private Date updated_date;
    private Date created_date;
    private Date published_date;
    private String material_type_facet;
    private String kicker;
    private String imageUrl;

    public NewsEntity() {
    }

    public NewsEntity(Parcel in) {
        section = in.readString();
        subSection = in.readString();
        title = in.readString();
        abstracts = in.readString();
        url = in.readString();
        byline = in.readString();
        item_type = in.readString();
        material_type_facet = in.readString();
        kicker = in.readString();
        imageUrl = in.readString();
        long dateMillis=in.readLong();
        published_date = (dateMillis == -1 ? null : new Date(dateMillis));
        long dateMillis1=in.readLong();
        created_date = (dateMillis1 == -1 ? null : new Date(dateMillis1));
        long dateMillis2=in.readLong();
        updated_date = (dateMillis2 == -1 ? null : new Date(dateMillis2));
    }

    public NewsEntity(String section, String subSection, String title, String abstracts, String url, String byline, String item_type, Date updated_date, Date created_date, Date published_date, String material_type_facet, String kicker, String imageUrl)
    {
        this.section = section;
        this.subSection = subSection;
        this.title = title;
        this.abstracts = abstracts;
        this.url = url;
        this.byline = byline;
        this.item_type = item_type;
        this.updated_date = updated_date;
        this.created_date = created_date;
        this.published_date = published_date;
        this.material_type_facet = material_type_facet;
        this.kicker = kicker;
        this.imageUrl = imageUrl;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubSection() {
        return subSection;
    }

    public Date getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(Date updated_date) {
        this.updated_date = updated_date;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getPublished_date() {
        return published_date;
    }

    public void setPublished_date(Date published_date) {
        this.published_date = published_date;
    }

    public void setSubSection(String subSection) {
        this.subSection = subSection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getMaterial_type_facet() {
        return material_type_facet;
    }

    public void setMaterial_type_facet(String material_type_facet) {
        this.material_type_facet = material_type_facet;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return
        "\nsection: " + section +
            "\nsubSection " + subSection +
            "\ntitle " + title +
            "\nabstract " + abstracts +
            "\nurl " + url +
            "\nbyline " + byline +
            "\nitemType " + item_type +
            "\nupdatedDate " + updated_date +
            "\ncreatedDate " + created_date +
            "\npublishedDate " + published_date +
            "\nmaterialType " + material_type_facet +
            "\nkicker " + kicker +
            "\nimageUrl " + imageUrl +
            "\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(section);
        dest.writeString(subSection);
        dest.writeString(title);
        dest.writeString(abstracts);
        dest.writeString(url);
        dest.writeString(byline);
        dest.writeString(item_type);
        dest.writeString(material_type_facet);
        dest.writeString(kicker);
        dest.writeString(imageUrl);
        dest.writeLong(updated_date == null ? -1 : updated_date.getTime());
        dest.writeLong(created_date == null ? -1 : created_date.getTime());
        dest.writeLong(published_date == null ? -1 : published_date.getTime());
    }
}