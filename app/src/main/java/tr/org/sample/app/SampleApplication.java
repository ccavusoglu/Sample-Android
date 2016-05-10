package tr.org.sample.app;

import android.app.Application;
import android.content.Context;
import timber.log.Timber;
import tr.org.sample.app.infrastructure.di.components.ApplicationComponent;
import tr.org.sample.app.infrastructure.di.components.DaggerApplicationComponent;
import tr.org.sample.app.infrastructure.di.modules.ApplicationModule;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class SampleApplication extends Application {
    ApplicationComponent mApplicationComponent;

    public static SampleApplication get(Context context) {
        return (SampleApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            //Fabric.with(this, new Crashlytics());
        }

//        SystemClock.sleep(1000);
    }


    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
