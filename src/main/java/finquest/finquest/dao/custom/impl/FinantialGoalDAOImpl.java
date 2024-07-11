package finquest.finquest.dao.custom.impl;

import finquest.finquest.dao.SQLUtil;
import finquest.finquest.dao.custom.FinantialGoalDAO;
import finquest.finquest.db.DbConnection;
import finquest.finquest.entity.Finacialgoal;
import finquest.finquest.entity.FinancialGoal;
import finquest.finquest.tm.FinancialGoalTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FinantialGoalDAOImpl implements FinantialGoalDAO {

    public static FinancialGoal financialGoal;

    @Override
    public ObservableList<FinancialGoalTM> getData() throws SQLException, ClassNotFoundException {
        ObservableList<FinancialGoalTM> obList = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM financialgoal");
        while (rs.next()) {
            obList.add(new FinancialGoalTM(rs.getString("name"), rs.getDouble("target_amount"), rs.getDate("target_date"), rs.getString("category")));
        }

        return obList;
    }

    @Override
    public boolean save(FinancialGoal financialGoal) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO financialgoal (name, target_amount, target_date, category) VALUES (?, ?, ?, ?) ",financialGoal.getName(),financialGoal.getAmount(),financialGoal.getDate(),financialGoal.getCategory());
    }

    @Override
    public boolean delete(String name) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM financialGoal WHERE name = ?",name);
    }

    @Override
    public boolean edit(String name) throws SQLException, ClassNotFoundException {

        ResultSet rs = SQLUtil.execute("SELECT * FROM financialGoal WHERE name = ?",name);

        if (rs.next()) {
            financialGoal = new FinancialGoal(
                    rs.getString("name"),
                    rs.getDouble("target_amount"),
                    rs.getDate("target_date"),
                    rs.getString("category")
            );
            return true;
        } else {
            new Alert(Alert.AlertType.CONFIRMATION,"can't find name").show();
        }
        return false;
    }
    @Override
    public boolean update(FinancialGoal financialGoal) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE financialGoal SET target_amount = ?, target_date = ?, category = ? WHERE name = ?", financialGoal.getAmount(),financialGoal.getDate(), financialGoal.getCategory(),financialGoal.getName());
    }

    @Override
    public ObservableList<Finacialgoal> getIncomeGoal() throws SQLException, ClassNotFoundException {
        ObservableList<Finacialgoal> obList = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM financialgoal");
        while (rs.next()) {
            if (rs.getString("category").equals("Income") && rs.getString("achieved") == null){
                obList.add(new Finacialgoal(rs.getInt("goal_id"),rs.getString("name"),rs.getDouble("target_amount"),rs.getDate("target_date"),rs.getString("category")));
            }
        }
        return obList;
    }

    @Override
    public ObservableList<Finacialgoal> getExpenseGoal() throws SQLException, ClassNotFoundException {
        ObservableList<Finacialgoal> obList = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM financialgoal");
        while (rs.next()) {
            if (rs.getString("category").equals("Expense") && rs.getString("achieved") == null){
                obList.add(new Finacialgoal(rs.getInt("goal_id"),rs.getString("name"),rs.getDouble("target_amount"),rs.getDate("target_date"),rs.getString("category")));
            }
        }
        return obList;
    }

    @Override
    public ObservableList<Finacialgoal> getBudgetGoal() throws SQLException, ClassNotFoundException {
        ObservableList<Finacialgoal> obList = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM financialgoal");
        while (rs.next()) {
            if (rs.getString("category").equals("Budget") && rs.getString("achieved") == null){
                obList.add(new Finacialgoal(rs.getInt("goal_id"),rs.getString("name"),rs.getDouble("target_amount"),rs.getDate("target_date"),rs.getString("category")));
            }
        }
        return obList;
    }

    @Override
    public boolean updateAchieve(int id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE financialgoal SET achieved = ? WHERE goal_id = ?", "Achieved", id);
    }
    @Override
    public boolean updateFailed(int id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE financialgoal SET achieved = ? WHERE goal_id = ?", "Failed", id);
    }
    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) FROM financialgoal ORDER BY goal_id");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public int getIncome() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) FROM financialgoal WHERE category = 'Income' ORDER BY goal_id");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public int getExpense() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) FROM financialgoal WHERE category = 'Expense' ORDER BY goal_id");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }

    @Override
    public int getBudget() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) FROM financialgoal WHERE category = 'Budget' ORDER BY goal_id");

        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
