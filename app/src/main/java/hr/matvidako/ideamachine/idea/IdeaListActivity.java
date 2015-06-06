package hr.matvidako.ideamachine.idea;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.AddItemDialogBuilder;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.base.BaseDataListActivity;

public class IdeaListActivity extends BaseDataListActivity<Idea> implements AddItemDialogBuilder.OnAddListener {

    private IdeaAdapter ideaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getString(R.string.ideas));
    }

    @Override
    protected BaseDataAdapter<Idea> getAdapter() {
        if(ideaAdapter == null) {
            ideaAdapter = new IdeaAdapter(this, getApp().getIdeaStorage());
        }
        return ideaAdapter;
    }

    @Override
    protected boolean isUsingFab() {
        return true;
    }

    @Override
    protected void onFabClick() {
        showAddNewIdeaDialog();
    }

    @Override
    protected int getEmptyViewStringResId() {
        return R.string.no_ideas;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_idea_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(IdeaDetailsActivity.buildIntent(this, (int)ideaAdapter.getItemId(position)));
    }

    private void showAddNewIdeaDialog() {
        AddItemDialogBuilder.build(this, R.string.hint_new_idea, this).show();
    }

    @Override
    public void onAdd(String text) {
        ideaAdapter.add(new Idea(text));
    }

}
