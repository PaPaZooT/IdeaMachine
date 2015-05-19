package hr.matvidako.ideamachine.tag;

import android.view.View;
import android.widget.AdapterView;
import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.BaseDataAdapter;
import hr.matvidako.ideamachine.base.BaseDataListActivity;

public class TagListActivity extends BaseDataListActivity<Tag> {

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
        tagAdapter.add(new Tag("bla"));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tag_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
