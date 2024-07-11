package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;

import java.sql.SQLException;

public interface DashboardBO extends SuperBO {

    double getIncomeTotal() throws SQLException, ClassNotFoundException;
    int getIncomeCount() throws SQLException, ClassNotFoundException;
    double totalExpense() throws SQLException, ClassNotFoundException;
    int getExpenseCount() throws SQLException, ClassNotFoundException;
    double totalBudget() throws SQLException, ClassNotFoundException;
    int getBudgetCount() throws SQLException, ClassNotFoundException;
    int getReminderCount() throws SQLException, ClassNotFoundException;
    int getFinancialCount() throws SQLException, ClassNotFoundException;
    int getIncome() throws SQLException, ClassNotFoundException;
    int getBudget() throws SQLException, ClassNotFoundException;
    int getExpense() throws SQLException, ClassNotFoundException;
}
