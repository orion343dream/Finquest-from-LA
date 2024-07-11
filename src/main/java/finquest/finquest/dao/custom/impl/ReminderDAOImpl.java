package finquest.finquest.dao.custom.impl;

import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.SQLUtil;
import finquest.finquest.dao.custom.BudgetDAO;
import finquest.finquest.dao.custom.FinantialGoalDAO;
import finquest.finquest.dao.custom.IncomeDAO;
import finquest.finquest.dao.custom.ReminderDAO;
import finquest.finquest.entity.*;
import finquest.finquest.tm.ReminderTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReminderDAOImpl implements ReminderDAO {
    BudgetDAO budgetDAO = (BudgetDAO) DAOFactory.getDAO(DAOFactory.DAOType.BUDGET);
    IncomeDAO incomeDAO = (IncomeDAO) DAOFactory.getDAO(DAOFactory.DAOType.INCOME);
    FinantialGoalDAO finantialGoalDAO = (FinantialGoalDAO) DAOFactory.getDAO(DAOFactory.DAOType.FINANCIALGOAL);

    @Override
    public boolean save(Income income, ObservableList<Finacialgoal> incomeGoal) throws SQLException, ClassNotFoundException {
        for (Finacialgoal finacialgoal : incomeGoal) {
            double value = finacialgoal.getAmount() - (incomeDAO.totalIncome());
            double percentage = (incomeDAO.totalIncome() / finacialgoal.getAmount()) * 100;
            String text = null;
            if (value > 0){
                text = "Your " + finacialgoal.getName() + ", " + finacialgoal.getCategory() + " type goal need " + value + " (" +  percentage + "%) to complete the goal";
            } else {
                text = "Your " + finacialgoal.getName() + ", " + finacialgoal.getCategory() + " type goal is complete succusfully ";
                try {
                    if (!finantialGoalDAO.updateAchieve(finacialgoal.getId())){
                        return false;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            Reminder reminder = new Reminder(UserDAOImpl.user.getId(), Date.valueOf(LocalDate.now()),text, finacialgoal.getId());
            if (!save(reminder)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(Reminder reminder) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO reminder (user_id, reminder_date, reminder_text, goal_id) VALUES (?,?,?,?)",
                reminder.getUserId(), reminder.getDate(), reminder.getText(), reminder.getGoalId());
    }

    @Override
    public ObservableList<ReminderTM> getData() throws SQLException, ClassNotFoundException {
        ObservableList<ReminderTM> list = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM reminder");
        while (rs.next()){
            list.add(new ReminderTM(Date.valueOf(LocalDate.now()),rs.getString("reminder_text")));
        }
        return list;
    }

    @Override
    public boolean save(Budget budget, ObservableList<Finacialgoal> budgetGoal) throws SQLException, ClassNotFoundException {
        for (Finacialgoal finacialgoal : budgetGoal) {
            double value = finacialgoal.getAmount() - (budgetDAO.totalBudget());
            double percentage = (budgetDAO.totalBudget() / finacialgoal.getAmount()) * 100;
            String text = null;
            if (value > 0){
                text = "Your " + finacialgoal.getName() + ", " + finacialgoal.getCategory() + "  type goal need " + value + " (" +  percentage + "%) to complete the goal";
            } else {
                text = "Your" + finacialgoal.getName() + ", " + finacialgoal.getCategory() +" type goal is complete succusfully ";
                try {
                    if (!finantialGoalDAO.updateAchieve(finacialgoal.getId())){
                        return false;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            Reminder reminder = new Reminder(UserDAOImpl.user.getId(), Date.valueOf(LocalDate.now()),text, finacialgoal.getId());
            if (!save(reminder)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(Expense expense, ObservableList<Finacialgoal> expenseGoal) throws SQLException, ClassNotFoundException {
        for (Finacialgoal finacialgoal : expenseGoal) {
            double value = (budgetDAO.totalBudget() + incomeDAO.totalIncome()) - finacialgoal.getAmount() ;
            double percentage = (finacialgoal.getAmount() / (budgetDAO.totalBudget() + incomeDAO.totalIncome())) * 100;
            String text = null;
            if (value > 0){
                text = "Your " + finacialgoal.getName() + ", " + finacialgoal.getCategory() + " type goal could be able to spent " + value + " (" +  percentage + "%) of money without fail the goal";
            } else {
                text = "Your " + finacialgoal.getName() + ", " + finacialgoal.getCategory() + " type goal is failed";
                try {
                    if (!finantialGoalDAO.updateFailed(finacialgoal.getId())){
                        return false;
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            Reminder reminder = new Reminder(UserDAOImpl.user.getId(), Date.valueOf(LocalDate.now()),text, finacialgoal.getId());
            if (!save(reminder)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(String text) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("Delete from reminder where reminder_text = ?",text);
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {

        ResultSet set = SQLUtil.execute("select count(*) from reminder order by reminder_id");
        if (set.next()){
            return set.getInt(1);
        }
        return 0;
    }
}
