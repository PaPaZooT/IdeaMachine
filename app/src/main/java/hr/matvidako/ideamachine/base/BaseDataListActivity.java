package hr.matvidako.ideamachine.base;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import butterknife.InjectView;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.db.Data;

public abstract class BaseDataListActivity<T extends Data> extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    @InjectView(R.id.list)
    protected ListView listView;
    @InjectView(R.id.empty_list)
    protected TextView emptyIdeaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupListView();
        if(isUsingFab()) {
            setupFab();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab) {
            onFabClick();
        }
    }

    private void setupListView() {
        listView.setOnItemClickListener(this);
        listView.setEmptyView(emptyIdeaList);
        listView.setAdapter(getAdapter());
    }

    private void setupFab() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButton.attachToListView(listView);
        floatingActionButton.setOnClickListener(this);
    }

    protected abstract BaseDataAdapter<T> getAdapter();

    protected abstract boolean isUsingFab();

    protected abstract void onFabClick();
}
