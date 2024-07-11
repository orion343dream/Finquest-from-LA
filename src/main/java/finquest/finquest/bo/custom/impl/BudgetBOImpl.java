package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.BudgetBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.BudgetDAO;
import finquest.finquest.dao.custom.ExpenseDAO;
import finquest.finquest.dao.custom.IncomeDAO;
import finquest.finquest.dto.BudgetDTO;
import finquest.finquest.entity.Budget;
import finquest.finquest.tm.BudgetTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class BudgetBOImpl implements BudgetBO {
    BudgetDAO budgetDAO;
    ExpenseDAO expenseDAO;
    IncomeDAO incomeDAO;

    public BudgetBOImpl() {
        this.budgetDAO = (BudgetDAO) DAOFactory.getDAO(DAOFactory.DAOType.BUDGET);
        this.expenseDAO = (ExpenseDAO) DAOFactory.getDAO(DAOFactory.DAOType.EXPENSE);
        this.incomeDAO = (IncomeDAO) DAOFactory.getDAO(DAOFactory.DAOType.INCOME);
    }

    @Override
    public ObservableList<BudgetTM> getAllBudgets() throws SQLException, ClassNotFoundException {
       return budgetDAO.getData();
    }

    @Override
    public boolean saveBudget(BudgetDTO budgetDTO) throws SQLException, ClassNotFoundException {
        Budget budget = new Budget(budgetDTO.getUserId(), budgetDTO.getName(), budgetDTO.getAmount(), budgetDTO.getStartDate(), budgetDTO.getEndDate());
        return budgetDAO.save(budget);
    }

    @Override
    public boolean updateBudget() throws SQLException, ClassNotFoundException {
        return budgetDAO.update();
    }

    @Override
    public boolean deleteBudget(String name) throws SQLException, ClassNotFoundException {
        return budgetDAO.delete(name);
    }

    @Override
    public double totalBudget() throws SQLException, ClassNotFoundException {
        return budgetDAO.totalBudget();
    }

    @Override
    public boolean edit(String text) throws SQLException, ClassNotFoundException {
        return budgetDAO.edit(text);
    }

    @Override
    public double totalExpense() throws SQLException, ClassNotFoundException {
        return expenseDAO.totalExpense();
    }

    @Override
    public double totalIncome() throws SQLException, ClassNotFoundException {
        return incomeDAO.totalIncome();
    }
}
