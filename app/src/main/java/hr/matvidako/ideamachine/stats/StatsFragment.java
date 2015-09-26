package hr.matvidako.ideamachine.stats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;
import hr.matvidako.ideamachine.stats.chart.IdeasPerDayChart;
import hr.matvidako.ideamachine.stats.chart.TotalIdeasChart;

public class StatsFragment extends Fragment {

    @InjectView(R.id.ideas_per_day)
    IdeasPerDayChart chartIdeasPerDay;
    @InjectView(R.id.total_ideas)
    TotalIdeasChart chartTotalIdeas;
    IdeaStorage ideaStorage;

    private static int NUMBER_OF_DAYS_TO_SHOW = 10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_stats, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ideaStorage = IdeaApplication.getInstance().getIdeaStorage();
        chartIdeasPerDay.setupData(ideaStorage, NUMBER_OF_DAYS_TO_SHOW);
        chartTotalIdeas.setupData(ideaStorage, NUMBER_OF_DAYS_TO_SHOW);
    }

}
