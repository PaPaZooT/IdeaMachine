package hr.matvidako.ideamachine.base;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.InjectView;
import butterknife.Optional;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.db.Data;
import hr.matvidako.ideamachine.view.DividerItemDecoration;

public abstract class BaseDataListFragment<T extends Data> extends Fragment implements BaseDataAdapter.OnItemClickListener, View.OnClickListener {

    @InjectView(R.id.empty_list) protected TextView emptyListView;
    @InjectView(R.id.list) protected RecyclerView listView;

    @Override
    public void onResume() {
        super.onResume();
        setupListView();
        refreshAdapter();
        updateEmptyViewVisibility();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab) {
            onFabClick();
        }
    }

    private void setupListView() {
        Context context = getActivity();
        emptyListView.setText(getEmptyViewStringResId());
        listView.setLayoutManager(new LinearLayoutManager(context));
        listView.addItemDecoration(new DividerItemDecoration(context, R.drawable.abc_list_divider_mtrl_alpha));
        listView.setAdapter(getAdapter());
        getAdapter().setOnItemClickListener(this);
        listView.setItemAnimator(new DefaultItemAnimator());
    }

    private void refreshAdapter() {
        getAdapter().refresh();
    }

    protected void updateEmptyViewVisibility() {
        if(getAdapter().items.isEmpty()) {
            emptyListView.setVisibility(View.VISIBLE);
        } else {
            emptyListView.setVisibility(View.GONE);
        }
    }

    protected abstract BaseDataAdapter<T> getAdapter();

    protected abstract void onFabClick();

    protected abstract int getEmptyViewStringResId();

}
