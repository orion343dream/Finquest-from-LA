package finquest.finquest.dao.custom;

import finquest.finquest.dao.SuperDAO;
import finquest.finquest.entity.Income;
import finquest.finquest.tm.IncomeTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface IncomeDAO extends SuperDAO {

    double totalIncome() throws SQLException, ClassNotFoundException;

    boolean edit(String incomeId) throws SQLException, ClassNotFoundException;

    int getCount() throws SQLException, ClassNotFoundException;

    ObservableList<IncomeTM> getData() throws SQLException, ClassNotFoundException;

    boolean save(Income income) throws SQLException, ClassNotFoundException;

    boolean saveIncome(Income income) throws SQLException, ClassNotFoundException;

    boolean delete(String incomeId) throws SQLException, ClassNotFoundException;

    double getTotal() throws SQLException, ClassNotFoundException;

    boolean update() throws SQLException, ClassNotFoundException;

}

