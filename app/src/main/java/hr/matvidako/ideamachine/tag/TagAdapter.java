package hr.matvidako.ideamachine.tag;

import android.content.Context;
import android.view.View;
import hr.matvidako.ideamachine.BaseDataAdapter;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.SimpleListItemViewHolder;
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
        return new SimpleListItemViewHolder(convertView);
    }

    @Override
    protected void updateView(ViewHolder viewHolder, Tag item) {
        SimpleListItemViewHolder myViewHolder= (SimpleListItemViewHolder) viewHolder;
        myViewHolder.title.setText(item.getTitle());
    }

}
