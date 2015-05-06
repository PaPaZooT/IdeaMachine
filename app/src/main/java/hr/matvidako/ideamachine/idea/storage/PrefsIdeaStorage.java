package hr.matvidako.ideamachine.idea.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.matvidako.ideamachine.idea.Idea;

public class PrefsIdeaStorage implements IdeaStorage {

    private static final String BASE_PREFS_PATH = "matvidako/ideas_";
    private static final String PREFERENCES_NAME = "ideas";
    SharedPreferences prefs;
    Gson gson = new Gson();
    IdeaCountStorage ideaCountStorage;

    public PrefsIdeaStorage(Context context) {
        prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        ideaCountStorage = new IdeaCountStorage(context);
    }

    @Override
    public List<Idea> loadIdeas(int exerciseId) {
        String json = prefs.getString(buildIdeaPrefFilePath(exerciseId), "");
        Idea[] ideas = gson.fromJson(json, Idea[].class);
        if(ideas == null) {
            return new ArrayList<Idea>();
        } else {
            return Arrays.asList(ideas);
        }
    }

    @Override
    public void storeIdeas(int exerciseId, List<Idea> ideas) {
        prefs.edit().putString(buildIdeaPrefFilePath(exerciseId), gson.toJson(ideas)).commit();
        ideaCountStorage.storeCount(exerciseId, ideas.size());
    }

    private String buildIdeaPrefFilePath(int exerciseId) {
        return BASE_PREFS_PATH + exerciseId;
    }

}
