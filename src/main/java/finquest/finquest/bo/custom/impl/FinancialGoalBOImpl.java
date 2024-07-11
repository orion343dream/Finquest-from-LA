package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.FinancialGoalBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.FinantialGoalDAO;
import finquest.finquest.dto.FinancialGoalDTO;
import finquest.finquest.entity.FinancialGoal;
import finquest.finquest.tm.FinancialGoalTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class FinancialGoalBOImpl implements FinancialGoalBO {
    private final FinantialGoalDAO financialGoalDAO = (FinantialGoalDAO) DAOFactory.getDAO(DAOFactory.DAOType.FINANCIALGOAL);

    @Override
    public boolean saveFinancialGoal(FinancialGoalDTO financialGoalDTO) throws SQLException, ClassNotFoundException {
        FinancialGoal financialGoal = new FinancialGoal(financialGoalDTO.getName(),financialGoalDTO.getAmount(),financialGoalDTO.getDate(),financialGoalDTO.getCategory());
        return financialGoalDAO.save(financialGoal);
    }

    @Override
    public boolean updateFinancialGoal(FinancialGoalDTO financialGoalDTO) throws SQLException, ClassNotFoundException {
        FinancialGoal financialGoal = new FinancialGoal(
                financialGoalDTO.getName(),
                financialGoalDTO.getAmount(),
                financialGoalDTO.getDate(),
                financialGoalDTO.getCategory()
        );
        return financialGoalDAO.update(financialGoal);
    }

    @Override
    public boolean deleteFinancialGoal(String id) throws SQLException, ClassNotFoundException {
        return financialGoalDAO.delete(id);
    }

    @Override
    public ObservableList<FinancialGoalTM> getAll() throws SQLException, ClassNotFoundException {
        return financialGoalDAO.getData();
    }

    @Override
    public boolean edit(String name) throws SQLException, ClassNotFoundException {
        return financialGoalDAO.edit(name);
    }
}
