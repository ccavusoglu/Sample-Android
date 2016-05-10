package tr.org.sample.app.infrastructure.di.components;

import dagger.Component;
import tr.org.sample.app.infrastructure.di.modules.ActivityModule;
import tr.org.sample.app.infrastructure.di.scopes.PerActivity;
import tr.org.sample.app.ui.activities.*;
import tr.org.sample.app.ui.fragments.*;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
    void inject(DoActivity activity);
    void inject(AboutActivity activity);
    void inject(ContactActivity activity);

    void inject(HeadlinesFragment fragment);
    void inject(HeadlineDetailFragment fragment);
    void inject(AnnouncementsFragment fragment);
    void inject(EventsFragment fragment);
    void inject(CompaniesFragment fragment);
    void inject(LoginFragment fragment);
    void inject(DoFragment fragment);
    void inject(DoDetailFragment fragment);
    void inject(OnlineOpsFragment fragment);
    void inject(AboutFragment fragment);
    void inject(ContactFragment fragment);
}
