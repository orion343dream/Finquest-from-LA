package finquest.finquest.dao.custom.impl;

import finquest.finquest.dao.DAOFactory;
import finquest.finquest.dao.SQLUtil;
import finquest.finquest.dao.custom.ExpenseDAO;
import finquest.finquest.dao.custom.FinantialGoalDAO;
import finquest.finquest.dao.custom.ReminderDAO;
import finquest.finquest.db.DbConnection;
import finquest.finquest.entity.Expense;
import finquest.finquest.tm.ExpenseTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseDAOImpl implements ExpenseDAO {

    public static Expense expense;

    @Override
    public double totalExpense() throws SQLException, ClassNotFoundException {
        double total = 0;

        ResultSet rs = SQLUtil.execute("SELECT amount FROM expense");

        while (rs.next()) {
            total += rs.getDouble("amount");
        }
        return total;
    }

    @Override
    public ObservableList<ExpenseTM> getData() throws SQLException, ClassNotFoundException {
        ObservableList<ExpenseTM> obList = FXCollections.observableArrayList();

        ResultSet rs = SQLUtil.execute("SELECT * FROM expense");
        while (rs.next()) {
            obList.add(new ExpenseTM(rs.getString("expense_id"),rs.getDouble("amount"),rs.getString("description"),rs.getDate("date"),rs.getString("category")));
        }

        return obList;
    }

    @Override
    public boolean save(Expense expense) throws SQLException, ClassNotFoundException {
        ReminderDAO reminderDAO = (ReminderDAO) DAOFactory.getDAO(DAOFactory.DAOType.REMINDER);
        FinantialGoalDAO finantialGoalDAO = (FinantialGoalDAO) DAOFactory.getDAO(DAOFactory.DAOType.FINANCIALGOAL);

        Connection conn = DbConnection.getInstance().getConnection();
        conn.setAutoCommit(false);

        try {
            if (saveExpense(expense)){
                if (reminderDAO.save(expense,finantialGoalDAO.getExpenseGoal())){
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
    public boolean saveExpense(Expense expense) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO expense (expense_id, user_id, amount, description, date,category) VALUES (?,?,?,?,?,?)", expense.getId(),expense.getUserId(), expense.getAmount(), expense.getDescription(), expense.getDate(), expense.getCategory());
    }

    @Override
    public boolean delete(String text) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM expense WHERE expense_id = ?",text);
    }

    @Override
    public boolean edit(String text) throws SQLException, ClassNotFoundException {

        ResultSet rs = SQLUtil.execute("SELECT * FROM expense WHERE expense_id = ?",text);

        if (rs.next()){
            expense = new Expense(rs.getString("expense_id"),rs.getString("user_id"),rs.getDouble("amount"),rs.getString("description"),rs.getDate("date"),rs.getString("category"));
            return true;
        } else {
            new Alert(Alert.AlertType.CONFIRMATION,"can't find name").show();
        }
        return false;
    }

    @Override
    public boolean update() throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE expense SET amount = ? , description = ? , date = ? , category = ? WHERE expense_id = ?", expense.getAmount(), expense.getDescription(), expense.getDate(), expense.getCategory(), expense.getId());

    }

    @Override
    public double getTotal() throws SQLException, ClassNotFoundException {
        double total = 0;

        ResultSet set = SQLUtil.execute("select amount from expense");

        while (set.next()) {
            total += set.getDouble("amount");
        }
        return total;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        int count = 0;

        ResultSet rs = SQLUtil.execute("select count(*) from expense order by expense_id");

        if (rs.next()) {
            count = rs.getInt(1);
        }

        return count;
    }
}
