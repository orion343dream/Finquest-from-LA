package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;
import finquest.finquest.dto.UserDTO;

import java.sql.SQLException;

public interface RegisterBO extends SuperBO {
    boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
