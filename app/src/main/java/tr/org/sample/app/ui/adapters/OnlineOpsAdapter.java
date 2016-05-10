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
import tr.org.sample.app.presentation.models.Headline;
import tr.org.sample.app.presentation.models.OnlineOps;
import tr.org.sample.app.util.Utils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccavusoglu on 23.03.2016.
 */
public class OnlineOpsAdapter extends RecyclerView.Adapter<OnlineOpsAdapter.DoViewHolder> {
    @Inject
    @ApplicationContext
    Context context;

    private List<Headline> mEvents;

    @Inject
    public OnlineOpsAdapter() {
        mEvents = new ArrayList<>();
    }

    public void setEvents(List<Headline> newses) {
        mEvents = newses;
    }

    @Override
    public DoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_onlineops, parent, false);
        return new DoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DoViewHolder holder, int position) {
        Headline event = mEvents.get(position);
        holder.textView.setText(event.title);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    class DoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_name)
        TextView textView;

        public DoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            final Typeface type = Typeface.createFromAsset(context.getAssets(), Utils.BOLD_FONT);
            textView.setTypeface(type);
        }
    }
}
