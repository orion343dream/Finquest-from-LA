package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;
import finquest.finquest.tm.ReminderTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ReminderBO extends SuperBO {
   boolean DeleteReminder(String text) throws SQLException, ClassNotFoundException;
   ObservableList<ReminderTM> getAll() throws SQLException, ClassNotFoundException;
}
