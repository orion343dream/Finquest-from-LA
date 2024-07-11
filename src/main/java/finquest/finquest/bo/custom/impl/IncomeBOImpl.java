package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.IncomeBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.FinantialGoalDAO;
import finquest.finquest.dao.custom.IncomeDAO;
import finquest.finquest.dto.IncomeDTO;
import finquest.finquest.entity.Income;
import finquest.finquest.tm.IncomeTM;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IncomeBOImpl implements IncomeBO {

    IncomeDAO incomeDAO = (IncomeDAO) DAOFactory.getDAO(DAOFactory.DAOType.INCOME);
    FinantialGoalDAO finantialGoalDAO = (FinantialGoalDAO) DAOFactory.getDAO(DAOFactory.DAOType.FINANCIALGOAL);


    @Override
    public ObservableList<IncomeTM> getTotalIncomeList() throws SQLException, ClassNotFoundException {
        return incomeDAO.getData();
    }

    @Override
    public boolean saveIncome(IncomeDTO incomeDTO) throws SQLException, ClassNotFoundException {
        Income income = new Income(incomeDTO.getId(), incomeDTO.getUserId(), incomeDTO.getAmount(), incomeDTO.getDescription(), incomeDTO.getDate(), incomeDTO.getCategory(), incomeDTO.getAccount());
        return incomeDAO.save(income);
    }

    @Override
    public boolean updateIncome() throws SQLException, ClassNotFoundException {
        return incomeDAO.update();
    }

    @Override
    public boolean deleteIncome(String id) throws SQLException, ClassNotFoundException {
        return incomeDAO.delete(id);
    }

    @Override
    public double getTotal() throws SQLException, ClassNotFoundException {
        return finantialGoalDAO.getIncome();
    }

    @Override
    public boolean edit(String text) throws SQLException, ClassNotFoundException {
        return incomeDAO.edit(text);
    }
}
