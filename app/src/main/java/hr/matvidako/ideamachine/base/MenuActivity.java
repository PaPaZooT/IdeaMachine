package hr.matvidako.ideamachine.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.idea.IdeaListActivity;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;
import hr.matvidako.ideamachine.stats.StatsActivity;
import hr.matvidako.ideamachine.tag.TagListActivity;

public abstract class MenuActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @InjectView(R.id.navigation_view)
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        setupToolbar();
        setupMenuDrawer();
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.menu);
    }

    protected void setupMenuDrawer() {

        navigationView.setNavigationItemSelectedListener(this);

        final TextView tvIdeaCount = ButterKnife.findById(this, R.id.idea_count);
        final TextView tvIdeaStreak = ButterKnife.findById(this, R.id.idea_streak);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                IdeaStorage ideaStorage = getApp().getIdeaStorage();
                int ideasToday = ideaStorage.getIdeaCountForToday();
                tvIdeaCount.setText(resources.getQuantityString(R.plurals.ideas_today, ideasToday, ideasToday));
                int streak = ideaStorage.getCurrentIdeaStreak();
                tvIdeaStreak.setText(resources.getQuantityString(R.plurals.streak, streak, streak));
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();

        drawerLayout.closeDrawers();
        if(id == R.id.action_ideas) {
            startActivity(IdeaListActivity.class);
            return true;
        } else if(id == R.id.action_tags) {
            startActivity(TagListActivity.class);
            return true;
        } else if(id == R.id.action_stats) {
            startActivity(StatsActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void startActivity(Class activityClass) {
        Intent i = new Intent(MenuActivity.this, activityClass);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
