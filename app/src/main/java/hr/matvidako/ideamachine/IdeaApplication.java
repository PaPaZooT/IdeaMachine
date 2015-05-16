package hr.matvidako.ideamachine;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import hr.matvidako.ideamachine.idea.storage.DatabaseIdeaStorage;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public class IdeaApplication extends Application {


    private static IdeaApplication instance;
    private IdeaStorage ideaStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        instance = this;
        ideaStorage = new DatabaseIdeaStorage(this);
    }

    public static IdeaApplication getInstance() {
        return instance;
    }

    public IdeaStorage getIdeaStorage() {
        return ideaStorage;
    }

}
