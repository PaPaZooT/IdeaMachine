package hr.matvidako.ideamachine.ideatag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.BaseActivity;
import hr.matvidako.ideamachine.tag.Tag;
import hr.matvidako.ideamachine.tag.storage.TagStorage;

public class IdeasByTagListActivity extends BaseActivity {

    private static final String EXTRA_TAG_ID = "tagId";
    private Tag tag;
    private TagStorage tagStorage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tagStorage = getApp().getTagStorage();
        loadDataFromIntent(getIntent());
        getSupportActionBar().setTitle(getString(R.string.tag_ideas, tag.getTitle()));
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
        return R.layout.activity_idea_list;
    }

    @Override
    protected int getMenuResId() {
        return R.menu.menu_tag;
    }
}
