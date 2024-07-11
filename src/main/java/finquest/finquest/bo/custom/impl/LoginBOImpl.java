package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.LoginBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.UserDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean isValidLogin(String username, String password) throws SQLException, ClassNotFoundException {
        return userDAO.isValidLogin(username, password);
    }
}
