package hr.matvidako.ideamachine.idea;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.BaseActivity;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class IdeaDetailsActivity extends BaseActivity {

    private static String EXTRA_IDEA_ID = "ideaId";
    private Idea idea;
    private IdeaStorage ideaStorage;

    @InjectView(R.id.idea_content)
    EditText etIdeaContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        ideaStorage = getApp().getIdeaStorage();
        loadDataFromIntent(getIntent());
        etIdeaContent.setText(idea.getContent());
    }

    @Override
    protected void onPause() {
        updateIdea();
        super.onPause();
    }

    private void updateIdea() {
        idea.setContent(etIdeaContent.getText().toString());
        ideaStorage.update(idea);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_idea_details;
    }

    private void loadDataFromIntent(Intent intent) {
        if(intent == null) {
            return;
        }
        int ideaId = intent.getIntExtra(EXTRA_IDEA_ID, 0);
        idea = ideaStorage.getById(ideaId);
    }

    public static Intent buildIntent(Context context, int ideaId) {
        Intent i = new Intent(context, IdeaDetailsActivity.class);
        i.putExtra(EXTRA_IDEA_ID, ideaId);
        return i;
    }

}
