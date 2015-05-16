package hr.matvidako.ideamachine.idea.storage;

import java.util.List;

import hr.matvidako.ideamachine.idea.Idea;

public interface IdeaStorage {

    List<Idea> loadAll();

    void store(Idea idea);

    void remove(Idea idea);

    long getIdeaCountForToday();
}
