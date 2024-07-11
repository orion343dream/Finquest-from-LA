package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.DashboardBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.*;

import java.sql.SQLException;

public class DashboardBOImpl implements DashboardBO {

    IncomeDAO incomeDAO = (IncomeDAO) DAOFactory.getDAO(DAOFactory.DAOType.INCOME);
    ExpenseDAO expenseDAO = (ExpenseDAO) DAOFactory.getDAO(DAOFactory.DAOType.EXPENSE);
    BudgetDAO budgetDAO = (BudgetDAO) DAOFactory.getDAO(DAOFactory.DAOType.BUDGET);
    ReminderDAO reminderDAO = (ReminderDAO) DAOFactory.getDAO(DAOFactory.DAOType.REMINDER);
    FinantialGoalDAO finantialGoalDAO = (FinantialGoalDAO) DAOFactory.getDAO(DAOFactory.DAOType.FINANCIALGOAL);

    @Override
    public double getIncomeTotal() throws SQLException, ClassNotFoundException {
        return incomeDAO.getTotal();
    }

    @Override
    public int getIncomeCount() throws SQLException, ClassNotFoundException {
        return incomeDAO.getCount();
    }

    @Override
    public double totalExpense() throws SQLException, ClassNotFoundException {
        return expenseDAO.totalExpense();
    }

    @Override
    public int getExpenseCount() throws SQLException, ClassNotFoundException {
        return expenseDAO.getCount();
    }

    @Override
    public double totalBudget() throws SQLException, ClassNotFoundException {
        return budgetDAO.totalBudget();
    }

    @Override
    public int getBudgetCount() throws SQLException, ClassNotFoundException {
        return budgetDAO.getCount();
    }

    @Override
    public int getReminderCount() throws SQLException, ClassNotFoundException {
        return reminderDAO.getCount();
    }

    @Override
    public int getFinancialCount() throws SQLException, ClassNotFoundException {
        return finantialGoalDAO.getCount();
    }

    @Override
    public int getIncome() throws SQLException, ClassNotFoundException {
        return finantialGoalDAO.getIncome();
    }

    @Override
    public int getBudget() throws SQLException, ClassNotFoundException {
        return finantialGoalDAO.getBudget();
    }

    @Override
    public int getExpense() throws SQLException, ClassNotFoundException {
        return finantialGoalDAO.getExpense();
    }
}
