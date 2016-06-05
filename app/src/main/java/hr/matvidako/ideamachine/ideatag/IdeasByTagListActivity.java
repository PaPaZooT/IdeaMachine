package hr.matvidako.ideamachine.ideatag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.AddItemDialogBuilder;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.base.BaseDataListActivity;
import hr.matvidako.ideamachine.base.UpdateItemDialogBuilder;
import hr.matvidako.ideamachine.idea.Idea;
import hr.matvidako.ideamachine.idea.IdeaDetailsActivity;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;
import hr.matvidako.ideamachine.tag.Tag;
import hr.matvidako.ideamachine.tag.storage.TagStorage;

public class IdeasByTagListActivity extends BaseDataListActivity<Idea> implements UpdateItemDialogBuilder.OnUpdateListener, AddItemDialogBuilder.OnAddListener {

    private static final String EXTRA_TAG_ID = "tagId";
    private Tag tag;
    private TagStorage tagStorage;
    private IdeaStorage ideaStorage;
    private IdeaByTagAdapter ideaByTagAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagStorage = getApp().getTagStorage();
        ideaStorage = getApp().getIdeaStorage();
        loadDataFromIntent(getIntent());
        setTitle(getString(R.string.tag_ideas, tag.getTitle()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_delete) {
            onDeleteTag();
            return true;
        } else if(id == R.id.action_edit) {
            showUpdateTagDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onDeleteTag() {
        tagStorage.delete(tag);
        finish();
        Toast.makeText(this, getString(R.string.tag_deleted), Toast.LENGTH_SHORT).show();
    }

    private void showUpdateTagDialog() {
        View dialogContentView = getLayoutInflater().inflate(R.layout.dialog_new_item, null, false);
        final EditText etNewIdea = (EditText) dialogContentView.findViewById(R.id.new_item);
        etNewIdea.setText(tag.getTitle());
        UpdateItemDialogBuilder.build(this, etNewIdea, dialogContentView, this).show();
    }

    private void loadDataFromIntent(Intent intent) {
        if(intent == null) {
            return;
        }
        int tagId = intent.getIntExtra(EXTRA_TAG_ID, 0);
        tag = tagStorage.getById(tagId);
    }

    public static Intent buildIntent(Context context, int tagId) {
        Intent i = new Intent(context, IdeasByTagListActivity.class);
        i.putExtra(EXTRA_TAG_ID, tagId);
        return i;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_list;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.menu_tag;
    }

    @Override
    protected BaseDataAdapter<Idea> getAdapter() {
        if(ideaByTagAdapter == null) {
            ideaByTagAdapter = new IdeaByTagAdapter(this, ideaStorage, tag);
        }
        return ideaByTagAdapter;
    }

    @Override
    protected boolean isUsingFab() {
        return true;
    }

    @Override
    protected void onFabClick() {
        AddItemDialogBuilder.build(this, R.string.hint_new_idea, this).show();
    }

    @Override
    protected String getEmptyViewString() {
        return getString(R.string.no_ideas_for_tag, tag.getTitle());
    }

    @Override
    public void onUpdate(String text) {
        tag.setTitle(text);
        tagStorage.update(tag);
        setTitle(getString(R.string.tag_ideas, tag.getTitle()));
    }

    @Override
    public void onItemClick(int position) {
        startActivity(IdeaDetailsActivity.buildIntent(this, (int) getAdapter().getItemId(position)));
    }

    @Override
    public void onAdd(String text) {
        listView.scrollToPosition(0);
        ideaByTagAdapter.add(new Idea(text));
        updateEmptyViewVisibility();
    }

}
