package tr.org.sample.app.presentation.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ccavusoglu on 12.04.2016.
 */
public class Company {
    @SerializedName("Title")
    public String title;

    public Company(String name) {
        this.title = name;
    }

    public String getTitle() {
        return title;
    }
}
