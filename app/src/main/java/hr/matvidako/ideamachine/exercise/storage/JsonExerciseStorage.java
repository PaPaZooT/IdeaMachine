package hr.matvidako.ideamachine.exercise.storage;

import android.content.Context;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import hr.matvidako.ideamachine.exercise.Exercise;

public class JsonExerciseStorage implements ExerciseStorage {

    Context context;

    public JsonExerciseStorage(Context context) {
        this.context = context;
    }

    @Override
    public List<Exercise> loadExercises() {
        String json = loadJSONFromAsset();
        Gson gson = new Gson();
        Exercise[] exercises = gson.fromJson(json, Exercise[].class);
        return Arrays.asList(exercises);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("exercises.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}
