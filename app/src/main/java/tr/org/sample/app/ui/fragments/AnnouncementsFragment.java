package tr.org.sample.app.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.MainPresenter;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.ui.activities.MainActivity;
import tr.org.sample.app.ui.adapters.AnnouncementsAdapter;
import tr.org.sample.app.ui.views.MainMvpView;

import javax.inject.Inject;
import java.util.List;

public class AnnouncementsFragment extends android.support.v4.app.Fragment implements MainMvpView {
    public static String TAG = "AnnouncementFragment";

    @Bind(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    public MainPresenter mMainPresenter;

    @Inject
    public AnnouncementsAdapter announcementsAdapter;

    public AnnouncementsFragment() {
    }

    public static AnnouncementsFragment newInstance() {
        AnnouncementsFragment fragment = new AnnouncementsFragment();
        return fragment;
    }

    @DebugLog
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        mMainPresenter.attachView(this);
        mMainPresenter.loadAnnouncements();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_announcement, container, false);
        ButterKnife.bind(this, view);


        ((MainActivity) getActivity()).mTabLayout.getTabAt(2).select();

//        mTabLayout.refreshDrawableState();
        mRecyclerView.setAdapter(announcementsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.ic_announcement_white_48dp);
        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).mToolbarTitle.setText("Announcements");
        ((BaseActivity) getActivity()).mToolbarTitle.setTextSize(28);

        ((BaseActivity) getActivity()).addShareAction(false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void showHeadlines(List<Headline> headlines) {

    }

    @Override
    public void showAnnouncements(List<Headline> newses) {
        announcementsAdapter.setAnnouncements(newses);
        announcementsAdapter.notifyDataSetChanged();
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
