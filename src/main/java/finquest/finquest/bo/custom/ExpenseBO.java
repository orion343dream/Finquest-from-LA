package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;
import finquest.finquest.dto.ExpenseDTO;
import finquest.finquest.tm.ExpenseTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface ExpenseBO extends SuperBO {
    ObservableList<ExpenseTM> getTotalExpense() throws SQLException, ClassNotFoundException;

    boolean saveExpense(ExpenseDTO expenseDTO) throws SQLException, ClassNotFoundException;

    boolean updateExpense() throws SQLException, ClassNotFoundException;

    boolean deleteExpense(String id) throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
    boolean edit(String text) throws SQLException, ClassNotFoundException;
    double getTotal() throws SQLException, ClassNotFoundException;
}
