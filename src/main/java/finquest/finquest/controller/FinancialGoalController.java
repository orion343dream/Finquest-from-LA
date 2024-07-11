package finquest.finquest.controller;

import finquest.finquest.Util.Regex;
import finquest.finquest.bo.BOFactory;
import finquest.finquest.bo.custom.FinancialGoalBO;
import finquest.finquest.dao.custom.impl.FinantialGoalDAOImpl;
import finquest.finquest.dto.FinancialGoalDTO;
import finquest.finquest.entity.FinancialGoal;
import finquest.finquest.tm.FinancialGoalTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.SQLException;

public class FinancialGoalController {

    @FXML
    private TableColumn<?, ?> colamount;

    @FXML
    private TableColumn<?, ?> colgoalname;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> colcategory;

    @FXML
    private TableView<FinancialGoalTM> FgoalTable;

    @FXML
    private TextField GnameField;

    @FXML
    private TextField targetAmountField;

    @FXML
    private DatePicker targetDatePicker;

    @FXML
    private ChoiceBox<String> targetgoalchoisebox;

    FinancialGoalBO financialGoalBO = (FinancialGoalBO) BOFactory.getBO(BOFactory.BOType.FINANCIALGOAL);

    @FXML
    public void initialize() {
        targetgoalchoisebox.setItems(FXCollections.observableArrayList(
                "Income", "Expense", "Budget"
        ));


        targetgoalchoisebox.setOnAction(event -> {
            String selectedCategory = targetgoalchoisebox.getValue();
            System.out.println("Selected category: " + selectedCategory);
        });

        loadAllData();
    }

    private void loadAllData() {
        ObservableList<FinancialGoalTM> obList = FXCollections.observableArrayList();
        try {
            obList = financialGoalBO.getAll();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        FgoalTable.setItems(obList);
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colgoalname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colcategory.setCellValueFactory(new PropertyValueFactory<>("achieved"));
    }

    @FXML
    void addFinancialGoal(ActionEvent event) {
        if (isValied()) {
            FinancialGoalDTO financialGoal = new FinancialGoalDTO(GnameField.getText(), Double.parseDouble(targetAmountField.getText()), Date.valueOf(targetDatePicker.getValue()), targetgoalchoisebox.getValue());
            try {
                if (financialGoalBO.saveFinancialGoal(financialGoal)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Save successful").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Save failed").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            loadAllData();
        }
    }

    @FXML
    void onSearchAction(ActionEvent event) {
        try {
            if (financialGoalBO.edit(GnameField.getText())){
                targetAmountField.setText(String.valueOf(FinantialGoalDAOImpl.financialGoal.getAmount()));
                targetDatePicker.setValue(FinantialGoalDAOImpl.financialGoal.getDate().toLocalDate());
                targetgoalchoisebox.setValue(FinantialGoalDAOImpl.financialGoal.getCategory());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void deleteFinancialGoal(ActionEvent event) {
        try {
            if (financialGoalBO.deleteFinancialGoal(GnameField.getText())) {
                new Alert(Alert.AlertType.CONFIRMATION, "delete successful").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "delete failed").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.CONFIRMATION, e.getMessage()).show();
        }
        loadAllData();
    }

    @FXML
    void UpdateFinancialGoal(ActionEvent event) {
        try {
            if (financialGoalBO.edit(GnameField.getText())) {
                FinancialGoal financialGoal = getValues();
                updateValues(financialGoal);
                FinancialGoalDTO financialGoalDTO = new FinancialGoalDTO(financialGoal.getName(),financialGoal.getAmount(),financialGoal.getDate(),financialGoal.getCategory());
                if (financialGoalBO.updateFinancialGoal(financialGoalDTO)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Update failed").show();
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllData();
    }

    private void updateValues(FinancialGoal financialGoal) {
        FinancialGoal existingGoal = FinantialGoalDAOImpl.financialGoal;
        if (!financialGoal.getName().isEmpty() ) {
            existingGoal.setName(financialGoal.getName());
        }
        if (financialGoal.getAmount() != 0 ) {
            existingGoal.setAmount(financialGoal.getAmount());
        }
        if (financialGoal.getDate() != null ) {
            existingGoal.setDate(financialGoal.getDate());
        }
        if (!financialGoal.getCategory().equals("")) {
            existingGoal.setCategory(financialGoal.getCategory());
        }
    }

    private FinancialGoal getValues() {
        return new FinancialGoal(GnameField.getText(), Double.parseDouble(targetAmountField.getText()), Date.valueOf(targetDatePicker.getValue()), targetgoalchoisebox.getValue());
    }
    @FXML
    void goalTableAction(MouseEvent event) {
        FinancialGoalTM selectedItem = FgoalTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            GnameField.setText(selectedItem.getName());
        }
    }

    public boolean isValied() {
        if (!Regex.setTextColor(finquest.finquest.Util.TextField.ID,GnameField)) return false;
        if (!Regex.setTextColor(finquest.finquest.Util.TextField.AMOUNT,targetAmountField)) return false;
        return true;
    }

    @FXML
    void txtAmountAction(KeyEvent event) {
        Regex.setTextColor(finquest.finquest.Util.TextField.AMOUNT,targetAmountField);
    }

    @FXML
    void txtNameAction(KeyEvent event) {
        Regex.setTextColor(finquest.finquest.Util.TextField.ID,GnameField);
    }

}

