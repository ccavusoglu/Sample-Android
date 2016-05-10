package tr.org.sample.app.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Q on 10.05.2016.
 */
public class Headline implements Parcelable {
    public String image = "http://i.imgur.com/AL5jXz2.jpg";

    public String title;

    @SerializedName("episode_id")
    public String episodeId;

    @SerializedName("opening_crawl")
    public String desc;

    @SerializedName("release_date")
    public String date;

    public String director;
    public String producer;
    public String url;
    public String created;
    public String edited;

    @SerializedName("species")
    public ArrayList<String> speciesUrls;

    @SerializedName("starships")
    public ArrayList<String> starshipsUrls;

    @SerializedName("vehicles")
    public ArrayList<String> vehiclesUrls;

    @SerializedName("planets")
    public ArrayList<String> planetsUrls;

    @SerializedName("characters")
    public ArrayList<String> charactersUrls;

    protected Headline(Parcel in) {
        title = in.readString();
        director = in.readString();
        producer = in.readString();
        url = in.readString();
        created = in.readString();
        edited = in.readString();
        speciesUrls = in.createStringArrayList();
        starshipsUrls = in.createStringArrayList();
        vehiclesUrls = in.createStringArrayList();
        planetsUrls = in.createStringArrayList();
        charactersUrls = in.createStringArrayList();
    }

    public static final Creator<Headline> CREATOR = new Creator<Headline>() {
        @Override
        public Headline createFromParcel(Parcel in) {
            return new Headline(in);
        }

        @Override
        public Headline[] newArray(int size) {
            return new Headline[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(director);
        dest.writeString(producer);
        dest.writeString(url);
        dest.writeString(created);
        dest.writeString(edited);
        dest.writeStringList(speciesUrls);
        dest.writeStringList(starshipsUrls);
        dest.writeStringList(vehiclesUrls);
        dest.writeStringList(planetsUrls);
        dest.writeStringList(charactersUrls);
    }
}