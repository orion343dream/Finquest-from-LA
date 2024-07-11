package finquest.finquest.dao.custom;

import finquest.finquest.dao.SuperDAO;
import finquest.finquest.entity.Budget;
import finquest.finquest.tm.BudgetTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface BudgetDAO extends  SuperDAO {
    boolean saveBudget(Budget budget) throws SQLException, ClassNotFoundException;
    boolean delete(String text) throws SQLException, ClassNotFoundException;
    boolean edit(String text) throws SQLException, ClassNotFoundException;
    ObservableList<BudgetTM> getData() throws SQLException, ClassNotFoundException;
    boolean update() throws SQLException, ClassNotFoundException;
    double totalBudget() throws SQLException, ClassNotFoundException;
    int getCount() throws SQLException, ClassNotFoundException;
    boolean save(Budget budget) throws SQLException, ClassNotFoundException;
}
