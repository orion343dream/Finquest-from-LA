package finquest.finquest.controller;

import finquest.finquest.bo.BOFactory;
import finquest.finquest.bo.custom.DashboardBO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class DashboardController {

    @FXML
    private Label Budget;

    @FXML
    private Label Dashboard;

    @FXML
    private Label Expense;

    @FXML
    private Label FinantialAccount;

    @FXML
    private Label FinantialGoal;

    @FXML
    private Label Income;

    @FXML
    private Label Reminder;

    @FXML
    private Label Reports;

    @FXML
    private MenuButton addNewTra;

    @FXML
    private MenuItem addExpenceAction;

    @FXML
    private MenuItem addIncomeAction;

    @FXML
    private AnchorPane changeAnchorPane;

    @FXML
    private Label labelDashboard;

    @FXML
    private AnchorPane totalAnchorpane;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Label lbnTotalIncome;

    @FXML
    private Label lbnTotalExpense;

    @FXML
    private Label lbnTotalBuget;


    @FXML
    private Label frombudgetcountdash;

    @FXML
    private Label fromexpensecountdash;

    @FXML
    private Label fromincomecountdash;

    @FXML
    private Label lbnTotalremindercount;

    @FXML
    private Label fromgoalcountdash;

    @FXML
    private Label lbnTotalgoalpresentage;

    DashboardBO dashboardBO = (DashboardBO) BOFactory.getBO(BOFactory.BOType.DASHBOARD);

    public void initialize(){
        try {
            lbnTotalIncome.setText(String.valueOf(dashboardBO.getIncomeTotal())+ " LKR");
            lbnTotalExpense.setText(String.valueOf(dashboardBO.totalExpense())+ " LKR");
            lbnTotalBuget.setText(String.valueOf(dashboardBO.totalBudget())+ " LKR");
            int budgetCount = dashboardBO.getBudgetCount();
            frombudgetcountdash.setText("From " + budgetCount + " budgets");
            int expenseCount = dashboardBO.getExpenseCount();
            fromexpensecountdash.setText("From " + expenseCount + " expenses");
            int incomeCount = dashboardBO.getIncomeCount();
            fromincomecountdash.setText("From " + incomeCount + " incomes");
            int reminderCount = dashboardBO.getReminderCount();
            lbnTotalremindercount.setText(String.valueOf(reminderCount));
            int goalCount = dashboardBO.getFinancialCount();
            fromgoalcountdash.setText("From " + goalCount + " financial goals");
            int incomegoal = dashboardBO.getIncome();
            int budgetGoal = dashboardBO.getBudget();
            int expenseGoal = dashboardBO.getExpense();
            lbnTotalgoalpresentage.setText(incomegoal + " Income Goals\n" + budgetGoal + " Budget Goals\n" + expenseGoal + " Expense Goals");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void dashboardAction(MouseEvent event) {
        Scene scene = null;
        try {
            scene = new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void addNewTransaction(ActionEvent event) {

    }



    @FXML
    void budgetAction(MouseEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/budget.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void expenceAction(MouseEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/expense.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void fiAccAction(MouseEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/financial_account.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void goalAction(MouseEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/finantial_goal.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void incomeAction(MouseEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/income.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML
    void addNewBudget(ActionEvent event) {
            labelDashboard.setVisible(false);
            try {
                changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/budget.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    @FXML
    void addIncomeAction (ActionEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/expense.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addExpenceAction (ActionEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/expense.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void reminderAction(MouseEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/reminder.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void reportsAction(MouseEvent event) {
        labelDashboard.setVisible(false);
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/reports.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void navigateToLogin(ActionEvent event) throws IOException {
        BorderPane  rootNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/login.fxml")));


        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.mainPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login ");

        stage.show();
    }
    @FXML
    void reportAction(MouseEvent event) {
        try {
            changeAnchorPane.getChildren().setAll((Node) FXMLLoader.load(this.getClass().getResource("/view/reports.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
