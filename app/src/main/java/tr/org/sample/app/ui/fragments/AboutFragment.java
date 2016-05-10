package tr.org.sample.app.ui.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import tr.org.sample.app.R;
import tr.org.sample.app.data.DataManager;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.AboutPresenter;
import tr.org.sample.app.ui.activities.AboutActivity;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.ui.views.MainMvpView;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 06.04.2016.
 */
public class AboutFragment extends android.support.v4.app.Fragment implements MainMvpView {
    public static String TAG = "HeadlinesFragment";

    @Inject
    public AboutPresenter mMainPresenter;
    @Inject
    public DataManager mDataManager;

    @Bind(R.id.fr_i_image1)
    ImageView image;
    @Bind(R.id.iTitle)
    TextView iTitle;
    @Bind(R.id.iFullText)
    TextView iFullText;

    public AboutFragment() {
    }

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        mMainPresenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_i, container, false);
        ButterKnife.bind(this, view);

        ((AboutActivity)getActivity()).mTabLayout.getTabAt(0).select();

        // bir tek bunda gerekiyor lan!
//        ((AboutItoActivity)getActivity()).mTabLayout.getTabAt(0).getCustomView().findViewById(R.id.text1).setSelected(true);

        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.ic_i);
        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).mToolbarTitle.setText("About");
        ((BaseActivity) getActivity()).mToolbarTitle.setTextSize(28);

        ((BaseActivity) getActivity()).addShareAction(false);

        About about = mDataManager.getAbout();

        final Typeface type = Typeface.createFromAsset(getContext().getAssets(), Utils.BOLD_FONT);
        final Typeface type1 = Typeface.createFromAsset(getContext().getAssets(), Utils.REGULAR_FONT);

        Picasso.with(getContext()).load(about.getImage()).into(image);
        iTitle.setText(about.getTitle());
        iFullText.setText(about.getDesc());

        iTitle.setTypeface(type);
        iFullText.setTypeface(type1);

        return view;
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
