package hr.matvidako.ideamachine.idea;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.joda.time.DateTime;
import hr.matvidako.ideamachine.db.Data;

@DatabaseTable
public class Idea extends Data {

    @DatabaseField(columnName = Columns.content)
    private String content;

    public Idea() {
        super();
    }

    public Idea(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public interface Columns {
        String content = "content";
    }

}
