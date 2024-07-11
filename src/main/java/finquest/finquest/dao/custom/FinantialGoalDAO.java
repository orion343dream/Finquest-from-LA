package finquest.finquest.dao.custom;

import finquest.finquest.dao.SuperDAO;
import finquest.finquest.entity.Finacialgoal;
import finquest.finquest.entity.FinancialGoal;
import finquest.finquest.tm.FinancialGoalTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface FinantialGoalDAO extends SuperDAO {
        boolean updateAchieve(int id) throws SQLException, ClassNotFoundException;
        boolean updateFailed(int id) throws SQLException, ClassNotFoundException;
        int getCount() throws SQLException, ClassNotFoundException;
        int getIncome() throws SQLException, ClassNotFoundException;
        int getExpense() throws SQLException, ClassNotFoundException;
        int getBudget() throws SQLException, ClassNotFoundException;
        boolean update(FinancialGoal financialGoal) throws SQLException, ClassNotFoundException;
        ObservableList<FinancialGoalTM> getData() throws SQLException, ClassNotFoundException;
        boolean save(FinancialGoal financialGoal) throws SQLException, ClassNotFoundException;

        boolean delete(String name) throws SQLException, ClassNotFoundException;

        boolean edit(String name) throws SQLException, ClassNotFoundException;

        ObservableList<Finacialgoal> getIncomeGoal() throws SQLException, ClassNotFoundException;

        ObservableList<Finacialgoal> getExpenseGoal() throws SQLException, ClassNotFoundException;

        ObservableList<Finacialgoal> getBudgetGoal() throws SQLException, ClassNotFoundException;


    }
