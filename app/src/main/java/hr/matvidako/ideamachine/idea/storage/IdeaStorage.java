package hr.matvidako.ideamachine.idea.storage;

import hr.matvidako.ideamachine.Storage;
import hr.matvidako.ideamachine.idea.Idea;

public interface IdeaStorage extends Storage<Idea> {

    int IDEA_COUNT_FOR_STREAK = 10;

    int getIdeaCountForToday();

    int getCurrentIdeaStreak();

    void updateCurrentIdeaStreak();

}
