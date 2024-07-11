package finquest.finquest.dao.custom.impl;

import finquest.finquest.dao.SQLUtil;
import finquest.finquest.dao.custom.FinancialAccountDAO;
import finquest.finquest.entity.FinantialAccount;
import finquest.finquest.tm.FinancialAccountTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FinancialAccountDAOImpl implements FinancialAccountDAO {

    public static FinantialAccount financialAccount;

    @Override
    public ObservableList<FinancialAccountTM> getData() throws SQLException, ClassNotFoundException {
        ObservableList<FinancialAccountTM> obList = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM financialaccount");

        while (rs.next()) {
            obList.add(new FinancialAccountTM(rs.getString("account_id"),rs.getString("account_name"), rs.getInt("number"), rs.getString("account_type"),rs.getDouble("balance") ));
        }

        return obList;
    }

    @Override
    public boolean save(FinantialAccount account) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO financialAccount (account_id, user_id,account_name,number, account_type,balance) VALUES (?,?,?,?,?,?) ", financialAccount.getId(), financialAccount.getUserId(), financialAccount.getName(), financialAccount.getAccountNumber(), financialAccount.getType(),financialAccount.getBalance());
    }

    @Override
    public boolean delete(String text) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM financialAccount WHERE account_id = ?",text);
    }

    @Override
    public boolean edit(String id) throws SQLException, ClassNotFoundException {

        ResultSet rs = SQLUtil.execute("SELECT * FROM financialAccount WHERE account_id = ?",id);

        if (rs.next()) {
            financialAccount = new FinantialAccount(
                    rs.getString("account_id"),
                    rs.getString("user_id"),
                    rs.getString("account_name"),
                    rs.getInt("number"),
                    rs.getString("account_type"),
                    rs.getDouble("balance")
            );
            return true;
        } else {
            new Alert(Alert.AlertType.CONFIRMATION,"can't find name").show();
        }
        return false;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE financialAccount SET account_name=?, number=?, account_type=?,balance=? WHERE account_id=?", financialAccount.getName(), financialAccount.getAccountNumber(), financialAccount.getType(),financialAccount.getBalance(),financialAccount.getId());
    }
}
