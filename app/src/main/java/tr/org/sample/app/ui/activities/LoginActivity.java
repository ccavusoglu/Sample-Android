package tr.org.sample.app.ui.activities;

import android.os.Bundle;
import android.view.View;
import timber.log.Timber;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.presenters.LoginPresenter;
import tr.org.sample.app.ui.views.LoginMvpView;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginMvpView, View.OnClickListener {
    @Inject
    LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getActivityComponent().inject(this);

        bringLoginFragment();

        mToolbarTitle.setTypeface(toolbarTitleType);
        mToolbarTitle.setText("Login");
        mToolbarIcon.setBackgroundResource(R.drawable.bottom_login);

        mLoginButton.setSelected(true);
        mLoginButton.setImageResource(R.drawable.bottom4pressed);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLoginPresenter.detachView();
    }

    @Override
    public void onClick(View v) {
        Timber.d("CLICKED");
        if(v.getId() == R.id.btn_login) {
            bringOnlineOpsFragment();
        }
    }
}
