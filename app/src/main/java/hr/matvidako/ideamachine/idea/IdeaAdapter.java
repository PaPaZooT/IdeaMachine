package hr.matvidako.ideamachine.idea;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hr.matvidako.ideamachine.BaseDataAdapter;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.SimpleListItemViewHolder;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class IdeaAdapter extends BaseDataAdapter<Idea> {

    public IdeaAdapter(Context context, IdeaStorage ideaStorage) {
        super(context, ideaStorage);
    }

    @Override
    protected int getListItemLayoutResId() {
        return R.layout.list_item_simple;
    }

    @Override
    protected BaseDataAdapter.ViewHolder createViewHolder(View convertView) {
        return new SimpleListItemViewHolder(convertView);
    }

    @Override
    protected void updateView(BaseDataAdapter.ViewHolder viewHolder, Idea item) {
        SimpleListItemViewHolder myViewHolder= (SimpleListItemViewHolder) viewHolder;
        myViewHolder.title.setText(item.getContent());
    }

}
