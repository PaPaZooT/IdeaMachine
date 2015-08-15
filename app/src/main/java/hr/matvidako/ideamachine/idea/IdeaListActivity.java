package hr.matvidako.ideamachine.idea;

import android.content.Intent;
import android.os.Bundle;
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
        handleIntent(getIntent());
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action)) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            showAddNewIdeaDialog(sharedText);
        }
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
        showAddNewIdeaDialog("");
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
    public void onItemClick(int position) {
        startActivity(IdeaDetailsActivity.buildIntent(this, getAdapter().getItem(position).getId()));
    }

    private void showAddNewIdeaDialog(String startingText) {
        AddItemDialogBuilder.build(this, R.string.hint_new_idea, startingText, this).show();
    }

    @Override
    public void onAdd(String text) {
        listView.scrollToPosition(0);
        ideaAdapter.add(new Idea(text));
    }

}
