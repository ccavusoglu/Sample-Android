package tr.org.sample.app.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import timber.log.Timber;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.LoginPresenter;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.ui.views.MainMvpView;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 06.04.2016.
 */
public class LoginFragment extends android.support.v4.app.Fragment implements MainMvpView {
    public static String TAG = "LoginFragment";
    @Inject
    public LoginPresenter loginPresenter;

    @Bind(R.id.btn_login)
    Button login;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @DebugLog
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        loginPresenter.attachView(this);
    }

    @DebugLog
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("CLCIK");
                ((BaseActivity) getActivity()).bringOnlineOpsFragment();
            }
        });

        return view;
    }

    @Override
    public void showHeadlines(List<Headline> headlines) {

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
