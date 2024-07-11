package finquest.finquest.dao.custom;

import finquest.finquest.dao.SuperDAO;
import finquest.finquest.entity.FinantialAccount;
import finquest.finquest.tm.FinancialAccountTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface FinancialAccountDAO extends SuperDAO {
    ObservableList<FinancialAccountTM> getData() throws SQLException, ClassNotFoundException;
    boolean save(FinantialAccount financialAccount) throws SQLException, ClassNotFoundException;
    boolean update() throws SQLException, ClassNotFoundException;
    boolean delete(String text) throws SQLException, ClassNotFoundException;
    boolean edit(String text) throws SQLException, ClassNotFoundException;
}
