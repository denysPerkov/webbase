package db.dao;

import java.sql.Connection;

public interface GenericDAO<T> {

    boolean create(Connection con, T t);

    T read(Connection con, int id);

    boolean update(Connection con, T t);

    boolean delete(Connection con, int id);

}
