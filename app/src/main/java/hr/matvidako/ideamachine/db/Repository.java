package hr.matvidako.ideamachine.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import hr.matvidako.ideamachine.Storage;

public class Repository<T extends Data> implements Storage<T> {

	private DBHelper db;
	protected Dao<T, Integer> dao;
	protected Context context;

	public Repository(Context ctx, Class<T> typeParameterClass) {
		this.context = ctx;
		try {
			db = DBHelper.getInstance(ctx);
			dao = db.getDao(typeParameterClass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int create(T item) {
		try {
			return dao.create(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int update(T item) {
		try {
			return dao.update(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(T item) {
		try {
			return dao.delete(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<T> getAll() {
		try {
			return dao.queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public T getById(int id) {
		try {
			return dao.queryForId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
