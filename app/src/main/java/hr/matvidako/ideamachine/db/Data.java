package hr.matvidako.ideamachine.db;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Data implements Serializable {

    @DatabaseField(generatedId = true)
    private int id;

    public int getId() {
        return id;
    }

}
