package edu.pharmacie.controller;

import edu.pharmacie.model.entity.Employee;
import edu.pharmacie.model.entity.Function;
import edu.pharmacie.service.DataFixtures;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EmployeeFormController {
    private Employee employee;
    @FXML
    private TextField firstnameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<Function> functionField;
    @FXML
    private TextField salaryField;
    @FXML
    private TextField hoursPerWeekField;
    @FXML
    private CheckBox isActiveField;
    @FXML
    private Button employeeProcessButton;
    @FXML
    private Button employeeResetButton;


    public void initFields(Employee employee) {
        this.employee = employee;
        populateFields();
    }

    private void populateFields(){

        firstnameField.setText(employee.getFirstname());
        lastnameField.setText(employee.getLastname());
        emailField.setText(employee.getConnection().getEmail());
        salaryField.setText(String.valueOf(employee.getSalary()));
        isActiveField.setSelected(employee.getConnection().isActive());
        hoursPerWeekField.setText(String.valueOf(employee.getHoursPerWeek()));
        functionField.setItems(FXCollections.observableArrayList(DataFixtures.getInstance().getFunctions()));
        functionField.getSelectionModel().select(employee.getFunction());

    }
    @FXML
    protected void onReset(){

        if (employee != null){
            populateFields();

        }else{

            firstnameField.setText("");
            lastnameField.setText("");
            emailField.setText("");
            salaryField.setText("");
            isActiveField.setSelected(false);
            hoursPerWeekField.setText("");
            functionField.setItems(FXCollections.observableArrayList(DataFixtures.getInstance().getFunctions()));
            functionField.getSelectionModel().select(null);
        }
    } @FXML
    protected void onProcess(){
        if (employee != null){
            System.out.println("employee updated");
        }else{
            System.out.println("new employee created");
        }
    }
}
