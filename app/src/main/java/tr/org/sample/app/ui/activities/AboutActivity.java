package tr.org.sample.app.ui.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import timber.log.Timber;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.AboutPresenter;
import tr.org.sample.app.ui.views.MainMvpView;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.List;

public class AboutActivity extends BaseActivity implements MainMvpView {
    @Bind(R.id.tab_layout)
    public TabLayout mTabLayout;
    @Inject
    AboutPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getActivityComponent().inject(this);

        bringAboutFragment();

        mIButton.setSelected(true);
        mIButton.setImageResource(R.drawable.bottom3pressed);

        mToolbarTitle.setTypeface(toolbarTitleType);
        mToolbarTitle.setText("About");
        mToolbarIcon.setBackgroundResource(R.drawable.ic_i);

        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 1"), 0);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"), 1);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"), 2);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 4"), 3);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 5"), 4);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 6"), 5);

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
                    bringAboutFragment();
                else if (tab.getPosition() == 1)
                    bringAboutFragment();
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
    protected void onDestroy() {
        super.onDestroy();

        mPresenter.detachView();
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
