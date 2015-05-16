package hr.matvidako.ideamachine.idea.storage;

import android.content.Context;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.matvidako.ideamachine.db.Repository;
import hr.matvidako.ideamachine.idea.Idea;
import hr.matvidako.ideamachine.utils.DateUtils;

public class DatabaseIdeaStorage extends Repository<Idea> implements IdeaStorage {

    public DatabaseIdeaStorage(Context context) {
        super(context, Idea.class);
    }

    @Override
    public List<Idea> loadAll() {
        return getAllSortedByDateCreatedDesc();
    }

    @Override
    public void store(Idea idea) {
        create(idea);
    }

    @Override
    public void remove(Idea idea) {
        delete(idea);
    }

    private List<Idea> getAllSortedByDateCreatedDesc() {
        try {
            return dao.queryBuilder().orderBy(Idea.Columns.dateCreated, false).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public long getIdeaCountForToday() {
        try {
            return dao.queryBuilder().where().gt(Idea.Columns.dateCreated, DateUtils.getStartOfTodayMilis()).countOf();
        } catch (SQLException e) {
            return 0;
        }
    }

}
