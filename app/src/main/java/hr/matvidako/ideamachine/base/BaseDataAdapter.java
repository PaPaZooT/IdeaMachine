package hr.matvidako.ideamachine.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import hr.matvidako.ideamachine.Storage;
import hr.matvidako.ideamachine.db.Data;

public abstract class BaseDataAdapter<T extends Data> extends RecyclerView.Adapter<BaseDataAdapter.ViewHolder> implements View.OnClickListener {

    protected List<T> items = new ArrayList<>();
    protected LayoutInflater layoutInflater;
    protected Storage<T> storage;
    private OnItemClickListener onItemClickListener;

    public BaseDataAdapter(Context context, Storage<T> storage) {
        this.storage = storage;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(getListItemLayoutResId(), parent, false);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position, getItem(position), this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

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
        notifyItemInserted(0);
    }

    public void remove(T item) {
        storage.delete(item);
        refresh();
    }

    public void refresh() {
        items.clear();
        items.addAll(getAllItems());
        super.notifyDataSetChanged();
    }

    protected List<T> getAllItems() {
        return storage.getAll();
    }

    protected abstract int getListItemLayoutResId();
    protected abstract ViewHolder createViewHolder(View convertView);

    @Override
    public void onClick(View v) {
        if(onItemClickListener != null) {
            int position = (int) v.getTag();
            onItemClickListener.onItemClick(position);
        }
    }

    protected static abstract class ViewHolder<S> extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }

        public void bind(int position, S item, View.OnClickListener onClickListener) {
            itemView.setOnClickListener(onClickListener);
            itemView.setTag(position);
            update(item);
        }

        public abstract void update(S item);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }

}
