package hr.matvidako.ideamachine.idea.storage;

import java.util.List;

import hr.matvidako.ideamachine.idea.Idea;

public interface IdeaStorage {

    List<Idea> loadIdeas(int exerciseId);

    void storeIdeas(int exerciseId, List<Idea> ideas);

}
