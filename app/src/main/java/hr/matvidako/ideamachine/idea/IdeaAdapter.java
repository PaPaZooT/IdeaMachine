package hr.matvidako.ideamachine.idea;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.R;
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
        return new IdeaViewHolder(convertView);
    }

    static class IdeaViewHolder extends BaseDataAdapter.ViewHolder<Idea> {
        @InjectView(R.id.title)
        public TextView title;

        public IdeaViewHolder(View view) {
            super(view);
        }

        @Override
        public void update(Idea item) {
            title.setText(item.getContent());
        }
    }

}
