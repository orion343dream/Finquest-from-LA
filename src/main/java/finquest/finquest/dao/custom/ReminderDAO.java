package finquest.finquest.dao.custom;

import finquest.finquest.dao.SuperDAO;
import finquest.finquest.entity.*;
import finquest.finquest.tm.ReminderTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public interface ReminderDAO extends  SuperDAO {
    boolean save(Reminder reminder) throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
    boolean delete(String text) throws SQLException, ClassNotFoundException;
    boolean save(Income income, ObservableList<Finacialgoal> incomeGoal) throws SQLException, ClassNotFoundException;
    ObservableList<ReminderTM> getData() throws SQLException, ClassNotFoundException;
    boolean save(Budget budget, ObservableList<Finacialgoal> budgetGoal) throws SQLException, ClassNotFoundException;
    boolean save(Expense expense, ObservableList<Finacialgoal> expenseGoal) throws SQLException, ClassNotFoundException;
}
