package tr.org.sample.app.ui.views;

import tr.org.sample.app.presentation.models.*;

import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public interface MainMvpView extends MvpView {
    void showHeadlines(List<Headline> headlines);
    void showAnnouncements(List<Headline> announcements);
    void showEvents(List<Headline> events);
    void showCompanies(List<Headline> companies);

    void showDoes(List<Headline> companies);
    void showOnlineOps(List<Headline> companies);
}
