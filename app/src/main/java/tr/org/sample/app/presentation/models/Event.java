package tr.org.sample.app.presentation.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ccavusoglu on 12.04.2016.
 */
public class Event {
    @SerializedName("Image")
    private String image;
    @SerializedName("Title")
    private String title;
    @SerializedName("Desc")
    private String desc;
    @SerializedName("Date")
    private String date;
    @SerializedName("Location")
    public String location;

    public Event(String date, String desc, String image, String location, String title) {
        this.date = date;
        this.desc = desc;
        this.image = image;
        this.location = location;
        this.title = title;
    }

    public String getLoc() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getDesc() {
        return desc;
    }

    public String  getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }
}
