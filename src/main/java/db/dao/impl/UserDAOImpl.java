package db.dao.impl;

import db.dao.UserDAO;
import model.Role;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> findALl(Connection con) {
        return null;
    }



    @Override
    public boolean create(Connection con, User user) {
        PreparedStatement pStmt = null;

        String insertTableSQL = "INSERT INTO user"
                + "(firstName, lastName, login, password, email) VALUES"
                + "(?,?,?,?,?)";

        try {
            pStmt = con.prepareStatement(insertTableSQL);

            pStmt.setString(1, user.getFirstName());
            pStmt.setString(2, user.getLastName());
            pStmt.setString(3, user.getLogin());
            pStmt.setString(4, user.getPassword());
            pStmt.setString(5, user.getEmail());
            pStmt.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User read(Connection con, int id) {

        PreparedStatement pStmt = null;
        String selectSQL = "SELECT * FROM user WHERE idUser = ?";
        User user = null;
        try {
            pStmt = con.prepareStatement(selectSQL);
            pStmt.setInt(1, id);
            ResultSet rs = pStmt.executeQuery();

            user = new User();

            while (rs.next()) {
                user.setIdUser(rs.getInt("idUser"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                if(rs.getString("role").equals(Role.ADMIN.toString())){
                    user.setRole(Role.ADMIN);
                }else{
                    user.setRole(Role.USER);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean update(Connection con, User user) {
        PreparedStatement pStmt = null;

        String updateTableSQL = "UPDATE user SET firstName=?, lastName=?, login=?, password=?, email=? " +
                "WHERE idUser = ?";

        try {
            pStmt = con.prepareStatement(updateTableSQL);
            pStmt.setString(1, user.getFirstName());
            pStmt.setString(2, user.getLastName());
            pStmt.setString(3, user.getLogin());
            pStmt.setString(4, user.getPassword());
            pStmt.setString(5, user.getEmail());
            pStmt.setInt(6, user.getIdUser());


            pStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Connection con, int id) {
        PreparedStatement pStmt = null;
        String deleteSQL = "DELETE FROM user where idUser = ?";

        try {
            pStmt = con.prepareStatement(deleteSQL);
            pStmt.setInt(1, id);
            pStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User readByLogin(Connection con, String login) {
        PreparedStatement pStmt = null;
        String selectSQL = "SELECT * FROM user WHERE login LIKE ?";
        User user = new User();
        try {
            pStmt = con.prepareStatement(selectSQL);
            pStmt.setString(1, login);
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                user.setIdUser(rs.getInt("idUser"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setLogin(rs.getString("login"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                if(rs.getString("role").equals(Role.ADMIN.toString())){
                    user.setRole(Role.ADMIN);
                }else{
                    user.setRole(Role.USER);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
