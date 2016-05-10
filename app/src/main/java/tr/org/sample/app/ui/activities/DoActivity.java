package tr.org.sample.app.ui.activities;

import android.os.Bundle;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.presenters.DoPresenter;
import tr.org.sample.app.ui.views.LoginMvpView;

import javax.inject.Inject;

public class DoActivity extends BaseActivity implements LoginMvpView {
    @Inject
    DoPresenter mDoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do);

        getActivityComponent().inject(this);

        bringDoFragment();

        mToolbarTitle.setTypeface(toolbarTitleType);
        mToolbarTitle.setText("Do?");
        mToolbarIcon.setBackgroundResource(R.drawable.ic_help_white_48dp);

        mDoButton.setSelected(true);
        mDoButton.setImageResource(R.drawable.bottom2pressed);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDoPresenter.detachView();
    }
}
