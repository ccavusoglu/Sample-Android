package tr.org.sample.app.presentation.presenters;

import hugo.weaving.DebugLog;
import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;
import tr.org.sample.app.data.DataManager;
import tr.org.sample.app.presentation.models.Do;
import tr.org.sample.app.presentation.models.Result;
import tr.org.sample.app.ui.views.MainMvpView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class DoPresenter extends BasePresenter<MainMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public DoPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
    }


    @DebugLog
    public void loadDoes() {
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
                            getMvpView().showDoes(announcements.results);
                        } else {
                            getMvpView().showDoes(announcements.results);
                        }
                    }
                });
    }
}
