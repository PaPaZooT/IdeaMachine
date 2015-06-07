package hr.matvidako.ideamachine.idea;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.MenuActivity;
import hr.matvidako.ideamachine.base.UpActivity;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class IdeaDetailsActivity extends UpActivity implements View.OnClickListener {

    private static String EXTRA_IDEA_ID = "ideaId";
    private Idea idea;
    private IdeaStorage ideaStorage;

    @InjectView(R.id.idea_content)
    EditText etIdeaContent;
    @InjectView(R.id.fab)
    FloatingActionButton fabCamera;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        ideaStorage = getApp().getIdeaStorage();
        loadDataFromIntent(getIntent());
        etIdeaContent.setText(idea.getContent());
        fabCamera.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    @Override
    protected int getMenuResId() {
        return R.menu.menu_idea;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_delete) {
            onDeleteIdea();
            return true;
        } else if(id == R.id.action_share) {
            onShareIdea();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onDeleteIdea() {
        ideaStorage.delete(idea);
        finish();
        Toast.makeText(this, getString(R.string.idea_deleted), Toast.LENGTH_SHORT).show();
    }

    private void onShareIdea() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, idea.getContent());
        startActivity(intent);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.fab) {
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        int REQUEST_IMAGE_CAPTURE = 1;
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        } else {
            Toast.makeText(this, R.string.camera_not_supported, Toast.LENGTH_SHORT).show();
        }
    }

}
