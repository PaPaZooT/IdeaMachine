package hr.matvidako.ideamachine.drawer;

import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.idea.IdeaListActivity;

public enum DrawerMenuItem {

    IDEAS(0, R.string.ideas, IdeaListActivity.class),
    TAGS(0, R.string.tags, IdeaListActivity.class),
    HELP(0, R.string.help_feedback, IdeaListActivity.class),
    SETTINGS(0, R.string.settings, IdeaListActivity.class);

    public final int drawable;
    public final int title;
    public final Class activityClass;

    DrawerMenuItem(int drawable, int title, Class activityClass) {
        this.drawable = drawable;
        this.title = title;
        this.activityClass = activityClass;
    }

    public static DrawerMenuItem getByPosition(int pos) {
        return DrawerMenuItem.values()[pos];
    }

    public static int getCount() {
        return DrawerMenuItem.values().length;
    }

}
