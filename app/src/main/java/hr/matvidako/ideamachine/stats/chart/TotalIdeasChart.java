package hr.matvidako.ideamachine.stats.chart;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class TotalIdeasChart extends LineChart {

    public TotalIdeasChart(Context context) {
        this(context, null);
    }

    public TotalIdeasChart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TotalIdeasChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

    public void setupData(IdeaStorage ideaStorage, int numberOfDaysToShow) {
        List<String> dates = new ArrayList<>(numberOfDaysToShow);
        List<Entry> ideaCounts = new ArrayList<>(numberOfDaysToShow);
        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/M");
        DateTime currentDay = new DateTime();
        currentDay = currentDay.minusDays(numberOfDaysToShow - 1);
        int totalIdeas = 0;
        for(int i = 0; i < numberOfDaysToShow; i++) {
            dates.add(currentDay.toString(dateFormatter));
            totalIdeas += ideaStorage.getIdeaCountForDay(currentDay);
            ideaCounts.add(new Entry(totalIdeas, i));
            currentDay = currentDay.plusDays(1);
        }

        LineDataSet dataSet = new LineDataSet(ideaCounts, "");
        Resources res = getResources();
        dataSet.setColor(res.getColor(R.color.accent));
        dataSet.setDrawCircles(false);
        dataSet.setDrawFilled(true);
        dataSet.setFillColor(res.getColor(R.color.accent));
        dataSet.setFillAlpha(255);
        dataSet.setDrawValues(false);
        setData(new LineData(dates, dataSet));
        invalidate();
    }

    private int reverseIndex(int index, int count) {
        return count - index - 1;
    }

}
