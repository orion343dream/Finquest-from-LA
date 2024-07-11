package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;
import finquest.finquest.dto.IncomeDTO;
import finquest.finquest.entity.Income;
import finquest.finquest.tm.IncomeTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public interface IncomeBO extends SuperBO {
    public ObservableList<IncomeTM> getTotalIncomeList() throws SQLException, ClassNotFoundException;
    public boolean saveIncome(IncomeDTO incomeDTO) throws SQLException, ClassNotFoundException;
    public boolean updateIncome() throws SQLException, ClassNotFoundException;
    public boolean deleteIncome(String id) throws SQLException, ClassNotFoundException;
    double getTotal() throws SQLException, ClassNotFoundException;
    boolean edit(String text) throws SQLException, ClassNotFoundException;
}
