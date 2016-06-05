package hr.matvidako.ideamachine.idea;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.AddItemDialogBuilder;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.base.BaseDataListFragment;

public class IdeaListFragment extends BaseDataListFragment<Idea> implements AddItemDialogBuilder.OnAddListener {

    private IdeaAdapter ideaAdapter;
    @InjectView(R.id.idea_content) EditText ideaEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_idea_list, container, false);
        ButterKnife.inject(this, view);
        setAddIdeaEditorActionListener();
        return view;
    }

    private void setAddIdeaEditorActionListener() {
        ideaEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addIdea();
                    return true;
                }
                return false;
            }
        });
    }

    //TODO
    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_SEND.equals(action)) {
            String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
            showAddNewIdeaDialog(sharedText);
        }
    }

    @Override
    protected BaseDataAdapter<Idea> getAdapter() {
        if (ideaAdapter == null) {
            ideaAdapter = new IdeaAdapter(getActivity(), IdeaApplication.getInstance().getIdeaStorage());
        }
        return ideaAdapter;
    }

    @Override
    protected void onFabClick() {
    }

    @OnClick(R.id.add)
    void addIdea() {
        String ideaText = ideaEt.getText().toString();
        if (ideaText.isEmpty()) {
            return;
        }
        onAdd(ideaText);
        ideaEt.setText("");
    }

    @Override
    protected int getEmptyViewStringResId() {
        return R.string.no_ideas;
    }

    @Override
    public void onItemClick(int position) {
        startActivity(IdeaDetailsActivity.buildIntent(getActivity(), getAdapter().getItem(position).getId()));
    }

    private void showAddNewIdeaDialog(String startingText) {
        AddItemDialogBuilder.build(getActivity(), R.string.hint_new_idea, startingText, this).show();
    }

    @Override
    public void onAdd(String text) {
        listView.scrollToPosition(0);
        ideaAdapter.add(new Idea(text));
        updateEmptyViewVisibility();
    }

}
