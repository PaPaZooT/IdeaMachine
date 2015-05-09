package hr.matvidako.ideamachine.idea;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import hr.matvidako.ideamachine.db.Data;

@DatabaseTable
public class Idea extends Data {

    @DatabaseField
    private String content;

    public Idea() {}

    public Idea(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
