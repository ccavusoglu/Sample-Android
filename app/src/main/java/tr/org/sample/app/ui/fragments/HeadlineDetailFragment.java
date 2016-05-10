package tr.org.sample.app.ui.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import tr.org.sample.app.R;
import tr.org.sample.app.infrastructure.di.scopes.ApplicationContext;
import tr.org.sample.app.presentation.models.Headline;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.ui.activities.MainActivity;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;

/**
 * Created by ccavusoglu on 06.04.2016.
 */
public class HeadlineDetailFragment extends android.support.v4.app.Fragment {
    @Inject
    @ApplicationContext
    Context context;

    public static String TAG = "HeadlineDetailFragment";
    private final Headline mHeadline;

    @Bind(R.id.fr_headline_detail_image)
    ImageView imageView;
    @Bind(R.id.headlineTitle)
    TextView headlineTitle;
    @Bind(R.id.headlineFullText)
    TextView headlineText;

    public HeadlineDetailFragment(Headline headline) {
        mHeadline = headline;
    }

    public static HeadlineDetailFragment newInstance(Headline headline) {
        HeadlineDetailFragment fragment = new HeadlineDetailFragment(headline);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BaseActivity) getActivity()).getActivityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline_detail, container, false);
        ButterKnife.bind(this, view);

        final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.REGULAR_FONT);
        headlineTitle.setTypeface(type);
        headlineText.setTypeface(type);

        // TODO: image taşınacak!
        Picasso.with(context).load("http://i.imgur.com/AL5jXz2.jpg?1").into(imageView);
        headlineTitle.setText(mHeadline.title);
        headlineText.setText(mHeadline.desc);

        LinearLayout view1 = (LinearLayout) getActivity().findViewById(R.id.layout_bottom_bar);
        view1.setTranslationY(0);

        ((MainActivity) getActivity()).mTabLayout.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
