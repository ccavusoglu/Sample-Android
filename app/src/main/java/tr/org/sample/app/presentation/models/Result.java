package tr.org.sample.app.presentation.models;

import retrofit2.http.HEAD;

import java.util.ArrayList;

/**
 * Created by Q on 10.05.2016.
 */
public class Result {
    public String count;
    public String next;
    public String previous;

    public ArrayList<Headline> results;
}
