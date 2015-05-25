package hr.matvidako.ideamachine;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import net.danlew.android.joda.JodaTimeAndroid;
import hr.matvidako.ideamachine.idea.storage.DatabaseIdeaStorage;
import hr.matvidako.ideamachine.idea.storage.DummyIdeaStorage;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;
import hr.matvidako.ideamachine.tag.storage.DatabaseTagStorage;
import hr.matvidako.ideamachine.tag.storage.TagStorage;

public class IdeaApplication extends Application {

    private static IdeaApplication instance;
    private IdeaStorage ideaStorage;
    private TagStorage tagStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        JodaTimeAndroid.init(this);
        instance = this;
        ideaStorage = new DatabaseIdeaStorage(this);
        ideaStorage.updateCurrentIdeaStreak();
        tagStorage = new DatabaseTagStorage(this);
    }

    public static IdeaApplication getInstance() {
        return instance;
    }

    public IdeaStorage getIdeaStorage() {
        return ideaStorage;
    }

    public TagStorage getTagStorage() {
        return tagStorage;
    }

}
