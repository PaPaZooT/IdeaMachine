package hr.matvidako.ideamachine.ideatag;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hr.matvidako.ideamachine.db.Repository;
import hr.matvidako.ideamachine.idea.Idea;
import hr.matvidako.ideamachine.tag.Tag;

public class IdeaByTagStorage extends Repository<IdeaByTag> {

    public IdeaByTagStorage(Context ctx) {
        super(ctx, IdeaByTag.class);
    }

    public List<IdeaByTag> getByTag(Tag tag) {
        try {
            return dao.queryForEq(IdeaByTag.Columns.tagId, tag.getId());
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public List<IdeaByTag> getByIdea(Idea idea) {
        try {
            return dao.queryForEq(IdeaByTag.Columns.ideaId, idea.getId());
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

}
