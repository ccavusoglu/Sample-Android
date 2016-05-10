package tr.org.sample.app.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.MainPresenter;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.ui.activities.MainActivity;
import tr.org.sample.app.ui.adapters.HeadlinesAdapter;
import tr.org.sample.app.ui.views.MainMvpView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 06.04.2016.
 */
public class HeadlinesFragment extends android.support.v4.app.Fragment implements MainMvpView {
    public static String TAG = "HeadlinesFragment";
    @Inject
    public MainPresenter mMainPresenter;
    @Inject
    public HeadlinesAdapter mHeadlinesAdapter;

    @Bind(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    public HeadlinesFragment() {
    }

    public static HeadlinesFragment newInstance() {
        HeadlinesFragment fragment = new HeadlinesFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        mHeadlinesAdapter.attachAdapter(this);

        mMainPresenter.attachView(this);
        mMainPresenter.loadHeadlines();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline, container, false);
        ButterKnife.bind(this, view);

        ((MainActivity) getActivity()).mTabLayout.getTabAt(0).select();

        // bir tek bunda gerekiyor lan!
        ((MainActivity) getActivity()).mTabLayout.getTabAt(0).getCustomView().findViewById(R.id.text1).setSelected(true);

//        mTabLayout.refreshDrawableState();
        mRecyclerView.setAdapter(mHeadlinesAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.bottom_news);
        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).mToolbarTitle.setText("News");
        ((BaseActivity) getActivity()).mToolbarTitle.setTextSize(28);
        ((BaseActivity) getActivity()).mToolbarTitle.setGravity(Gravity.CENTER);

        ((BaseActivity) getActivity()).addShareAction(false);

        return view;
    }

    public void bringDetail(Headline headline) {
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, HeadlineDetailFragment.newInstance(headline), HeadlineDetailFragment.TAG);
        ft.addToBackStack(HeadlineDetailFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.GONE);
        ((BaseActivity) getActivity()).mToolbarTitle.setText(headline.title);
        ((BaseActivity) getActivity()).mToolbarTitle.setTextSize(18);
        ((BaseActivity) getActivity()).mToolbarTitle.setGravity(Gravity.START | Gravity.CENTER);

        ((BaseActivity) getActivity()).mBottomBarLayout.setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).addShareAction(true);

        ((BaseActivity) getActivity()).activeHeadline = headline;

//        getActivity().findViewById(R.id.drawer_layout1).invalidate();
//        getActivity().findViewById(R.id.main_content).invalidate();

        ft.commit();
    }

    public void bringShareSheet(Headline headline) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TITLE, headline.title);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "http://www.google.com");
        startActivity(sharingIntent);
    }

    @Override
    public void showHeadlines(List<Headline> newses) {
        mHeadlinesAdapter.setNews(newses);
        mHeadlinesAdapter.notifyDataSetChanged();
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
