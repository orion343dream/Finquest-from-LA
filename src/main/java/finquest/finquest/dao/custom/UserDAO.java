package finquest.finquest.dao.custom;

import finquest.finquest.dao.SuperDAO;
import finquest.finquest.entity.User;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO {
    boolean saveUser(User user) throws SQLException, ClassNotFoundException;
    boolean isValidLogin(String username, String password) throws SQLException, ClassNotFoundException;
}
