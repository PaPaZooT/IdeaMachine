package hr.matvidako.ideamachine.idea.storage;

import java.util.List;

import hr.matvidako.ideamachine.idea.Idea;

public interface IdeaStorage {

    int IDEA_COUNT_FOR_STREAK = 10;

    List<Idea> loadAll();

    void store(Idea idea);

    void remove(Idea idea);

    int getIdeaCountForToday();

    int getCurrentIdeaStreak();

    void updateCurrentIdeaStreak();

}
