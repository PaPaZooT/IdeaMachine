package hr.matvidako.ideamachine.idea.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTimeUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.matvidako.ideamachine.db.Data;
import hr.matvidako.ideamachine.db.Repository;
import hr.matvidako.ideamachine.idea.Idea;
import hr.matvidako.ideamachine.utils.DateUtils;

public class DatabaseIdeaStorage extends Repository<Idea> implements IdeaStorage {

    private static final String PREFS = "idea_prefs";
    private static final String PREF_STREAK = "idea/streak";
    private SharedPreferences prefs;

    public DatabaseIdeaStorage(Context context) {
        super(context, Idea.class);
        prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    @Override
    public List<Idea> getAll() {
        return getAllSortedByDateCreatedDesc();
    }

    @Override
    public int create(Idea idea) {
        int nRowsModified = super.create(idea);
        if(getIdeaCountForToday() == IDEA_COUNT_FOR_STREAK) {
            incrementIdeaStreak();
        }
        return nRowsModified;
    }

    @Override
    public int delete(Idea idea) {
        int nRowsModified = super.delete(idea);
        if(getIdeaCountForToday() == IDEA_COUNT_FOR_STREAK - 1) {
            decrementIdeaStreak();
        }
        return nRowsModified;
    }

    private List<Idea> getAllSortedByDateCreatedDesc() {
        try {
            return dao.queryBuilder().orderBy(Data.Columns.dateCreated, false).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public int getIdeaCountForToday() {
        return getIdeaCountForDay(DateUtils.getStartOfTodayMilis());
    }

    @Override
    public int getIdeaCountForDay(DateTime day) {
        long dayStartMilis = DateUtils.getStartOfDayMilis(day);
        return getIdeaCountForDay(dayStartMilis);
    }

    private int getIdeaCountForDay(long dayStartMilis) {
        try {
            return (int) dao.queryBuilder().where().between(Data.Columns.dateCreated, dayStartMilis, dayStartMilis + DateTimeConstants.MILLIS_PER_DAY).countOf();
        } catch (SQLException e) {
            return 0;
        }
    }

    @Override
    public int getCurrentIdeaStreak() {
        return prefs.getInt(PREF_STREAK, 0);
    }

    @Override
    public void updateCurrentIdeaStreak() {
        if(getIdeaCountForDay(DateUtils.getStartOfYesterdayMilis()) < IDEA_COUNT_FOR_STREAK) {
            if(getIdeaCountForToday() >= IDEA_COUNT_FOR_STREAK) {
                storeIdeaStreak(1);
            } else {
                storeIdeaStreak(0);
            }
        }
    }

    private void incrementIdeaStreak() {
        storeIdeaStreak(getCurrentIdeaStreak() + 1);
    }

    private void decrementIdeaStreak() {
        storeIdeaStreak(getCurrentIdeaStreak() - 1);
    }

    private void storeIdeaStreak(int streak) {
        prefs.edit().putInt(PREF_STREAK, streak).commit();
    }
}
