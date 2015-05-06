package hr.matvidako.ideamachine.idea;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import hr.matvidako.ideamachine.ActionBarListActivity;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.exercise.Exercise;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;
import hr.matvidako.ideamachine.idea.storage.PrefsIdeaStorage;

public class IdeaListActivity extends ActionBarListActivity implements View.OnClickListener, AdapterView.OnItemClickListener, PopupMenu.OnMenuItemClickListener {

    private Exercise exercise;
    private IdeaAdapter ideaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exercise = IntentBuilder.load(getIntent());
        setTitle(exercise.title);
        setupFab();
        setupListHeader();
        setupAdapter();
        getListView().setOnItemClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        List<Idea> ideas = ideaAdapter.getItems();
        IdeaStorage ideaStorage = new PrefsIdeaStorage(this);
        ideaStorage.storeIdeas(exercise.id, ideas);
    }

    private void setupAdapter() {
        IdeaStorage ideaStorage = new PrefsIdeaStorage(this);
        ideaAdapter = new IdeaAdapter(this, ideaStorage.loadIdeas(exercise.id));
        setListAdapter(ideaAdapter);
    }

    private void setupFab() {
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add_idea);
        floatingActionButton.attachToListView(getListView());
        floatingActionButton.setOnClickListener(this);
    }

    private void setupListHeader() {
        TextView headerDescription = (TextView) getLayoutInflater().inflate(R.layout.header_exercise_description, null, false);
        headerDescription.setText(exercise.description);
        getListView().addHeaderView(headerDescription, null, false);
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

    public static class IntentBuilder {
        private static final String EXTRA_EXERCISE = "EXTRA_EXERCISE";

        public static Intent build(Activity activity, Exercise exercise) {
            Intent intent = new Intent(activity, IdeaListActivity.class);
            intent.putExtra(EXTRA_EXERCISE, exercise);
            return intent;
        }
        public static Exercise load(Intent intent) {
            Exercise exercise = (Exercise) intent.getSerializableExtra(EXTRA_EXERCISE);
            return exercise;
        }
    }

}
