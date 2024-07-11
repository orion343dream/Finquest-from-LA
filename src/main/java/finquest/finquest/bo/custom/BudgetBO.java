package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;
import finquest.finquest.dto.BudgetDTO;
import finquest.finquest.tm.BudgetTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BudgetBO extends SuperBO {
    ObservableList<BudgetTM> getAllBudgets() throws SQLException, ClassNotFoundException;

    boolean saveBudget(BudgetDTO budgetDTO) throws SQLException, ClassNotFoundException;

    boolean updateBudget() throws SQLException, ClassNotFoundException;

    boolean deleteBudget(String name) throws SQLException, ClassNotFoundException;

    double totalBudget() throws SQLException, ClassNotFoundException;

    boolean edit(String text) throws SQLException, ClassNotFoundException;

    double totalExpense() throws SQLException, ClassNotFoundException;

    double totalIncome() throws SQLException, ClassNotFoundException;
}
