package tr.org.sample.app.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
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
import tr.org.sample.app.presentation.models.Event;
import tr.org.sample.app.presentation.models.Headline;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    @Inject
    @ApplicationContext
    Context context;

    private List<Headline> mEvents;

    @Inject
    public EventsAdapter() {
        mEvents = new ArrayList<>();
    }

    public void setEvents(List<Headline> newses) {
        mEvents = newses;
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_event, parent, false);
        return new EventsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        Headline event = mEvents.get(position);
        Picasso.with(context).load("http://i.imgur.com/2kXkLXU.jpg?1").into(holder.coverImage);
        holder.titleTextView.setText(event.title);
        holder.locTextView.setText(event.episodeId);
        holder.dateTextView.setText(event.date);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    class EventsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.cv_image)
        ImageView coverImage;
        @Bind(R.id.cv_event_title)
        TextView titleTextView;
        @Bind(R.id.item_location)
        TextView locTextView;
        @Bind(R.id.item_date)
        TextView dateTextView;

        public EventsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.REGULAR_FONT);
            final Typeface type1 = Typeface.createFromAsset(context.getAssets(), Utils.BOLD_FONT);
            titleTextView.setTypeface(type1);
            locTextView.setTypeface(type);
            dateTextView.setTypeface(type);
        }
    }
}
