package tr.org.sample.app.presentation.presenters;

import hugo.weaving.DebugLog;
import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;
import tr.org.sample.app.data.DataManager;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.ui.views.MainMvpView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class ContactPresenter extends BasePresenter<MainMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public ContactPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    @DebugLog
    public void loadHeadlines() {
        checkViewAttached();
        mSubscription = mDataManager.getHeadlines("1")
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the announcements.");
                    }

                    @Override
                    public void onNext(Result headlines) {
                        if (headlines != null) {
                            getMvpView().showHeadlines(headlines.results);
                        } else {
                            getMvpView().showHeadlines(headlines.results);
                        }
                    }
                });
    }

    @DebugLog
    public void loadEvents() {
        checkViewAttached();
        mSubscription = mDataManager.getEvents("3")
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the announcements.");
                    }

                    @Override
                    public void onNext(Result events) {
                        if (events != null) {
                            getMvpView().showEvents(events.results);
                        } else {
                            getMvpView().showEvents(events.results);
                        }
                    }
                });
    }

    @DebugLog
    public void loadAnnouncements() {
        checkViewAttached();
        mSubscription = mDataManager.getHeadlines("3")
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the announcements.");
                    }

                    @Override
                    public void onNext(Result announcements) {
                        if (announcements != null) {
                            getMvpView().showAnnouncements(announcements.results);
                        } else {
                            getMvpView().showAnnouncements(announcements.results);
                        }
                    }
                });
    }

    @DebugLog
    public void loadCompanies() {
        checkViewAttached();
        mSubscription = mDataManager.getHeadlines("3")
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the announcements.");
                    }

                    @Override
                    public void onNext(Result companies) {
                        if (companies != null) {
                            getMvpView().showCompanies(companies.results);
                        } else {
                            getMvpView().showCompanies(companies.results);
                        }
                    }
                });
    }
}
