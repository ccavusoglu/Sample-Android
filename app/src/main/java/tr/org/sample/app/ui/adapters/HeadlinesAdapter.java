package tr.org.sample.app.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import tr.org.sample.app.R;
import tr.org.sample.app.infrastructure.di.scopes.ApplicationContext;
import tr.org.sample.app.presentation.models.Headline;
import tr.org.sample.app.ui.fragments.HeadlinesFragment;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class HeadlinesAdapter extends RecyclerView.Adapter<HeadlinesAdapter.HeadlinesViewHolder> {
    @Inject
    @ApplicationContext
    Context context;

    List<Headline> mHeadlines;
    private HeadlinesFragment mFragment;

    @Inject
    public HeadlinesAdapter() {
        mHeadlines = new ArrayList<>();
    }

    public void setNews(List<Headline> newses) {
        mHeadlines = newses;
    }

    @Override
    public HeadlinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_headline, parent, false);
        return new HeadlinesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HeadlinesViewHolder holder, int position) {
        Headline headline = mHeadlines.get(position);
        Picasso.with(context).load("http://i.imgur.com/AL5jXz2.jpg?1").into(holder.coverImage);
        holder.titleTextView.setText(headline.title);
        holder.descTextView.setText(headline.desc);
        holder.dateTextView.setText(headline.date);
    }

    @Override
    public int getItemCount() {
        return mHeadlines.size();
    }

    public void attachAdapter(HeadlinesFragment headlinesFragment) {
        mFragment = headlinesFragment;
    }

    class HeadlinesViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.cv_image)
        ImageView coverImage;
        @Bind(R.id.cv_headline)
        TextView titleTextView;
        @Bind(R.id.cv_partial)
        TextView descTextView;
        @Bind(R.id.cv_date)
        TextView dateTextView;
        @Bind(R.id.cv_headline_cont)
        TextView mContTextView;
        @Bind(R.id.cv_headline_share)
        ImageButton mShareButton;

        public HeadlinesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.REGULAR_FONT);
            final Typeface type1 = Typeface.createFromAsset(context.getAssets(), Utils.LIGHT_FONT);
            titleTextView.setTypeface(type);
            descTextView.setTypeface(type1);
            dateTextView.setTypeface(type);
            mContTextView.setTypeface(type);
            mContTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFragment.bringDetail(mHeadlines.get(getLayoutPosition()));
                }
            });

            mShareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mFragment.bringShareSheet(mHeadlines.get(getLayoutPosition()));
                }
            });
        }
    }
}
