package edu.pharmacie.controller;

import edu.pharmacie.model.entity.Connection;
import edu.pharmacie.model.entity.Employee;
import edu.pharmacie.service.DataFixtures;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class EmployeeController {

    @FXML
    private VBox mainContainer;
    public void initialize(){
        mainContainer.getChildren().add(getEmployeesTable());
    }
    private TableView<Employee> getEmployeesTable(){
        ObservableList<Employee> employees = FXCollections.observableArrayList(DataFixtures.getInstance().getEmployees());

        TableView<Employee> tableView = new TableView<>(employees);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        TableColumn<Employee, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());
        TableColumn<Employee, String> firstNameColumn = new TableColumn<>("Prénom");
        firstNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstname()));

        TableColumn<Employee, String> lastNameColumn = new TableColumn<>("Nom");
        lastNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastname()));

        TableColumn<Employee, String> functionColumn = new TableColumn<>("Fonction");
        functionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFunction().toString()));

        TableColumn<Employee, Double> salaryColumn = new TableColumn<>("Salaire");
        salaryColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getSalary()).asObject());

        TableColumn<Employee, Integer> hoursColumn = new TableColumn<>("Heures/Semaine");
        hoursColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getHoursPerWeek()).asObject());

        TableColumn<Employee, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> {
            Connection connection = cellData.getValue().getConnection();
            return connection != null ? new SimpleStringProperty(connection.getEmail()) : new SimpleStringProperty("");
        });

        TableColumn<Employee, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(cellData -> {
            Connection connection = cellData.getValue().getConnection();
            return connection != null ? new SimpleStringProperty(connection.getRole().toString()) : new SimpleStringProperty("");
        });

        TableColumn<Employee, String> activeColumn = new TableColumn<>("Actif");
        activeColumn.setCellValueFactory(cellData -> {
            Connection connection = cellData.getValue().getConnection();
            return connection != null ? new SimpleStringProperty(connection.isActive() ? "Oui" : "Non") : new SimpleStringProperty("No");
        });

        tableView.getColumns().addAll(idColumn,firstNameColumn, lastNameColumn,emailColumn, functionColumn, salaryColumn, hoursColumn,roleColumn,activeColumn);
        tableView.getStyleClass().add("employeeTable");
        return tableView;
    }

}
