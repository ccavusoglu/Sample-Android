package tr.org.sample.app.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import tr.org.sample.app.R;
import tr.org.sample.app.infrastructure.di.scopes.ApplicationContext;
import tr.org.sample.app.presentation.models.Company;
import tr.org.sample.app.presentation.models.Headline;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class CompaniesAdapter extends RecyclerView.Adapter<CompaniesAdapter.NewsViewHolder> {
    @Inject
    @ApplicationContext
    Context context;

    private List<Headline> mNewses;

    @Inject
    public CompaniesAdapter() {
        mNewses = new ArrayList<>();
    }

    public void setNews(List<Headline> newses) {
        mNewses = newses;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_company, parent, false);
        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        Headline headline = mNewses.get(position);
        holder.titleTextView.setText(headline.title);
    }

    @Override
    public int getItemCount() {
        return mNewses.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_name)
        TextView titleTextView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.BOLD_FONT);
            titleTextView.setTypeface(type);
        }
    }
}
