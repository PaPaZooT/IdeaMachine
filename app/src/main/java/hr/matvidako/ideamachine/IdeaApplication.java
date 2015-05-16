package hr.matvidako.ideamachine;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

public class IdeaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }

}
