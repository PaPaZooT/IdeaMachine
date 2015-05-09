package hr.matvidako.ideamachine.idea.storage;

import java.util.List;

import hr.matvidako.ideamachine.idea.Idea;

public interface IdeaStorage {

    List<Idea> loadAll();

    void store(List<Idea> ideas);

    void store(Idea idea);

}
