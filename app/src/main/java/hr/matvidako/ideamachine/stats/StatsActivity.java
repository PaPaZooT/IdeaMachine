package hr.matvidako.ideamachine.stats;

import android.os.Bundle;
import android.os.PersistableBundle;

import butterknife.InjectView;
import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.BaseActivity;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;
import hr.matvidako.ideamachine.stats.chart.IdeasPerDayChart;
import hr.matvidako.ideamachine.stats.chart.TotalIdeasChart;

public class StatsActivity extends BaseActivity {

    @InjectView(R.id.ideas_per_day)
    IdeasPerDayChart chartIdeasPerDay;
    @InjectView(R.id.total_ideas)
    TotalIdeasChart chartTotalIdeas;
    IdeaStorage ideaStorage;

    private static int NUMBER_OF_DAYS_TO_SHOW = 10;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ideaStorage = IdeaApplication.getInstance().getIdeaStorage();
        chartIdeasPerDay.setupData(ideaStorage, NUMBER_OF_DAYS_TO_SHOW);
        chartTotalIdeas.setupData(ideaStorage, NUMBER_OF_DAYS_TO_SHOW);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_stats;
    }

}
