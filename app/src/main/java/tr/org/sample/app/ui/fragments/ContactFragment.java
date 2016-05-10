package tr.org.sample.app.ui.fragments;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import butterknife.Bind;
import butterknife.ButterKnife;
import tr.org.sample.app.R;
import tr.org.sample.app.presentation.models.*;
import tr.org.sample.app.presentation.presenters.ContactPresenter;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.ui.views.MainMvpView;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by ccavusoglu on 06.04.2016.
 */
public class ContactFragment extends android.support.v4.app.Fragment implements MainMvpView {
    public static String TAG = "ContactFragment";
    @Inject
    public ContactPresenter mContactPresenter;

    @Bind(R.id.t1)
    TextView t1;
    @Bind(R.id.t11)
    TextView t11;
    @Bind(R.id.t2)
    TextView t2;
    @Bind(R.id.t22)
    TextView t22;
    @Bind(R.id.t3)
    TextView t3;
    @Bind(R.id.t33)
    TextView t33;
    @Bind(R.id.t4)
    TextView t4;
    @Bind(R.id.t44)
    TextView t44;
    @Bind(R.id.t5)
    TextView t5;
    @Bind(R.id.t55)
    TextView t55;
    @Bind(R.id.t6)
    TextView t6;
    @Bind(R.id.t66)
    TextView t66;
    @Bind(R.id.b1)
    ImageButton b1;
    @Bind(R.id.b2)
    Button b2;

    public ContactFragment() {
    }

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity) getActivity()).getActivityComponent().inject(this);

        mContactPresenter.attachView(this);
        mContactPresenter.loadHeadlines();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);

        ((BaseActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((BaseActivity) getActivity()).mToolbarIcon.setBackgroundResource(R.drawable.bottom_contact);
        ((BaseActivity) getActivity()).mToolbarIcon.setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).mToolbarTitle.setText("Contact");
        ((BaseActivity) getActivity()).mToolbarTitle.setTextSize(28);

        ((BaseActivity) getActivity()).addShareAction(false);

        final Typeface type = Typeface.createFromAsset(getContext().getAssets(), Utils.REGULAR_FONT);
        final Typeface type1 = Typeface.createFromAsset(getContext().getAssets(), Utils.BOLD_FONT);

        t1.setTypeface(type1);
        t2.setTypeface(type1);
        t3.setTypeface(type1);
        t4.setTypeface(type1);
        t5.setTypeface(type1);
        t6.setTypeface(type1);
        t11.setTypeface(type);
        t22.setTypeface(type);
        t33.setTypeface(type);
        t44.setTypeface(type);
        t55.setTypeface(type);
        t66.setTypeface(type);

        t1.setText("Phone");
        t11.setText("000 0 000");
        t2.setText("Phone 2");
        t22.setText("0212 000 00 00");
        t3.setText("Fax");
        t33.setText("0212 000 00 00");
        t4.setText("E-Mail");
        t44.setText("0@0.com");
        t5.setText("Phone 3");
        t55.setText("000 0 000");
        t6.setText("Adress");
        t66.setText("Istanbul");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlAddress = "http://maps.google.com/maps?z=16&q=43.0166833,42.9759031(Istanbul)";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, urlAddress);
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                startActivity(sendIntent);
            }
        });

        b2.setText("Show on Google Maps");
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:43.0166833,42.9759031?z=16&q=43.0166833,42.9759031(Istanbul)");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void bringShareSheet(Headline headline) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TITLE, headline.title);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.google.com");
        startActivity(sharingIntent);
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
