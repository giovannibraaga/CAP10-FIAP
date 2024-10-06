package database;

import java.util.List;

public interface DAO<T> {
    void add(T t);

    void update(T t);

    void delete(T t);

    T findById(int id);

    List<T> findAll();
}
