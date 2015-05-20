package hr.matvidako.ideamachine.tag.storage;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.matvidako.ideamachine.db.Data;
import hr.matvidako.ideamachine.db.Repository;
import hr.matvidako.ideamachine.tag.Tag;

public class DatabaseTagStorage extends Repository<Tag> implements TagStorage {

    public DatabaseTagStorage(Context ctx) {
        super(ctx, Tag.class);
    }

    @Override
    public List<Tag> getAll() {
        return getAllSortedByDateCreatedDesc();
    }

    private List<Tag> getAllSortedByDateCreatedDesc() {
        try {
            return dao.queryBuilder().orderBy(Data.Columns.dateCreated, false).query();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
