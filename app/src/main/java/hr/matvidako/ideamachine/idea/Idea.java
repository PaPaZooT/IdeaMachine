package hr.matvidako.ideamachine.idea;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.joda.time.DateTime;
import hr.matvidako.ideamachine.db.Data;

@DatabaseTable
public class Idea extends Data {

    @DatabaseField(columnName = Columns.content)
    private String content;

    @DatabaseField(columnName = Columns.dateCreated)
    private long dateCreated;

    @DatabaseField(columnName = Columns.dateUpdated)
    private long dateUpdated;

    public Idea() {}

    public Idea(String content) {
        this.content = content;
        dateCreated = new DateTime().getMillis();
        dateUpdated = new DateTime().getMillis();
    }

    public String getContent() {
        return content;
    }

    public interface Columns {
        String content = "content";
        String dateCreated = "dateCreated";
        String dateUpdated = "dateUpdated";
    }
}
