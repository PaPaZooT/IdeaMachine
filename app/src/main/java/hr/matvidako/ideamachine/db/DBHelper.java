package hr.matvidako.ideamachine.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;

import hr.matvidako.ideamachine.R;
import hr.matvidako.ideamachine.idea.Idea;
import hr.matvidako.ideamachine.tag.Tag;

public class DBHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "ideaMachine.db";
	private static final int DATABASE_VERSION = 1;
	private static DBHelper instance = null;
	
	private HashMap<String, Dao<Data, Integer>> daoMap = new HashMap<>();
	private Context context;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	public static DBHelper getInstance(Context context) {
		if(instance == null){
			instance = OpenHelperManager.getHelper(context, DBHelper.class);
		}
		return instance;
	}
	
	public static void releaseInstance(DBHelper helper) {
        if (instance != null) {
            OpenHelperManager.releaseHelper();
            instance = null;
        }
    }
	
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try{
			TableUtils.createTable(connectionSource, Idea.class);
			TableUtils.createTable(connectionSource, Tag.class);
			createDefaultTags();
		} catch(SQLException e){
		}
	}

	private void createDefaultTags() {
		Repository<Tag> tagRepository = new Repository<>(context, Tag.class);
		String [] defaultTags = context.getResources().getStringArray(R.array.default_tags);
	    for(String tagTitle : defaultTags) {
            tagRepository.create(new Tag(tagTitle));
        }
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
	}
	
	@Override
	public void close() {
		super.close();
		for(String s : daoMap.keySet()){
			daoMap.put(s, null);
		}
	}
	
}
