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
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.LoginPresenter;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.ui.adapters.OnlineOpsAdapter;
import tr.org.sample.app.ui.views.MainMvpView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 06.04.2016.
 */
public class OnlineOpsFragment extends android.support.v4.app.Fragment implements MainMvpView {
    public static String TAG = "OnlineOpsFragment";
    @Inject
    public LoginPresenter mPresenter;
    @Inject
    public OnlineOpsAdapter moOnlineOpsAdapter;

    @Bind(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    public OnlineOpsFragment() {
    }

    public static OnlineOpsFragment newInstance() {
        OnlineOpsFragment fragment = new OnlineOpsFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        mPresenter.attachView(this);
        mPresenter.loadOnlineOps();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_do, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setAdapter(moOnlineOpsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.bottom_login);
        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).mToolbarTitle.setText("Online");
        ((BaseActivity) getActivity()).mToolbarTitle.setTextSize(28);

        ((BaseActivity) getActivity()).addShareAction(false);

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
        moOnlineOpsAdapter.setEvents(companies);
        moOnlineOpsAdapter.notifyDataSetChanged();
    }
}
