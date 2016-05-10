package tr.org.sample.app.ui.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;
import tr.org.sample.app.R;
import tr.org.sample.app.SampleApplication;
import tr.org.sample.app.infrastructure.di.components.ActivityComponent;
import tr.org.sample.app.infrastructure.di.components.DaggerActivityComponent;
import tr.org.sample.app.infrastructure.di.modules.ActivityModule;
import tr.org.sample.app.presentation.models.Headline;
import tr.org.sample.app.ui.fragments.*;
import tr.org.sample.app.util.Utils;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int ANIM_DURATION_TOOLBAR = 300;

    @Bind(R.id.toolbar_title)
    public TextView mToolbarTitle;

    @Bind(R.id.toolbar_icon)
    public ImageView mToolbarIcon;

    public MenuItem mMenu;

    @Bind(R.id.layout_bottom_bar)
    public LinearLayout mBottomBarLayout;

    // TODO: find a better place!
    public Headline activeHeadline;
    protected boolean mPendingIntroAnimation;
    protected Typeface toolbarTitleType;

    @Bind(R.id.app_bar)
    protected Toolbar mToolbar;

    @Bind(R.id.bottom_bar_news)
    ImageButton mNewsButton;

    @Bind(R.id.bottom_bar_do)
    ImageButton mDoButton;

    @Bind(R.id.bottom_bar_i)
    ImageButton mIButton;

    @Bind(R.id.bottom_bar_login)
    ImageButton mLoginButton;

    @Bind(R.id.bottom_bar_contactus)
    ImageButton mContactButton;

    private ActivityComponent mActivityComponent;
    private boolean shareAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        init();
    }

    protected void init() {
        toolbarTitleType = Typeface.createFromAsset(getAssets(), "fonts/LatoTR-Bold.ttf");
        mToolbarTitle.setTypeface(toolbarTitleType);

        setSupportActionBar(mToolbar);

        mBottomBarLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBottomBar(v);
            }
        });

        mDoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBottomBar(v);
            }
        });

        mIButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBottomBar(v);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBottomBar(v);
            }
        });

        mContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBottomBar(v);
            }
        });
    }

    public void onClickBottomBar(View v) {
        if (v.isSelected())
            return;

        // TODO: selector!
        mNewsButton.setSelected(false);
        mDoButton.setSelected(false);
        mIButton.setSelected(false);
        mLoginButton.setSelected(false);
        mContactButton.setSelected(false);

        v.setSelected(true);

        // TODO: kötü burası. komple selector geçmek gerek.
        mNewsButton.setImageResource(R.drawable.bottom1);
        mDoButton.setImageResource(R.drawable.bottom2);
        mIButton.setImageResource(R.drawable.bottom3);
        mLoginButton.setImageResource(R.drawable.bottom4);
        mContactButton.setImageResource(R.drawable.bottom5);

        switch (v.getId()) {
            case R.id.bottom_bar_news:
                mNewsButton.setImageResource(R.drawable.bottom1pressed);
                if (!(this instanceof MainActivity)) {
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    bringHeadlinesFragment();
                }
                break;
            case R.id.bottom_bar_do:
                mDoButton.setImageResource(R.drawable.bottom2pressed);
                if (!(this instanceof DoActivity)) {
                    startActivity(new Intent(this, DoActivity.class));
                } else {
                    bringDoFragment();
                }
                break;
            case R.id.bottom_bar_i:
                mIButton.setImageResource(R.drawable.bottom3pressed);
                if (!(this instanceof AboutActivity)) {
                    startActivity(new Intent(this, AboutActivity.class));
                } else {
                    bringAboutFragment();
                }
                break;
            case R.id.bottom_bar_login:
                mLoginButton.setImageResource(R.drawable.bottom4pressed);
                if (!(this instanceof LoginActivity)) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    bringLoginFragment();
                }
                break;
            case R.id.bottom_bar_contactus:
                mContactButton.setImageResource(R.drawable.bottom5pressed);
                if (!(this instanceof ContactActivity)) {
                    startActivity(new Intent(this, ContactActivity.class));
                } else {
                    bringContactFragment();
                }
                break;
            default:
                break;
        }
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(SampleApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mPendingIntroAnimation) {
            mPendingIntroAnimation = false;
            startIntroAnimation();
        }

        if (mMenu == null) {
            getMenuInflater().inflate(R.menu.menu_main, menu);
            mMenu = menu.findItem(R.id.share_action);
            mMenu.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    private void startIntroAnimation() {
        int actionbarSize = Utils.dpToPx(56);
        mToolbar.setTranslationY(-actionbarSize);
//        mLoginItem.getActionView().setTranslationY(-actionbarSize);

        mToolbar.animate()
                .translationY(0)
                .setDuration(ANIM_DURATION_TOOLBAR)
                .setStartDelay(300);
//        mLoginItem.getActionView().animate()
//                .translationY(0)
//                .setDuration(ANIM_DURATION_TOOLBAR)
//                .setStartDelay(400)
//                .setListener(new AnimatorListenerAdapter() {
//                    @Override
//                    public void onAnimationEnd(Animator animation) {
//                        startContentAnimation();
//                    }
//                })
//                .start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.share_action:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TITLE, activeHeadline.title);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "http://www.google.com");
                startActivity(sharingIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        // TODO: FIND SMTH ELSE!
        mNewsButton.setSelected(false);
        mDoButton.setSelected(false);
        mIButton.setSelected(false);
        mLoginButton.setSelected(false);
        mContactButton.setSelected(false);

        // TODO: kötü burası. komple selector geçmek gerek.
        mNewsButton.setImageResource(R.drawable.bottom1);
        mDoButton.setImageResource(R.drawable.bottom2);
        mIButton.setImageResource(R.drawable.bottom3);
        mLoginButton.setImageResource(R.drawable.bottom4);
        mContactButton.setImageResource(R.drawable.bottom5);

        if (this instanceof MainActivity) {
            mNewsButton.setSelected(true);
            mNewsButton.setImageResource(R.drawable.bottom1pressed);
        } else if (this instanceof DoActivity) {
            mDoButton.setSelected(true);
            mDoButton.setImageResource(R.drawable.bottom2pressed);
        } else if (this instanceof AboutActivity) {
            mIButton.setSelected(true);
            mIButton.setImageResource(R.drawable.bottom3pressed);
        } else if (this instanceof LoginActivity) {
            mLoginButton.setSelected(true);
            mLoginButton.setImageResource(R.drawable.bottom4pressed);
        } else if (this instanceof ContactActivity) {
            mContactButton.setSelected(true);
            mContactButton.setImageResource(R.drawable.bottom5pressed);
        }
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            String name = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1).getName();

            Fragment fragment = getSupportFragmentManager().findFragmentByTag(name);

            getFragmentManager().popBackStack();

            // TODO: FIND SMTH ELSE!
            mNewsButton.setSelected(false);
            mDoButton.setSelected(false);
            mIButton.setSelected(false);
            mLoginButton.setSelected(false);
            mContactButton.setSelected(false);

            // TODO: kötü burası. komple selector geçmek gerek.
            mNewsButton.setImageResource(R.drawable.bottom1);
            mDoButton.setImageResource(R.drawable.bottom2);
            mIButton.setImageResource(R.drawable.bottom3);
            mLoginButton.setImageResource(R.drawable.bottom4);
            mContactButton.setImageResource(R.drawable.bottom5);

            if (fragment instanceof HeadlinesFragment || fragment instanceof EventsFragment || fragment instanceof AnnouncementsFragment || fragment instanceof CompaniesFragment) {
                mNewsButton.setSelected(true);
                mNewsButton.setImageResource(R.drawable.bottom1pressed);
            } else if (fragment instanceof DoFragment || fragment instanceof DoDetailFragment) {
                mDoButton.setSelected(true);
                mDoButton.setImageResource(R.drawable.bottom2pressed);
            } else if (fragment instanceof AboutFragment) {
                mIButton.setSelected(true);
                mIButton.setImageResource(R.drawable.bottom3pressed);
            } else if (fragment instanceof LoginFragment || fragment instanceof OnlineOpsFragment) {
                mLoginButton.setSelected(true);
                mLoginButton.setImageResource(R.drawable.bottom4pressed);
            } else if (fragment instanceof ContactFragment) {
                mContactButton.setSelected(true);
                mContactButton.setImageResource(R.drawable.bottom5pressed);
            }

        } else {
            super.onBackPressed();
        }
    }

    @DebugLog
    void bringLoginFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.login_content, LoginFragment.newInstance(), LoginFragment.TAG);
