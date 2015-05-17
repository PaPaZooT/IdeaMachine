package hr.matvidako.ideamachine;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hr.matvidako.ideamachine.drawer.DrawerItemAdapter;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public abstract class BaseActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.menu_list)
    ListView menuList;
    @InjectView(R.id.menu_drawer)
    DrawerLayout drawerLayout;

    DrawerItemAdapter menuAdapter;
    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);
        resources = getResources();
        setupToolbar();
        setupMenuDrawer();
    }

    private void setupToolbar() {
        toolbar.setNavigationIcon(R.drawable.menu);
        setSupportActionBar(toolbar);
    }

    protected void setupMenuDrawer() {
        menuAdapter = new DrawerItemAdapter(this);
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new DrawerItemClickListener());

        View header = getLayoutInflater().inflate(R.layout.header_menu, null, false);
        menuList.addHeaderView(header, null, false);
        final TextView tvIdeaCount = ButterKnife.findById(header, R.id.idea_count);
        final TextView tvIdeaStreak = ButterKnife.findById(header, R.id.idea_streak);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                IdeaStorage ideaStorage = IdeaApplication.getInstance().getIdeaStorage();
                int ideasToday = ideaStorage.getIdeaCountForToday();
                tvIdeaCount.setText(resources.getQuantityString(R.plurals.ideas_today, ideasToday, ideasToday));
                int streak = ideaStorage.getCurrentIdeaStreak();
                tvIdeaStreak.setText(resources.getQuantityString(R.plurals.streak, streak, streak));
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    abstract protected int getLayoutId();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position == 0) return;
            position--;
            drawerLayout.closeDrawer(menuList);
            Toast.makeText(BaseActivity.this, menuAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
            Class activityClass = menuAdapter.getItem(position).activityClass;
            Intent i = new Intent(BaseActivity.this, activityClass);
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(i);
        }
    }


}
