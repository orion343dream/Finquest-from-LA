package finquest.finquest.dao.custom.impl;

import finquest.finquest.dao.SQLUtil;
import finquest.finquest.dao.custom.UserDAO;
import finquest.finquest.entity.User;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    public static User user;

    @Override
    public boolean isValidLogin(String username, String password) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT password,user_id FROM user WHERE username = ?",username);

        if (resultSet.next()){
            if (password.equals(resultSet.getString("password"))){
                user = new User(resultSet.getString(2),username,password);
                return true;
            } else {
                new Alert(Alert.AlertType.ERROR,"Incorrect password !!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR,"Incorrect username !!").show();
        }
        return false;
    }

    @Override
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO user (user_id, username, password) VALUES(?, ?, ?)",user.getId(),user.getUsername(),user.getPassword());
    }
}
