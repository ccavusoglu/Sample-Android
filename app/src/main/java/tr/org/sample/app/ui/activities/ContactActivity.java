package tr.org.sample.app.ui.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.ContactPresenter;
import tr.org.sample.app.ui.views.MainMvpView;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.List;

public class ContactActivity extends BaseActivity implements MainMvpView {
    @Inject
    ContactPresenter mMainPresenter;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        getActivityComponent().inject(this);

        bringContactFragment();

        mContactButton.setSelected(true);
        mContactButton.setImageResource(R.drawable.bottom5pressed);

        mToolbarTitle.setTypeface(toolbarTitleType);
        mToolbarTitle.setText("Contact");
        mToolbarIcon.setBackgroundResource(R.drawable.bottom_contact);

        mTabLayout.addTab(mTabLayout.newTab().setText("Contact"), 0);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 2"), 1);
        mTabLayout.addTab(mTabLayout.newTab().setText("Tab 3"), 2);

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
