package db.transaction;


import java.sql.Connection;

public interface Transaction<T> {

    T execute(Connection con);

}