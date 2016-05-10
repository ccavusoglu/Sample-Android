package tr.org.sample.app.ui.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import tr.org.sample.app.R;
import tr.org.sample.app.infrastructure.di.scopes.ApplicationContext;
import tr.org.sample.app.presentation.models.Do;
import tr.org.sample.app.presentation.models.Headline;
import tr.org.sample.app.ui.activities.BaseActivity;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;

/**
 * Created by ccavusoglu on 06.04.2016.
 */
public class DoDetailFragment extends android.support.v4.app.Fragment {
    @Inject
    @ApplicationContext
    Context context;

    public static String TAG = "DoDetailFragment";
    private final Headline mHeadline;

    @Bind(R.id.fr_do_detail_image)
    ImageView imageView;
    @Bind(R.id.fr_do_detail_title)
    TextView title;
    @Bind(R.id.fr_do_detail_text)
    TextView text;

    public DoDetailFragment(Headline headline) {
        mHeadline = headline;
    }

    public static DoDetailFragment newInstance(Headline headline) {
        DoDetailFragment fragment = new DoDetailFragment(headline);
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
        View view = inflater.inflate(R.layout.fragment_do_detail, container, false);
        ButterKnife.bind(this, view);

        final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.REGULAR_FONT);
        title.setTypeface(type);
        text.setTypeface(type);

        // TODO: image taşınacak!
        Picasso.with(context).load("http://i.imgur.com/20aKboh.jpg?1").into(imageView);
        title.setText(mHeadline.title);
        text.setText(mHeadline.desc);

        return view;
    }
}
