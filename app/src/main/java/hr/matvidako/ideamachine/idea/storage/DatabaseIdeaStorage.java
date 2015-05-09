package hr.matvidako.ideamachine.idea.storage;

import android.content.Context;

import java.util.List;

import hr.matvidako.ideamachine.db.Repository;
import hr.matvidako.ideamachine.idea.Idea;

public class DatabaseIdeaStorage extends Repository<Idea> implements IdeaStorage {

    public DatabaseIdeaStorage(Context context) {
        super(context, Idea.class);
    }

    @Override
    public List<Idea> loadAll() {
        return getAll();
    }

    @Override
    public void store(Idea idea) {
        create(idea);
    }

    @Override
    public void remove(Idea idea) {
        delete(idea);
    }

}
