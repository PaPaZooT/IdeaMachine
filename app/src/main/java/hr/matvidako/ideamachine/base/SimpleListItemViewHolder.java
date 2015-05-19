package hr.matvidako.ideamachine.base;

import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.BaseDataAdapter;

public class SimpleListItemViewHolder extends BaseDataAdapter.ViewHolder {
    @InjectView(R.id.title)
    public TextView title;

    public SimpleListItemViewHolder(View view) {
        super(view);
    }
}
