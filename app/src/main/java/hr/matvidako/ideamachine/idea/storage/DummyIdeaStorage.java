package hr.matvidako.ideamachine.idea.storage;

import java.util.ArrayList;
import java.util.List;

import hr.matvidako.ideamachine.idea.Idea;

public class DummyIdeaStorage implements IdeaStorage {

    @Override
    public List<Idea> loadIdeas(int exerciseId) {
        List<Idea> ideas = new ArrayList<>();
        if(exerciseId == 1) {
            return ideas;
        }
        ideas.add(new Idea(1, "Gladiola app"));
        ideas.add(new Idea(2, "Weddings"));
        ideas.add(new Idea(3, "Completed games"));
        ideas.add(new Idea(4, "Geometry calculator"));
        ideas.add(new Idea(5, "Rpg player notes"));
        ideas.add(new Idea(6, "Study buddy"));
        ideas.add(new Idea(7, "Brain teasers"));
        ideas.add(new Idea(8, "Cv app"));
        ideas.add(new Idea(9, "Roman numerals"));
        ideas.add(new Idea(10, "Steam details"));
        return ideas;
    }

    @Override
    public void storeIdeas(int exerciseId, List<Idea> ideas) {
    }

}
