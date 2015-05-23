package hr.matvidako.ideamachine.stats.chart;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class IdeasPerDayChart extends BarChart {

    private static int NUMBER_OF_DAYS_TO_SHOW = 10;
    private Resources res;

    public IdeasPerDayChart(Context context) {
        this(context, null);
    }

    public IdeasPerDayChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IdeasPerDayChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        res = getResources();
        style();
    }

    private void style() {
        setDrawBorders(false);
        getLegend().setEnabled(false);
        setDescription("");

        XAxis dateAxis = getXAxis();
        dateAxis.setDrawGridLines(false);
        dateAxis.setAxisLineWidth(2);
        dateAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisLineWidth(2);

        YAxis rightAxis = getAxisRight();
        rightAxis.setEnabled(false);

        setTouchEnabled(false);
    }

    public void setupData(IdeaStorage ideaStorage) {
        List<String> dates = new ArrayList<>(NUMBER_OF_DAYS_TO_SHOW);
        List<BarEntry> ideaCounts = new ArrayList<>(NUMBER_OF_DAYS_TO_SHOW);
        DateTime currentDay = new DateTime();
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/M");
        for(int i = 0; i < NUMBER_OF_DAYS_TO_SHOW; i++) {
            dates.add(currentDay.toString(dateFormatter));
            ideaCounts.add(new BarEntry(ideaStorage.getIdeaCountForDay(currentDay), reverseIndex(i)));
            currentDay = currentDay.minusDays(1);
        }
        Collections.reverse(dates);

        BarDataSet dataSet = new BarDataSet(ideaCounts, "");
        dataSet.setColor(res.getColor(R.color.accent));
        dataSet.setDrawValues(false);
        setData(new BarData(dates, dataSet));
        invalidate();
    }

    private int reverseIndex(int index) {
        return NUMBER_OF_DAYS_TO_SHOW - index - 1;
    }

}
