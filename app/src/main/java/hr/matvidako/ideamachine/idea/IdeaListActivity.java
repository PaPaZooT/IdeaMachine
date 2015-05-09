package hr.matvidako.ideamachine.idea;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.melnykov.fab.FloatingActionButton;
import hr.matvidako.ideamachine.ActionBarListActivity;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.idea.storage.DummyIdeaStorage;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class IdeaListActivity extends ActionBarListActivity implements View.OnClickListener, AdapterView.OnItemClickListener, PopupMenu.OnMenuItemClickListener {

    private IdeaAdapter ideaAdapter;
    private IdeaStorage ideaStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFab();
        setupAdapter();
        getListView().setOnItemClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void setupAdapter() {
        ideaStorage = new DummyIdeaStorage();
        ideaAdapter = new IdeaAdapter(this, ideaStorage.loadAll());
        setListAdapter(ideaAdapter);
    }

    private void setupFab() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add_idea);
        floatingActionButton.attachToListView(getListView());
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_idea_list;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab_add_idea) {
            showCreateNewIdeaDialog();
        }
    }

    private void showCreateNewIdeaDialog() {
        View dialogContent = getLayoutInflater().inflate(R.layout.dialog_new_idea, null, false);
        final EditText etNewIdea = (EditText) dialogContent.findViewById(R.id.new_idea);
        showKeyboard(etNewIdea);

        new MaterialDialog.Builder(this)
                .title(R.string.title_add_idea)
                .customView(dialogContent, false)
                .positiveText(R.string.add)
                .negativeText(R.string.add_and_next)
                .neutralText(R.string.cancel)
                .autoDismiss(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        ideaAdapter.addIdea(etNewIdea.getText().toString());
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        ideaAdapter.addIdea(etNewIdea.getText().toString());
                        etNewIdea.setText("");
                        etNewIdea.requestFocus();
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void showKeyboard(final View viewInFocus) {
        viewInFocus.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                viewInFocus.requestFocus();
                mgr.showSoftInput(viewInFocus, InputMethodManager.SHOW_IMPLICIT);
            }
        }, 100);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.menu_idea);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_edit) {
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_delete) {
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
