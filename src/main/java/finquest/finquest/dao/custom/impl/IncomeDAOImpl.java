package finquest.finquest.dao.custom.impl;

import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.SQLUtil;
import finquest.finquest.dao.custom.FinantialGoalDAO;
import finquest.finquest.dao.custom.IncomeDAO;
import finquest.finquest.dao.custom.ReminderDAO;
import finquest.finquest.db.DbConnection;
import finquest.finquest.entity.Income;
import finquest.finquest.tm.IncomeTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeDAOImpl implements IncomeDAO {

    public static Income income;

    @Override
    public double totalIncome() throws SQLException, ClassNotFoundException {
        double total = 0;

        ResultSet rs = SQLUtil.execute("SELECT amount FROM income");

        while (rs.next()) {
            total += rs.getDouble("amount");
        }
        return total;
    }

    @Override
    public ObservableList<IncomeTM> getData() throws SQLException, ClassNotFoundException {
        ObservableList<IncomeTM> obList = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM income");

        while (rs.next()) {
            obList.add(new IncomeTM(rs.getString("income_id"),rs.getDouble("amount"),rs.getString("description"),rs.getDate("date"),rs.getString("category"), rs.getString("account")));
        }

        return obList;
    }

    @Override
    public boolean save(Income income) throws SQLException {
        ReminderDAO reminderDAO = (ReminderDAO) DAOFactory.getDAO(DAOFactory.DAOType.REMINDER);
        FinantialGoalDAO finantialGoalDAO = (FinantialGoalDAO) DAOFactory.getDAO(DAOFactory.DAOType.FINANCIALGOAL);

        Connection conn = DbConnection.getInstance().getConnection();
        conn.setAutoCommit(false);

        try {
            if (saveIncome(income)){
                System.out.println("1");
                if (reminderDAO.save(income,finantialGoalDAO.getIncomeGoal())){
                    conn.commit();
                    return true;
                }
            }
            conn.rollback();
            return false;
        } catch (Exception e){
            System.out.println(e.getMessage());
            conn.rollback();
            return false;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    @Override
    public boolean saveIncome(Income income) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO income (income_id, user_id, amount, description, date,category, account) VALUES (?,?,?,?,?,?,?)",
                income.getId(), income.getUserId(), income.getAmount(), income.getDescription(), income.getDate(),income.getCategory(),income.getAccount());
    }

    @Override
    public boolean delete(String text) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM income WHERE income_id = ?",text);
    }

    @Override
    public boolean edit(String text) throws SQLException, ClassNotFoundException {

        ResultSet rs = SQLUtil.execute("SELECT * FROM income WHERE income_id = ?",text);

        if (rs.next()){
            income = new Income(rs.getString("income_id"),rs.getString("user_id"),rs.getDouble("amount"),rs.getString("description"),rs.getDate("date"),rs.getString("category"),rs.getString("account"));
            return true;
        } else {
            new Alert(Alert.AlertType.CONFIRMATION,"can't find name").show();
        }
        return false;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE income SET amount = ?, description = ?, date = ?, category = ?, account = ? WHERE income_id = ?", income.getAmount(), income.getDescription(), income.getDate(), income.getCategory(), income.getAccount(), income.getId());
    }

    @Override
    public double getTotal() throws SQLException, ClassNotFoundException {
        double total = 0;

        ResultSet set = SQLUtil.execute("SELECT amount FROM income");

        while (set.next()) {
            total += set.getDouble("amount");
        }
        return total;
    }
    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        int count = 0;

        ResultSet rs = SQLUtil.execute("select count(*) from income order by income_id");

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;
    }


}



