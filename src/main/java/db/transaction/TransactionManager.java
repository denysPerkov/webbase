package db.transaction;

import db.dao.DAOFactory;
import db.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    public <T> T doTask(Transaction<T> t) {
        Connection con = null;
        try {
            con = DAOFactory.getDataSource().getConnection();
            con.setAutoCommit(false);
            con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            T value = t.execute(con);

            con.commit();
            return value;
        } catch (SQLException ex) {
            tryToRollback(con);
            throw new DAOException("Cannot do transaction", ex);
        } finally {
            tryToClose(con);
        }
    }

    private void tryToRollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Cannot rollback a connection", ex);
            }
        }
    }

    private void tryToClose(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
              throw new DAOException("Cannot close a connection", ex);
            }
        }
    }
}
