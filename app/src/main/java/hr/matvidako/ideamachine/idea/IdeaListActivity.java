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

public class IdeaListActivity extends BaseDataListActivity<Idea> implements PopupMenu.OnMenuItemClickListener, AddItemDialogBuilder.OnAddListener {

    private IdeaAdapter ideaAdapter;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getString(R.string.ideas));
    }

    @Override
    protected BaseDataAdapter<Idea> getAdapter() {
        if(ideaAdapter == null) {
            ideaAdapter = new IdeaAdapter(this, IdeaApplication.getInstance().getIdeaStorage());
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
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_idea);
        popupMenu.show();
        selectedPosition = position;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete) {
            ideaAdapter.remove(ideaAdapter.getItem(selectedPosition));
        }
        return true;
    }

    private void showAddNewIdeaDialog() {
        View dialogContentView = getLayoutInflater().inflate(R.layout.dialog_new_item, null, false);
        final EditText etNewIdea = (EditText) dialogContentView.findViewById(R.id.new_item);
        etNewIdea.setHint(R.string.hint_new_idea);
        AddItemDialogBuilder.build(this, etNewIdea, dialogContentView, this).show();
    }

    @Override
    public void onAdd(String text) {
        addNewIdea(text);
    }

    private void addNewIdea(String text) {
        Toast toast = Toast.makeText(this, R.string.new_idea_added, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        ideaAdapter.add(new Idea(text));
    }


}
