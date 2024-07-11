package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean isValidLogin(String username, String password) throws SQLException, ClassNotFoundException;
}