//        ft.addToBackStack(LoginFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ft.commit();
    }

    @DebugLog
    void bringContactFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.contact_content, ContactFragment.newInstance(), ContactFragment.TAG);
//        ft.addToBackStack(ContactFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ft.commit();
    }

    @DebugLog
    void bringDoFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.do_content, DoFragment.newInstance(), DoFragment.TAG);
//        ft.addToBackStack(DoFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ft.commit();
    }

    @DebugLog
    public void bringOnlineOpsFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.login_content, OnlineOpsFragment.newInstance(), OnlineOpsFragment.TAG);
//        ft.addToBackStack(OnlineOpsFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ft.commit();
    }

    @DebugLog
    public void bringAboutFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.i_content, AboutFragment.newInstance(), AboutFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ft.commit();
    }

    @DebugLog
    void bringSettingsFragment() {

    }

    public void bringCompaniesFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, CompaniesFragment.newInstance(), CompaniesFragment.TAG);
//        ft.addToBackStack(CompaniesFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ft.commit();
    }

    public void bringEventsFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, EventsFragment.newInstance(), EventsFragment.TAG);
//        ft.addToBackStack(EventsFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ft.commit();
    }

    public void bringAnnouncementsFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, AnnouncementsFragment.newInstance(), AnnouncementsFragment.TAG);
//        ft.addToBackStack(AnnouncementsFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        ft.commit();
    }

    @DebugLog
    public void bringHeadlinesFragment() {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.main_content, HeadlinesFragment.newInstance(), HeadlinesFragment.TAG);
//        ft.addToBackStack(HeadlinesFragment.TAG);
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right);

//        if (getSupportFragmentManager().getBackStackEntryCount() != 0) {
//            Timber.d("Fragments on stack!");
//            String tag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
//
//            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
//
//            if (!(fragment instanceof NewsFragment)) {
//                ft.replace(R.id.main_content, NewsFragment.newInstance(), NewsFragment.TAG);
//                ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
//            }
//        } else {
//            ft.add(R.id.main_content, NewsFragment.newInstance(), NewsFragment.TAG);
//            ft.addToBackStack(NewsFragment.TAG);
//        }
// alternatively add it with a tag
//        ft.add(R.id.main_content, NewsFragment.newInstance(), NewsFragment.TAG);
//
//// replace
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.fragment_placeholder, new YourFragment());
//        ft.commit();
//
//// remove
//        Fragment fragment = fm.findFragmentById(R.id.your_placehodler);
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.remove(fragment);
//        ft.commit();

        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    public void addShareAction(boolean add) {
        if (mMenu == null)
            return;

        if (add) {
            mMenu.setVisible(true);
//            invalidateOptionsMenu();
        } else {
            mMenu.setVisible(false);
//            invalidateOptionsMenu();
        }
    }
}

