package tr.org.sample.app.data.api;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import tr.org.sample.app.presentation.models.*;

import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public interface ApiService {
    String SERVICE_ENDPOINT = "http://swapi.co/api/";

    @GET("films")
    Observable<Result> getHeadlines();

    @GET("films")
    Observable<List<Event>> getEvents();

    @GET("films")
    Observable<List<Announcement>> getAnnouncements();

    @GET("films")
    Observable<List<Company>> getCompanies();

    @GET("films")
    Observable<List<Do>> getDoes();

    @GET("films")
    Observable<List<OnlineOps>> getOnlineOps();
}
