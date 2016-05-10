package tr.org.sample.app.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.DoPresenter;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.ui.adapters.DoAdapter;
import tr.org.sample.app.ui.views.MainMvpView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 06.04.2016.
 */
public class DoFragment extends android.support.v4.app.Fragment implements MainMvpView {
    public static String TAG = "DoFragment";
    @Inject
    public DoPresenter mDoPresenter;
    @Inject
    public DoAdapter mDoAdapter;

    @Bind(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    public DoFragment() {
    }

    public static DoFragment newInstance() {
        DoFragment fragment = new DoFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        mDoPresenter.attachView(this);
        mDoPresenter.loadDoes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_do, container, false);
        ButterKnife.bind(this, view);

        mRecyclerView.setAdapter(mDoAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.bottom_question);
        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).mToolbarTitle.setText("Do?");
        ((BaseActivity) getActivity()).mToolbarTitle.setTextSize(28);
        ((BaseActivity) getActivity()).mToolbarTitle.setGravity(Gravity.CENTER);

        ((BaseActivity) getActivity()).addShareAction(false);

        mDoAdapter.attachAdapter(this);

        return view;
    }

    public void bringDetail(Headline headline) {
        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.do_content, DoDetailFragment.newInstance(headline), DoDetailFragment.TAG);
        ft.addToBackStack(DoDetailFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.GONE);
        ((BaseActivity) getActivity()).mToolbarTitle.setText(headline.title);
        ((BaseActivity) getActivity()).mToolbarTitle.setTextSize(18);
        ((BaseActivity) getActivity()).mToolbarTitle.setGravity(Gravity.START | Gravity.CENTER);
        ((BaseActivity) getActivity()).mBottomBarLayout.setVisibility(View.VISIBLE);

//        ((BaseActivity) getActivity()).addShareAction(true);

        LinearLayout view1 = (LinearLayout) getActivity().findViewById(R.id.layout_bottom_bar);
        view1.setTranslationY(0);

        ft.commit();
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
        mDoAdapter.setEvents(companies);
        mDoAdapter.notifyDataSetChanged();
    }

    @Override
    public void showOnlineOps(List<Headline> companies) {

    }
}
