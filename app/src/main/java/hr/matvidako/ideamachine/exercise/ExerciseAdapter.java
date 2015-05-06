package hr.matvidako.ideamachine.exercise;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.matvidako.ideamachine.R;

public class ExerciseAdapter extends BaseAdapter {

    private List<Exercise> exercises = new ArrayList<>();
    private List<Integer> ideaCounts = new ArrayList<>();

    private LayoutInflater layoutInflater;
    private Context context;

    public ExerciseAdapter(Context context, List<Exercise> exercises, List<Integer> ideaCounts) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.exercises.addAll(exercises);
        this.ideaCounts.addAll(ideaCounts);
    }

    public void updateIdeaCounts(List<Integer> ideaCounts) {
        this.ideaCounts.clear();
        this.ideaCounts.addAll(ideaCounts);
    }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Exercise getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_exercise, null, false);
        }
        TextView title = (TextView) convertView.findViewById(R.id.exercise_title);
        title.setText(context.getString(R.string.order, position + 1, getItem(position).title));
        ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.exercise_progress);
        int ideaCount = ideaCounts.get(position);
        if(ideaCount <= 0) {
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(ideaCounts.get(position));
        }
        return convertView;
    }

}
