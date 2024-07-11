package finquest.finquest.dao.custom.impl;

import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.SQLUtil;
import finquest.finquest.dao.custom.BudgetDAO;
import finquest.finquest.dao.custom.FinantialGoalDAO;
import finquest.finquest.dao.custom.ReminderDAO;
import finquest.finquest.db.DbConnection;
import finquest.finquest.entity.Budget;
import finquest.finquest.tm.BudgetTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetDAOImpl implements BudgetDAO {

    public static Budget budget;

    @Override
    public boolean save(Budget budget) throws SQLException,ClassNotFoundException {
        ReminderDAO reminderDAO = (ReminderDAO) DAOFactory.getDAO(DAOFactory.DAOType.REMINDER);
        FinantialGoalDAO finantialGoalDAO = (FinantialGoalDAO) DAOFactory.getDAO(DAOFactory.DAOType.FINANCIALGOAL);
        Connection conn = DbConnection.getInstance().getConnection();
        conn.setAutoCommit(false);

        try {
            if (saveBudget(budget)){
                if (reminderDAO.save(budget,finantialGoalDAO.getBudgetGoal())){
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
    public boolean saveBudget(Budget budget) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO budget (user_id,budget_name,amount,start_date,end_date) VALUES (?,?,?,?,?)",budget.getUserId(),budget.getName(),budget.getAmount(),budget.getStartDate(),budget.getEndDate());
    }


    @Override
    public boolean delete(String text) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM budget WHERE budget_name = ?",text);
    }

    @Override
    public boolean edit(String text) throws SQLException, ClassNotFoundException {
        budget = null;

        ResultSet rs = SQLUtil.execute("SELECT * FROM budget WHERE budget_name = ?",text);

        if (rs.next()){
            budget = new Budget(rs.getString("user_id"), rs.getString("budget_name"),rs.getDouble("amount"),rs.getDate("start_date"),rs.getDate("end_date"));
            return true;
        } else {
            new Alert(Alert.AlertType.CONFIRMATION,"can't find name").show();
        }
        return false;
    }

    @Override
    public ObservableList<BudgetTM> getData() throws SQLException, ClassNotFoundException {
        ObservableList<BudgetTM> obList = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM budget");

        while (rs.next()) {
            obList.add(new BudgetTM(rs.getString("budget_name"), rs.getDouble("amount"), rs.getDate("start_date"), rs.getDate("end_date")));
        }

        return obList;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE budget SET budget_name = ?, amount = ?, start_date = ?, end_date = ? WHERE budget_name = ?",budget.getName(),budget.getAmount(),budget.getStartDate(),budget.getEndDate(),budget.getName());
    }

    @Override
    public double totalBudget() throws SQLException, ClassNotFoundException {
        double total = 0;

        ResultSet rs = SQLUtil.execute("SELECT amount FROM budget");

        while (rs.next()) {
            total += rs.getDouble("amount");
        }
        return total;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        int count = 0;

        ResultSet rs = SQLUtil.execute("select count(*) from budget order by budget_name");

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;
    }
}
