package hr.matvidako.ideamachine.stats;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;
import hr.matvidako.ideamachine.IdeaApplication;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.base.BaseActivity;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class StatsActivity extends BaseActivity {

    @InjectView(R.id.ideas_per_day)
    BarChart chartIdeasPerDay;
    IdeaStorage ideaStorage;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        ideaStorage = IdeaApplication.getInstance().getIdeaStorage();
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<String> xVals = Arrays.asList("yesterday", "today");
        List<BarEntry> barEntries = Arrays.asList(new BarEntry(10, 0), new BarEntry(2, 1));
        BarDataSet dataSet = new BarDataSet(barEntries, "bla");

        BarData barData = new BarData(xVals, dataSet);
        chartIdeasPerDay.setData(barData);
        chartIdeasPerDay.setDescription("");
        chartIdeasPerDay.setDrawBorders(false);
        chartIdeasPerDay.getLegend().setEnabled(false);
        chartIdeasPerDay.getXAxis().setDrawGridLines(false);
        chartIdeasPerDay.getXAxis().setDrawAxisLine(false);
        chartIdeasPerDay.getAxisLeft().setDrawGridLines(false);
        chartIdeasPerDay.getAxisLeft().setDrawAxisLine(false);
        chartIdeasPerDay.getAxisRight().setDrawGridLines(false);
        chartIdeasPerDay.getAxisRight().setDrawAxisLine(false);
        chartIdeasPerDay.setDrawBorders(false);
        chartIdeasPerDay.invalidate();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_stats;
    }

}
