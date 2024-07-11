package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.RegisterBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.UserDAO;
import finquest.finquest.dto.UserDTO;
import finquest.finquest.entity.User;

import java.sql.SQLException;

public class RegisterBOImpl implements RegisterBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.DAOType.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        User user = new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
        return userDAO.saveUser(user);
    }
}
