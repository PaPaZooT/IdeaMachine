package hr.matvidako.ideamachine.tag;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.ButterKnife;
import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.AddItemDialogBuilder;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.base.BaseDataListActivity;
import hr.matvidako.ideamachine.base.BaseDataListFragment;
import hr.matvidako.ideamachine.ideatag.IdeasByTagListActivity;

public class TagListFragment extends BaseDataListFragment<Tag> implements AddItemDialogBuilder.OnAddListener {

    TagAdapter tagAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tag_list, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    protected BaseDataAdapter<Tag> getAdapter() {
        if(tagAdapter == null) {
            tagAdapter = new TagAdapter(getActivity(), IdeaApplication.getInstance().getTagStorage());
        }
        return tagAdapter;
    }

    @Override
    protected void onFabClick() {
        showAddNewTagDialog();
    }

    private void showAddNewTagDialog() {
        Context context = getActivity();
        View dialogContentView = LayoutInflater.from(context).inflate(R.layout.dialog_new_item, null, false);
        final EditText etNewIdea = (EditText) dialogContentView.findViewById(R.id.new_item);
        etNewIdea.setHint(R.string.hint_new_tag);
        AddItemDialogBuilder.build(context, etNewIdea, dialogContentView, this).show();
    }

    @Override
    protected int getEmptyViewStringResId() {
        return R.string.no_tags;
    }

    @Override
    public void onItemClick(int position) {
        startActivity(IdeasByTagListActivity.buildIntent(getActivity(), (int) tagAdapter.getItemId(position)));
    }

    @Override
    public void onAdd(String text) {
        listView.scrollToPosition(0);
        tagAdapter.add(new Tag(text));
        updateEmptyViewVisibility();
    }
}
