package hr.matvidako.ideamachine.tag;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.Storage;

public class TagAdapter extends BaseDataAdapter<Tag> {

    public TagAdapter(Context context, Storage<Tag> storage) {
        super(context, storage);
    }

    @Override
    protected int getListItemLayoutResId() {
        return R.layout.list_item_simple;
    }

    @Override
    protected ViewHolder createViewHolder(View convertView) {
        return new TagViewHolder(convertView);
    }

    static class TagViewHolder extends BaseDataAdapter.ViewHolder<Tag> {
        @InjectView(R.id.title)
        public TextView title;

        public TagViewHolder(View view) {
            super(view);
        }

        @Override
        public void update(Tag item) {
            title.setText(item.getTitle());
        }
    }

}
