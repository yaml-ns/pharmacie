package edu.pharmacie.controller;

import edu.pharmacie.component.ErrorLabel;
import edu.pharmacie.event.EmployeeEvent;
import edu.pharmacie.event.EmployeeEventManager;
import edu.pharmacie.model.entity.*;
import edu.pharmacie.service.DataFixtures;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;

public class EmployeeFormController {
    private Employee employee;
    private EmployeeEventManager eventManager;
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
    @FXML
    private VBox formErrors;


    public void initFields(Employee employee) {
        this.employee = employee;
        populateFields();
    }

    public void setEventManager(EmployeeEventManager eventManager){
        this.eventManager = eventManager;
    }
    private void populateFields(){

        if (employee != null){
            firstnameField.setText(employee.getFirstname());
            lastnameField.setText(employee.getLastname());
            emailField.setText(employee.getConnection().getEmail());
            salaryField.setText(String.valueOf(employee.getSalary()));
            isActiveField.setSelected(employee.getConnection().isActive());
            hoursPerWeekField.setText(String.valueOf(employee.getHoursPerWeek()));
            functionField.setItems(FXCollections.observableArrayList(DataFixtures.getInstance().getFunctions()));
            functionField.getSelectionModel().select(employee.getFunction());
        }

        functionField.setItems(FXCollections.observableArrayList(DataFixtures.getInstance().getFunctions()));
    }

    protected boolean validateForm(){

        resetFieldStyles();
        int errors = 0;
        if (firstnameField.getText().isBlank()){
            firstnameField.getStyleClass().add("form-error");
            HBox error = new ErrorLabel("Le prénom est obligatoire");
            formErrors.getChildren().add(error);
            errors++;
        }
        if (lastnameField.getText().isBlank()){
            lastnameField.getStyleClass().add("form-error");
            HBox error = new ErrorLabel("Le nom est obligatoire");
            formErrors.getChildren().add(error);
            errors++;
        }
        if (emailField.getText().isBlank()){
            emailField.getStyleClass().add("form-error");
            HBox error = new ErrorLabel("L'email est obligatoire");
            formErrors.getChildren().add(error);
            errors++;
        }
        if (salaryField.getText().isBlank()){
            salaryField.getStyleClass().add("form-error");
            HBox error = new ErrorLabel("Le salaire est obligatoire");
            formErrors.getChildren().add(error);
            errors++;
        }
        if (hoursPerWeekField.getText().isBlank()){
            hoursPerWeekField.getStyleClass().add("form-error");
            HBox error = new ErrorLabel("Les heures / semaine est obligatoire");
            formErrors.getChildren().add(error);
            errors++;
        }
        if (functionField.getSelectionModel().getSelectedItem() == null){
            functionField.getStyleClass().add("form-error");
            HBox error = new ErrorLabel("La fonction est obligatoire");
            formErrors.getChildren().add(error);

            errors++;
        }
        return errors == 0;
    }
    private void resetFieldStyles() {
        firstnameField.getStyleClass().remove("form-error");
        lastnameField.getStyleClass().remove("form-error");
        emailField.getStyleClass().remove("form-error");
        functionField.getStyleClass().remove("form-error");
        salaryField.getStyleClass().remove("form-error");
        hoursPerWeekField.getStyleClass().remove("form-error");
    }

    private void cleanErrors(){
        formErrors.getChildren().clear();
    }

    @FXML
    protected void onReset(){
        resetFieldStyles();
        cleanErrors();
        if (employee != null){
            populateFields();

        }else{

            firstnameField.clear();
            lastnameField.clear();
            emailField.clear();
            salaryField.clear();
            isActiveField.setSelected(false);
            hoursPerWeekField.clear();
            functionField.setItems(FXCollections.observableArrayList(DataFixtures.getInstance().getFunctions()));
            functionField.getSelectionModel().select(null);
        }
    }
    @FXML
    protected void onProcess(){
        cleanErrors();

        if (validateForm()){

            if (employee != null){
                Connection connection = employee.getConnection();
                Connection updatedConnection = new Connection(
                        connection.getId(),
                        emailField.getText(),
                        connection.getPassword(),
                        connection.getRole(),
                        isActiveField.isSelected()
                );
                Employee updatedEmployee = new Employee(
                        employee.getId(),
                        firstnameField.getText(),
                        lastnameField.getText(),
                        employee.getAddress(),
                        employee.getPhoneNumber(),
                        employee.getDateOfBirth(),
                        employee.getDateOfHiring(),
                        Double.parseDouble(salaryField.getText()),
                        functionField.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(hoursPerWeekField.getText()
                        )
                );
                updatedEmployee.setConnection(updatedConnection);
                DataFixtures.getInstance().updateEmployee(updatedEmployee);

                this.eventManager.fireEmployeeEvent(EmployeeEvent.UPDATE,employee);
                ((Stage) firstnameField.getScene().getWindow()).close();
            }else{
                User user = new User();
                user.setAddress("Nouvelle adresse");
                user.setFirstname(firstnameField.getText());
                user.setLastname(lastnameField.getText());
                user.setPhoneNumber("438 529-5544");
                user.setDateOfBirth(new Date(2000, Calendar.JANUARY,27));
                Connection connection = new Connection();
                connection.setActive(isActiveField.isSelected());
                connection.setEmail(emailField.getText());
                connection.setPassword("motdepass");
                connection.setRole(Role.ADMIN);
                connection.setUser(user);
                Employee newEmployee = new Employee(
                        null,
                        user.getFirstname(),
                        user.getLastname(),
                        user.getAddress(),
                        user.getPhoneNumber(),
                        user.getDateOfBirth(),
                        new Date(),
                        Double.parseDouble(salaryField.getText()),
                        functionField.getSelectionModel().getSelectedItem(),
                        Integer.parseInt(hoursPerWeekField.getText()
                        )
                );
                newEmployee.setConnection(connection);

                DataFixtures.getInstance().addEmployee(newEmployee);
                this.eventManager.fireEmployeeEvent(EmployeeEvent.CREATE,newEmployee);
                ((Stage) firstnameField.getScene().getWindow()).close();
            }

        }
    }


}
