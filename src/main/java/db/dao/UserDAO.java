package db.dao;

import model.User;

import java.sql.Connection;
import java.util.List;

public interface UserDAO extends GenericDAO<User> {

    List<User> findALl(Connection con);

    User readByLogin(Connection con, String login);

}
