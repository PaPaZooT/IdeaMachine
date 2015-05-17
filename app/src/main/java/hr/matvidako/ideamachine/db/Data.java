package hr.matvidako.ideamachine.db;

import com.j256.ormlite.field.DatabaseField;

import org.joda.time.DateTime;

import java.io.Serializable;

public class Data implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = Columns.dateCreated)
    private long dateCreated;

    @DatabaseField(columnName = Columns.dateUpdated)
    private long dateUpdated;

    public Data() {
        dateCreated = new DateTime().getMillis();
        dateUpdated = new DateTime().getMillis();
    }

    public int getId() {
        return id;
    }

    public interface Columns {
        String dateCreated = "dateCreated";
        String dateUpdated = "dateUpdated";
    }

}
