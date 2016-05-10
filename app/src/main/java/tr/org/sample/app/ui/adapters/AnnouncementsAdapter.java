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
import hugo.weaving.DebugLog;
import tr.org.sample.app.R;
import tr.org.sample.app.infrastructure.di.scopes.ApplicationContext;
import tr.org.sample.app.presentation.models.Announcement;
import tr.org.sample.app.presentation.models.Headline;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class AnnouncementsAdapter extends RecyclerView.Adapter<AnnouncementsAdapter.AnnouncementsViewHolder> {
    @Inject
    @ApplicationContext
    Context context;

    private List<Headline> mAnnouncements;

    @Inject
    public AnnouncementsAdapter() {
        mAnnouncements = new ArrayList<>();
    }

    public void setAnnouncements(List<Headline> announcements) {
        mAnnouncements = announcements;
    }

    @Override
    public AnnouncementsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_announcement, parent, false);
        return new AnnouncementsViewHolder(itemView);
    }

    @DebugLog
    @Override
    public void onBindViewHolder(AnnouncementsViewHolder holder, int position) {
        Headline announcement = mAnnouncements.get(position);
        holder.titleTextView.setText(announcement.title);
        holder.dateTextView.setText(announcement.date);
    }

    @Override
    public int getItemCount() {
        return mAnnouncements.size();
    }

    class AnnouncementsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_name)
        TextView titleTextView;

        @Bind(R.id.item_date)
        TextView dateTextView;

        public AnnouncementsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.BOLD_FONT);
            titleTextView.setTypeface(type);
            dateTextView.setTypeface(type);
        }
    }
}
