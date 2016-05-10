package tr.org.sample.app.presentation.presenters;

import tr.org.sample.app.ui.views.MvpView;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
