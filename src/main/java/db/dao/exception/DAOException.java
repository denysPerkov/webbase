package db.dao.exception;

public class DAOException extends RuntimeException {

    public DAOException(String mes, Exception ex) {
        super(mes, ex);
    }

}