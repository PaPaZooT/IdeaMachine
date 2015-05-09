package hr.matvidako.ideamachine.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class Repository<T extends Data> {

	private DBHelper db;
	Dao<T, Integer> dao;

	public Repository(Context ctx, Class<T> typeParameterClass) {
		try {
			db = DBHelper.getInstance(ctx);
			dao = db.getDao(typeParameterClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int create(T item) {
		try {
			return dao.create(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int update(T item) {
		try {
			return dao.update(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(T item) {
		try {
			return dao.delete(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<T> getAll() {
		try {
			return dao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public T getById(int id) {
		try {
			return dao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
