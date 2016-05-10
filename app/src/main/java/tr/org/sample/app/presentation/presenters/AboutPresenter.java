package tr.org.sample.app.presentation.presenters;

import rx.Subscription;
import tr.org.sample.app.data.DataManager;
import tr.org.sample.app.ui.views.MainMvpView;

import javax.inject.Inject;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class AboutPresenter extends BasePresenter<MainMvpView> {
    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public AboutPresenter(DataManager dataManager) {
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

}
