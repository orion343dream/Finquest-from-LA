package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;
import finquest.finquest.dto.FinantialAccountDTO;
import finquest.finquest.tm.FinancialAccountTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface FinantialAccountBO extends SuperBO {
    boolean saveFinancialAccount(FinantialAccountDTO financialAccountDTO) throws SQLException, ClassNotFoundException ;
    boolean updateFinancialAccount() throws SQLException, ClassNotFoundException;
    boolean deleteFinancialAccount(String id) throws SQLException, ClassNotFoundException;
    ObservableList<FinancialAccountTM> getData() throws SQLException, ClassNotFoundException;
    boolean edit(String text) throws SQLException, ClassNotFoundException;
}
