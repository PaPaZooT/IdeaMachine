package hr.matvidako.ideamachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import hr.matvidako.ideamachine.drawer.DrawerItemAdapter;
import hr.matvidako.ideamachine.idea.storage.DatabaseIdeaStorage;
import hr.matvidako.ideamachine.idea.storage.IdeaStorage;

public abstract class BaseActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.menu_list)
    ListView menuList;
    @InjectView(R.id.menu_drawer)
    DrawerLayout drawerLayout;
    TextView tvIdeaCount;

    ActionBarDrawerToggle drawerToggle;
    DrawerItemAdapter menuAdapter;
    IdeaStorage ideaStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);
        ideaStorage = new DatabaseIdeaStorage(this);
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
        tvIdeaCount = ButterKnife.findById(header, R.id.idea_count);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                tvIdeaCount.setText(getString(R.string.ideas_today, ideaStorage.getIdeaCountForToday()));
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
