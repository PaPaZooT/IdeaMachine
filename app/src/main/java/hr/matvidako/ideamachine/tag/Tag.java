package hr.matvidako.ideamachine.tag;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import hr.matvidako.ideamachine.db.Data;

@DatabaseTable
public class Tag extends Data {

    @DatabaseField(columnName = Columns.title)
    String title;

    public Tag() {}

    public Tag(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public interface Columns {
        String title = "title";
    }
}
