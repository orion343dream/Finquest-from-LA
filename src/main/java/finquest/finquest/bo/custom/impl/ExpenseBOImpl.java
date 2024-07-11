package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.ExpenseBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.ExpenseDAO;
import finquest.finquest.dto.ExpenseDTO;
import finquest.finquest.entity.Expense;
import finquest.finquest.tm.ExpenseTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseBOImpl implements ExpenseBO {
    ExpenseDAO expenseDAO = (ExpenseDAO) DAOFactory.getDAO(DAOFactory.DAOType.EXPENSE);

    @Override
    public ObservableList<ExpenseTM> getTotalExpense() throws SQLException, ClassNotFoundException {
        return expenseDAO.getData();
    }

    @Override
    public boolean saveExpense(ExpenseDTO expenseDTO) throws SQLException, ClassNotFoundException {
        Expense expense = new Expense(expenseDTO.getId(), expenseDTO.getUserId(), expenseDTO.getAmount(), expenseDTO.getDescription(), expenseDTO.getDate(), expenseDTO.getCategory());
        return expenseDAO.save(expense);
    }

    @Override
    public boolean updateExpense() throws SQLException, ClassNotFoundException {
        return expenseDAO.update();
    }

    @Override
    public boolean deleteExpense(String id) throws SQLException, ClassNotFoundException {
        return expenseDAO.delete(id);
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return expenseDAO.getCount();
    }

    @Override
    public boolean edit(String text) throws SQLException, ClassNotFoundException {
        return expenseDAO.edit(text);
    }

    @Override
    public double getTotal() throws SQLException, ClassNotFoundException {
        return expenseDAO.getTotal();
    }
}
