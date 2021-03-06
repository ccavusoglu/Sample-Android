package tr.org.sample.app.presentation.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ccavusoglu on 16.04.2016.
 */
public class OnlineOps {
    @SerializedName("Image")
    String image;
    @SerializedName("Title")
    String title;
    @SerializedName("Desc")
    String desc;
    public OnlineOps(String title, String desc) {
        this.title = title;
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
