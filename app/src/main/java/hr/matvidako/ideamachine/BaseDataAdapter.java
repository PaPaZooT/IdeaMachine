package hr.matvidako.ideamachine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import hr.matvidako.ideamachine.db.Data;

public abstract class BaseDataAdapter<T extends Data> extends BaseAdapter {

    protected List<T> items = new ArrayList<>();
    protected LayoutInflater layoutInflater;
    protected Storage<T> storage;

    public BaseDataAdapter(Context context, Storage<T> storage) {
        this.storage = storage;
        layoutInflater = LayoutInflater.from(context);
        this.items.addAll(storage.getAll());
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public void add(T item) {
        storage.create(item);
        items.add(0, item);
        notifyDataSetChanged();
    }

    public void remove(T item) {
        storage.delete(item);
        items.remove(item);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(getListItemLayoutResId(), null, false);
            viewHolder = createViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        updateView(viewHolder, getItem(position));
        return convertView;
    }

    protected abstract int getListItemLayoutResId();
    protected abstract ViewHolder createViewHolder(View convertView);

    protected abstract void updateView(ViewHolder viewHolder, T item);

    protected static class ViewHolder {
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }

}
