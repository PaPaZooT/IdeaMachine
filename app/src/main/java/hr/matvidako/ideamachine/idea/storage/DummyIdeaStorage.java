package hr.matvidako.ideamachine.idea.storage;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hr.matvidako.ideamachine.idea.Idea;

public class DummyIdeaStorage implements IdeaStorage {

    static List<Idea> ideas = new ArrayList<>();
    static {
        ideas.add(new Idea("Gladiola app"));
        ideas.add(new Idea("Weddings"));
        ideas.add(new Idea("Completed games"));
        ideas.add(new Idea("Geometry calculator"));
        ideas.add(new Idea("Rpg player notes"));
        ideas.add(new Idea("Study buddy"));
        ideas.add(new Idea("Brain teasers"));
        ideas.add(new Idea("Cv app"));
        ideas.add(new Idea("Roman numerals"));
        ideas.add(new Idea("Steam details"));
    }

    Random random = new Random();

    @Override
    public List<Idea> getAll() {
        return ideas;
    }

    @Override
    public Idea getById(int id) {
        return ideas.get(0);
    }

    @Override
    public int create(Idea idea) {
        ideas.add(idea);
        return 1;
    }

    @Override
    public int delete(Idea idea) {
        ideas.remove(idea);
        return 1;
    }

    @Override
    public int update(Idea item) {
        return 1;
    }

    @Override
    public int getIdeaCountForToday() {
        return random.nextInt(11);
    }

    @Override
    public int getIdeaCountForDay(DateTime day) {
        return random.nextInt(20);
    }

    @Override
    public int getCurrentIdeaStreak() {
        return random.nextInt(150);
    }

    @Override
    public void updateCurrentIdeaStreak() {
    }

}
