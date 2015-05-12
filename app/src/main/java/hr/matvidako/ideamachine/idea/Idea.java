package hr.matvidako.ideamachine.idea;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import hr.matvidako.ideamachine.db.Data;

@DatabaseTable
public class Idea extends Data {

    @DatabaseField(columnName = Columns.content)
    private String content;

    @DatabaseField(columnName = Columns.dateCreated)
    private Date dateCreated;

    @DatabaseField(columnName = Columns.dateUpdated)
    private Date dateUpdated;

    public Idea() {}

    public Idea(String content) {
        this.content = content;
        dateCreated = new Date();
        dateUpdated = new Date();
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
