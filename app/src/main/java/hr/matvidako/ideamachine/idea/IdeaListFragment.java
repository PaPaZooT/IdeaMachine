package hr.matvidako.ideamachine.idea;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.AddItemDialogBuilder;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.base.BaseDataListFragment;

public class IdeaListFragment extends BaseDataListFragment<Idea> implements AddItemDialogBuilder.OnAddListener {

    private IdeaAdapter ideaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.inject(this, view);
        return view;
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
        if(ideaAdapter == null) {
            ideaAdapter = new IdeaAdapter(getActivity(), IdeaApplication.getInstance().getIdeaStorage());
        }
        return ideaAdapter;
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
