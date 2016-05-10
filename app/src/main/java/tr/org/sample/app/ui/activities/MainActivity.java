package tr.org.sample.app.ui.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import timber.log.Timber;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.MainPresenter;
import tr.org.sample.app.ui.adapters.HeadlinesAdapter;
import tr.org.sample.app.ui.views.MainMvpView;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.List;

public class MainActivity extends BaseActivity implements MainMvpView {
    @Bind(R.id.tab_layout)
    public
    TabLayout mTabLayout;
    @Inject
    MainPresenter mMainPresenter;
    @Inject
    HeadlinesAdapter mHeadlinesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        mNewsButton.setSelected(true);
        mNewsButton.setImageResource(R.drawable.bottom1pressed);

        bringHeadlinesFragment();

        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"), 0);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"), 1);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"), 2);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 4"), 3);

//        mTabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            RelativeLayout relativeLayout = (RelativeLayout)
                    LayoutInflater.from(this).inflate(R.layout.tab_layout, mTabLayout, false);
            tab.setCustomView(relativeLayout);

            TextView tabTextView = (TextView) relativeLayout.findViewById(R.id.text1);
            tabTextView.setText(tab.getText());

            final Typeface type = Typeface.createFromAsset(getAssets(), Utils.LIGHT_FONT);
            (tabTextView).setTypeface(type);
        }

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Timber.d("SELECT");
                if (tab.getPosition() == 0)
                    bringHeadlinesFragment();
                else if (tab.getPosition() == 1)
                    bringEventsFragment();
                else if (tab.getPosition() == 2)
                    bringAnnouncementsFragment();
                else if (tab.getPosition() == 3)
                    bringCompaniesFragment();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Timber.d("RESLSECT");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mTabLayout.setVisibility(View.VISIBLE);
//            findViewById(R.id.drawer_layout1).invalidate();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            mTabLayout.setVisibility(View.VISIBLE);
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    @Override
    public void showHeadlines(List<Headline> newses) {

    }

    @Override
    public void showAnnouncements(List<Headline> announcements) {

    }

    @Override
    public void showEvents(List<Headline> events) {

    }

    @Override
    public void showCompanies(List<Headline> companies) {

    }

    @Override
    public void showDoes(List<Headline> companies) {

    }

    @Override
    public void showOnlineOps(List<Headline> companies) {

    }
}
