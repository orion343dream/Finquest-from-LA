package finquest.finquest.bo.custom;

import finquest.finquest.bo.SuperBO;
import finquest.finquest.dto.FinancialGoalDTO;
import finquest.finquest.tm.FinancialGoalTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface FinancialGoalBO extends SuperBO {

    boolean saveFinancialGoal(FinancialGoalDTO financialGoalDTO) throws SQLException, ClassNotFoundException;

    boolean updateFinancialGoal(FinancialGoalDTO financialGoalDTO) throws SQLException, ClassNotFoundException ;

        boolean deleteFinancialGoal(String id) throws SQLException, ClassNotFoundException;

    ObservableList<FinancialGoalTM> getAll() throws SQLException, ClassNotFoundException;
    boolean edit(String name) throws SQLException, ClassNotFoundException;
    }


