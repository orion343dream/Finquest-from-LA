package finquest.finquest.dao.custom;

import finquest.finquest.dao.SuperDAO;
import finquest.finquest.entity.Expense;
import finquest.finquest.tm.ExpenseTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface ExpenseDAO extends  SuperDAO {
    double totalExpense() throws SQLException, ClassNotFoundException;
    ObservableList<ExpenseTM> getData() throws SQLException, ClassNotFoundException;
    boolean save(Expense expense) throws SQLException, ClassNotFoundException;
    boolean saveExpense(Expense expense) throws SQLException, ClassNotFoundException;
    boolean delete(String text) throws SQLException, ClassNotFoundException;
    boolean edit(String text) throws SQLException, ClassNotFoundException;
    boolean update() throws SQLException, ClassNotFoundException;
    double getTotal() throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
}
