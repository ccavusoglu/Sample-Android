package tr.org.sample.app.infrastructure.di.components;

import android.content.Context;
import dagger.Component;
import tr.org.sample.app.data.DataManager;
import tr.org.sample.app.data.local.PreferencesHelper;
import tr.org.sample.app.infrastructure.di.modules.ApplicationModule;
import tr.org.sample.app.infrastructure.di.scopes.ApplicationContext;

import javax.inject.Singleton;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    DataManager dataManager();
    PreferencesHelper preferencesHelper();

    @ApplicationContext Context context();
}
