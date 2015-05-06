package hr.matvidako.ideamachine.exercise;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import hr.matvidako.ideamachine.ActionBarListActivity;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.exercise.storage.JsonExerciseStorage;
import hr.matvidako.ideamachine.idea.IdeaListActivity;
import hr.matvidako.ideamachine.idea.storage.IdeaCountStorage;

public class ExerciseListActivity extends ActionBarListActivity implements AdapterView.OnItemClickListener {

    private ExerciseAdapter exerciseAdapter;
    private IdeaCountStorage ideaCountStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Exercise> exercises = (new JsonExerciseStorage(this)).loadExercises();
        ideaCountStorage = new IdeaCountStorage(this);
        List<Integer> ideaCounts = ideaCountStorage.loadIdeaCounts();
        exerciseAdapter = new ExerciseAdapter(this, exercises, ideaCounts);
        getListView().setAdapter(exerciseAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return android.R.layout.list_content;
    }

    @Override
    protected void onResume() {
        super.onResume();
        exerciseAdapter.updateIdeaCounts(ideaCountStorage.loadIdeaCounts());
        exerciseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(IdeaListActivity.IntentBuilder.build(this, (Exercise) getListAdapter().getItem(position)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
