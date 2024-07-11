package finquest.finquest.controller;

import finquest.finquest.Util.Regex;
import finquest.finquest.bo.BOFactory;
import finquest.finquest.bo.custom.BudgetBO;
import finquest.finquest.dao.custom.impl.BudgetDAOImpl;
import finquest.finquest.dao.custom.impl.UserDAOImpl;
import finquest.finquest.dto.BudgetDTO;
import finquest.finquest.entity.Budget;
import finquest.finquest.tm.BudgetTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;

import java.sql.Date;
import java.sql.SQLException;

public class BudgetController {

    @FXML
    private TableColumn<?, ?> amontCol;

    @FXML
    private TextField amountField;

    @FXML
    private TextField amountField1;

    @FXML
    private TableColumn<?, ?> endDateCol;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TableView<BudgetTM> budgetTable;

    @FXML
    private BorderPane mainPane;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TextField nameField;

    @FXML
    private TableColumn<?, ?> startDateCol;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Text totalBudget;

    private static String name = null;

    BudgetBO budgetBO = (BudgetBO) BOFactory.getBO(BOFactory.BOType.BUDGET);

    public void initialize(){
        setTotal();
        loadAllDate();
    }

    private void setTotal(){
        try {
            double budgetTotal = budgetBO.totalBudget();
            double incomeTotal = budgetBO.totalIncome();
            double expenseTotal= budgetBO.totalExpense();
            double total = (budgetTotal + incomeTotal)- expenseTotal;
            totalBudget.setText(String.valueOf(total));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllDate() {
        ObservableList<BudgetTM> obList = FXCollections.observableArrayList();
        try {
            obList = budgetBO.getAllBudgets();
           
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        budgetTable.setItems(obList);
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amontCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        startDateCol.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateCol.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    }

    @FXML
    void addBudget(ActionEvent event) {
        if (isValied()){
            BudgetDTO budgetDTO = new BudgetDTO(UserDAOImpl.user.getId(),nameField.getText(),Double.parseDouble(amountField.getText()), Date.valueOf(startDatePicker.getValue()),Date.valueOf(endDatePicker.getValue()));
            try {
                if (budgetBO.saveBudget(budgetDTO)){
                    new Alert(Alert.AlertType.CONFIRMATION,"Save successful").show();
                } else {
                    new Alert(Alert.AlertType.ERROR,"Save failed").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            initialize();
            setTotal();
        }
    }

    @FXML
    void DeleteBudget(ActionEvent event) {
        try {
            if (name != null){
                if (budgetBO.deleteBudget(name)){
                    new Alert(Alert.AlertType.CONFIRMATION,"delete successful").show();
                } else {
                    new Alert(Alert.AlertType.ERROR,"delete failed").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
        }
        initialize();
        setTotal();
    }

    @FXML
    void nameAction(ActionEvent event) {
        try {
            if (budgetBO.edit(nameField.getText())){
                Budget budget = BudgetDAOImpl.budget;
                amountField.setText(String.valueOf(budget.getAmount()));
                startDatePicker.setValue(budget.getStartDate().toLocalDate());
                endDatePicker.setValue(budget.getEndDate().toLocalDate());
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
        }
    }

    @FXML
    void UpdateBduget(ActionEvent event) {
        try {
            Budget budget = getValues();
                updateValues(budget);
                if (budgetBO.updateBudget()){
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated!").show();
                }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        initialize();
        setTotal();
    }

    private void updateValues(Budget budget) {
        if (!budget.getName().equals("")){
            BudgetDAOImpl.budget.setName(budget.getName());
        }
        if (budget.getAmount() != 0){
            BudgetDAOImpl.budget.setAmount(budget.getAmount());
        }
        if (!budget.getStartDate().equals("")) {
            BudgetDAOImpl.budget.setStartDate(budget.getStartDate());
        }
        if (!budget.getEndDate().equals("")) {
            BudgetDAOImpl.budget.setEndDate(budget.getEndDate());
        }
    }

    private Budget getValues() {
        return new Budget(UserDAOImpl.user.getId(),nameField.getText(),Double.parseDouble(amountField.getText()),Date.valueOf(startDatePicker.getValue()),Date.valueOf(endDatePicker.getValue()));
    }


    @FXML
    void budgetTableAction(MouseEvent event) {
        BudgetTM selectedItem = budgetTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            name = selectedItem.getName();
        }
    }

    public boolean isValied() {
        if (!Regex.setTextColor(finquest.finquest.Util.TextField.AMOUNT,amountField)) return false;
        if (!Regex.setTextColor(finquest.finquest.Util.TextField.ID,nameField)) return false;
        return true;
    }

    @FXML
    void txtAmountAction(KeyEvent event) {
        Regex.setTextColor(finquest.finquest.Util.TextField.AMOUNT,amountField);
    }

    @FXML
    void txtNameAction(KeyEvent event) {
        Regex.setTextColor(finquest.finquest.Util.TextField.ID,nameField);
    }

}
