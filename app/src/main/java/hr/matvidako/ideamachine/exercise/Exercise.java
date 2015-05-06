package hr.matvidako.ideamachine.exercise;

import java.io.Serializable;

public class Exercise implements Serializable {

    public final int id;
    public final String title;
    public final String description;

    public Exercise(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

}
