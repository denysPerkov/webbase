package service.impl;

import db.dao.DAOFactory;
import db.dao.UserDAO;
import db.transaction.Transaction;
import db.transaction.TransactionManager;
import model.User;
import service.UserService;

import java.sql.Connection;

public class UserServiceImpl implements UserService {

    private TransactionManager transactionManager;
    private DAOFactory daoFactory;
    private UserDAO userDAO;

    public UserServiceImpl(TransactionManager transactionManager, DAOFactory daoFactory, UserDAO userDAO) {
        this.daoFactory = daoFactory;
        this.userDAO = userDAO;
        this.transactionManager = transactionManager;
    }


    @Override
    public boolean add(final User user) {
        boolean result = transactionManager.doTask(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection con) {
                return userDAO.create(con, user);
            }
        });
        return result;
    }

    public boolean login(final User user) {
        boolean result = transactionManager.doTask(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection con) {
                 if(userDAO.read(con, user.getIdUser()).equals(user)) {
                     return true;
                 }
                return false;
            }
        });

        return result;
    }

    @Override
    public boolean login(final String login, final String pass) {
        boolean result = transactionManager.doTask(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection con) {
                User user = userDAO.readByLogin(con, login);
                if(user.getLogin() != null && user.getPassword().equals(pass)){
                    return true;
                }
                return false;
            }
        });

        return result;
    }

    @Override
    public boolean remove(final int id) {
        boolean result = transactionManager.doTask(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection con) {
                return userDAO.delete(con, id);
            }
        });

        return result;
    }

    @Override
    public boolean update(final User user) {
        boolean result = transactionManager.doTask(new Transaction<Boolean>() {
            @Override
            public Boolean execute(Connection con) {
                return userDAO.update(con, user);
            }
        });

        return result;
    }

    @Override
    public User getUser(final String login) {
        User result = transactionManager.doTask(new Transaction<User>() {
            @Override
            public User execute(Connection con) {
                return userDAO.readByLogin(con, login);
            }
        });

        return result;
   }

}
