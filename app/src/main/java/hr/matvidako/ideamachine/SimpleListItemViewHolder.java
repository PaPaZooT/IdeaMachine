package hr.matvidako.ideamachine;

import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;

public class SimpleListItemViewHolder extends BaseDataAdapter.ViewHolder {
    @InjectView(R.id.title)
    public TextView title;

    public SimpleListItemViewHolder(View view) {
        super(view);
    }
}
