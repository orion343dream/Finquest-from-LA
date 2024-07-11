package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.ReminderBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.ReminderDAO;
import finquest.finquest.entity.Reminder;
import finquest.finquest.tm.ReminderTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReminderBOImpl implements ReminderBO {

    ReminderDAO reminderDAO = (ReminderDAO) DAOFactory.getDAO(DAOFactory.DAOType.REMINDER);

    @Override
    public boolean DeleteReminder(String text) throws SQLException, ClassNotFoundException {
        return reminderDAO.delete(text);
    }

    @Override
    public ObservableList<ReminderTM> getAll() throws SQLException, ClassNotFoundException {
        return reminderDAO.getData();
    }

}
