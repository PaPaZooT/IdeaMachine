package hr.matvidako.ideamachine.idea.storage;

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
    public List<Idea> loadAll() {
        return ideas;
    }

    @Override
    public void store(Idea idea) {
        ideas.add(idea);
    }

    @Override
    public void remove(Idea idea) {
        ideas.remove(idea);
    }

    @Override
    public int getIdeaCountForToday() {
        return random.nextInt(11);
    }

    @Override
    public int getCurrentIdeaStreak() {
        return random.nextInt(150);
    }

    @Override
    public void updateCurrentIdeaStreak() {
    }

}
