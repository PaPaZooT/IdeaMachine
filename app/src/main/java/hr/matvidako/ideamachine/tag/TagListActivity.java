package hr.matvidako.ideamachine.tag;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.AddItemDialogBuilder;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.base.BaseDataListActivity;

public class TagListActivity extends BaseDataListActivity<Tag> implements AddItemDialogBuilder.OnAddListener {

    TagAdapter tagAdapter;

    @Override
    protected BaseDataAdapter<Tag> getAdapter() {
        if(tagAdapter == null) {
            tagAdapter = new TagAdapter(this, IdeaApplication.getInstance().getTagStorage());
        }
        return tagAdapter;
    }

    @Override
    protected boolean isUsingFab() {
        return true;
    }

    @Override
    protected void onFabClick() {
        showAddNewTagDialog();
    }

    private void showAddNewTagDialog() {
        View dialogContentView = getLayoutInflater().inflate(R.layout.dialog_new_item, null, false);
        final EditText etNewIdea = (EditText) dialogContentView.findViewById(R.id.new_item);
        etNewIdea.setHint(R.string.hint_new_tag);
        AddItemDialogBuilder.build(this, etNewIdea, dialogContentView, this).show();
    }

    @Override
    protected int getEmptyViewStringResId() {
        return R.string.no_tags;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tag_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onAdd(String text) {
         Toast.makeText(this, R.string.new_tag_added, Toast.LENGTH_SHORT).show();
        tagAdapter.add(new Tag(text));
    }
}
