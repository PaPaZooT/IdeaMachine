package hr.matvidako.ideamachine.ideatag;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import hr.matvidako.ideamachine.db.Data;

@DatabaseTable
public class IdeaByTag extends Data {

    @DatabaseField(columnName = Columns.ideaId)
    int ideaId;
    @DatabaseField(columnName = Columns.tagId)
    int tagId;

    public int getTagId() {
        return tagId;
    }

    public int getIdeaId() {
        return ideaId;
    }

    public interface Columns {
        String ideaId = "ideaId";
        String tagId = "tagId";
    }

}
