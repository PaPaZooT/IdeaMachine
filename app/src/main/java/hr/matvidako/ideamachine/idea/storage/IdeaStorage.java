package hr.matvidako.ideamachine.idea.storage;

import org.joda.time.DateTime;

import java.util.List;

import hr.matvidako.ideamachine.Storage;
import hr.matvidako.ideamachine.idea.Idea;
import hr.matvidako.ideamachine.tag.Tag;

public interface IdeaStorage extends Storage<Idea> {

    int IDEA_COUNT_FOR_STREAK = 10;

    int getIdeaCountForToday();

    int getIdeaCountForDay(DateTime day);

    int getCurrentIdeaStreak();

    void updateCurrentIdeaStreak();

    List<Idea> getByTag(Tag tag);
}
