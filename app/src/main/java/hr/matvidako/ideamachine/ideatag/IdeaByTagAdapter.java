package hr.matvidako.ideamachine.ideatag;

import android.content.Context;

import java.util.List;

import hr.matvidako.ideamachine.idea.Idea;
import hr.matvidako.ideamachine.idea.IdeaAdapter;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;
import hr.matvidako.ideamachine.tag.Tag;

public class IdeaByTagAdapter extends IdeaAdapter {

    private IdeaStorage ideaStorage;
    private Tag tag;

    public IdeaByTagAdapter(Context context, IdeaStorage ideaStorage, Tag tag) {
        super(context, ideaStorage);
        this.ideaStorage = ideaStorage;
        this.tag = tag;
    }

    @Override
    protected List<Idea> getAllItems() {
        return ideaStorage.getByTag(tag);
    }

    @Override
    public void add(Idea item) {
        ideaStorage.create(item);
        ideaStorage.addTagToIdea(item, tag);
        items.add(0, item);
        notifyItemInserted(0);
    }
}
