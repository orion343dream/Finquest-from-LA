package finquest.finquest.bo.custom.impl;

import finquest.finquest.bo.custom.FinantialAccountBO;
import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.custom.FinancialAccountDAO;
import finquest.finquest.dao.custom.impl.FinancialAccountDAOImpl;
import finquest.finquest.dto.FinantialAccountDTO;
import finquest.finquest.entity.FinantialAccount;
import finquest.finquest.tm.FinancialAccountTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class FinancialAccountBOImpl implements FinantialAccountBO {
    FinancialAccountDAO financialAccountDAO = (FinancialAccountDAO) DAOFactory.getDAO(DAOFactory.DAOType.FINANCIALACCOUNT);

    @Override
    public boolean saveFinancialAccount(FinantialAccountDTO financialAccountDTO) throws SQLException, ClassNotFoundException {
        FinantialAccount financialAccount = new FinantialAccount(financialAccountDTO.getId(),financialAccountDTO.getUserId(), financialAccountDTO.getName(), financialAccountDTO.getAccountNumber(), financialAccountDTO.getType(), financialAccountDTO.getBalance());
        return financialAccountDAO.save(financialAccount);
    }

    @Override
    public boolean updateFinancialAccount() throws SQLException, ClassNotFoundException {
        return financialAccountDAO.update();
    }

    @Override
    public boolean deleteFinancialAccount(String id) throws SQLException, ClassNotFoundException {
        return financialAccountDAO.delete(id);
    }

    @Override
    public ObservableList<FinancialAccountTM> getData() throws SQLException, ClassNotFoundException {
        return financialAccountDAO.getData();
    }

    @Override
    public boolean edit(String text) throws SQLException, ClassNotFoundException {
        return financialAccountDAO.edit(text);
    }

}
