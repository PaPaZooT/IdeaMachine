package hr.matvidako.ideamachine.idea;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.base.BaseDataListActivity;

public class IdeaListActivity extends BaseDataListActivity<Idea> implements PopupMenu.OnMenuItemClickListener {

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
        View dialogContentView = getLayoutInflater().inflate(R.layout.dialog_new_idea, null, false);
        final EditText etNewIdea = (EditText) dialogContentView.findViewById(R.id.new_idea);

        final MaterialDialog dialog = createAddNewIdeaDialog(etNewIdea, dialogContentView);
        dialog.show();

        showKeyboard(etNewIdea);
        etNewIdea.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Idea idea = new Idea(etNewIdea.getText().toString());
                    ideaAdapter.add(idea);
                    dialog.dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    private MaterialDialog createAddNewIdeaDialog(final EditText etNewIdea, View dialogContentView) {
        return new MaterialDialog.Builder(this)
                .title(R.string.title_add_idea)
                .customView(dialogContentView, false)
                .positiveText(R.string.add)
                .negativeText(R.string.add_and_next)
                .neutralText(R.string.cancel)
                .autoDismiss(false)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        addNewIdea(etNewIdea.getText().toString());
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        addNewIdea(etNewIdea.getText().toString());
                        etNewIdea.setText("");
                        etNewIdea.requestFocus();
                    }

                    @Override
                    public void onNeutral(MaterialDialog dialog) {
                        dialog.dismiss();
                    }
                }).build();
    }

    private void addNewIdea(String text) {
        Toast toast = Toast.makeText(this, R.string.new_idea_added, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        Idea idea = new Idea(text);
        ideaAdapter.add(idea);
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

}
