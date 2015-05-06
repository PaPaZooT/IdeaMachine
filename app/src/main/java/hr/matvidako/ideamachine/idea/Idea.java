package hr.matvidako.ideamachine.idea;

import java.io.Serializable;

public class Idea implements Serializable {

    public final int id;
    public final String content;

    public Idea(int id, String content) {
        this.id = id;
        this.content = content;
    }

}
