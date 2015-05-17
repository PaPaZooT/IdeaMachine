package hr.matvidako.ideamachine;

import java.util.List;

public interface Storage<T> {

    int create(T item);
    int delete(T item);
    int update(T item);
    List<T> getAll();
    T getById(int id);

}
