package hr.matvidako.ideamachine.ideatag;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import hr.matvidako.ideamachine.db.Data;
import hr.matvidako.ideamachine.idea.Idea;
import hr.matvidako.ideamachine.tag.Tag;

@DatabaseTable
public class IdeaByTag extends Data {

    @DatabaseField(columnName = Columns.ideaId)
    int ideaId;
    @DatabaseField(columnName = Columns.tagId)
    int tagId;

    public IdeaByTag(){}

    public IdeaByTag(Idea idea, Tag tag) {
        ideaId = idea.getId();
        tagId = tag.getId();
    }

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
