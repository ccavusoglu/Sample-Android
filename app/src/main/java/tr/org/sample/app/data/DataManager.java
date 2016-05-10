package tr.org.sample.app.data;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tr.org.sample.app.data.api.ApiService;
import tr.org.sample.app.data.api.ServiceFactory;
import tr.org.sample.app.data.local.PreferencesHelper;
import tr.org.sample.app.presentation.models.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
@Singleton
public class DataManager {
    ApiService apiService;
    PreferencesHelper preferencesHelper;

    @Inject
    public DataManager(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    public Observable<Result> getHeadlines(String s) {
        if (apiService == null)
            apiService = ServiceFactory.createService(ApiService.class, ApiService.SERVICE_ENDPOINT);

        return apiService.getHeadlines()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Result> getEvents(String s) {
        if (apiService == null)
            apiService = ServiceFactory.createService(ApiService.class, ApiService.SERVICE_ENDPOINT);

        return apiService.getHeadlines()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Announcement>> getAnnouncements(String s) {
        if (apiService == null)
            apiService = ServiceFactory.createService(ApiService.class, ApiService.SERVICE_ENDPOINT);

        return apiService.getAnnouncements()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Company>> getCompanies(String s) {
        if (apiService == null)
            apiService = ServiceFactory.createService(ApiService.class, ApiService.SERVICE_ENDPOINT);

        return apiService.getCompanies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<Do>> getDoes(String s) {
        if (apiService == null)
            apiService = ServiceFactory.createService(ApiService.class, ApiService.SERVICE_ENDPOINT);

        return apiService.getDoes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<OnlineOps>> getOnlineOps(String s) {
        if (apiService == null)
            apiService = ServiceFactory.createService(ApiService.class, ApiService.SERVICE_ENDPOINT);

        return apiService.getOnlineOps()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public About getAbout() {
        return new About("http://i.imgur.com/JDg2k24.png?1",
                "Lorem Ipsum", " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget facilisis velit. Phasellus lacinia nibh et arcu feugiat porttitor. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Morbi sit amet sem sit amet ante auctor facilisis. Integer massa leo, tincidunt sit amet placerat ac, finibus eget felis. Sed auctor convallis tellus vel tincidunt. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Donec efficitur feugiat lacinia. Suspendisse bibendum erat vitae fermentum suscipit. Suspendisse eget nunc accumsan, imperdiet ex at, volutpat nisi. Aliquam sed risus sagittis, dapibus nisi quis, finibus ante. Morbi dignissim arcu augue, vitae sodales nulla eleifend vitae. Maecenas convallis tellus neque, sed commodo mauris gravida venenatis.\n" +
                "\n" +
                "Integer sagittis mi vitae massa tristique posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer imperdiet dignissim sem in aliquam. Etiam fringilla pretium mi, id sodales metus pretium sit amet. Fusce nec consectetur mauris. Sed venenatis ligula nec elit volutpat, sed cursus sem dapibus. Etiam et bibendum quam, non auctor dui. Maecenas ut ultricies nulla. Praesent eu ullamcorper neque. Curabitur vulputate maximus mattis. Morbi dolor magna, sodales id iaculis vitae, pretium vel lectus. Duis vel diam ipsum. Maecenas at fermentum sapien, quis mollis turpis. Vestibulum ut faucibus purus. ");
    }

    public List<Items> getItems() {
        ArrayList<Items> list = new ArrayList<Items>();

        list.add(new Items("http://i.imgur.com/xnnbo5I.png",
                "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum"));
        list.add(new Items("http://i.imgur.com/xnnbo5I.png",
                "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum"));
        list.add(new Items("http://i.imgur.com/xnnbo5I.png",
                "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum"));

        return list;
    }
}