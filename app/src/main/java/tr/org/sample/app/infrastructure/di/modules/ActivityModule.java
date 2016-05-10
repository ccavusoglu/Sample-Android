package tr.org.sample.app.infrastructure.di.modules;

import android.app.Activity;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import tr.org.sample.app.infrastructure.di.scopes.ActivityContext;
import tr.org.sample.app.ui.fragments.HeadlinesFragment;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    HeadlinesFragment provideHeadlinesFragment() {
        return new HeadlinesFragment();
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
