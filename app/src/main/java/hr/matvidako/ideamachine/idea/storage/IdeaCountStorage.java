package hr.matvidako.ideamachine.idea.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hr.matvidako.ideamachine.Constants;

public class IdeaCountStorage {

    private static final String BASE_PREFS_PATH = "matvidako/ideaCounts";
    private static final String PREFERENCES_NAME = "ideaCounts";

    SharedPreferences prefs;
    Gson gson = new Gson();

    public IdeaCountStorage(Context context) {
        prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public ArrayList<Integer> loadIdeaCounts() {
        String json = prefs.getString(BASE_PREFS_PATH, null);
        if(json == null) {
            return createDefaultCounts();
        } else {
            Integer[] counts = gson.fromJson(json, Integer[].class);
            return new ArrayList<>(Arrays.asList(counts));
        }
}

    public void storeCount(int index, int count) {
        Log.d("DISI", "storing at " + index + " count " + count);
        ArrayList<Integer> counts = loadIdeaCounts();
        Log.d("DISI", "before " + counts);
        counts.set(index, count);
        storeCounts(counts);
        Log.d("DISI", "after " +  loadIdeaCounts());
    }

    private ArrayList<Integer> createDefaultCounts() {
        ArrayList<Integer> counts = new ArrayList<>();
        for(int i = 0; i < Constants.TOTAL_EXERCISES; i++) {
            counts.add(0);
        }
        storeCounts(counts);
        return counts;
    }

    private void storeCounts(List<Integer> counts) {
        prefs.edit().putString(BASE_PREFS_PATH, gson.toJson(counts)).commit();
    }

}
