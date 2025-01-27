package edu.pharmacie.component;

import edu.pharmacie.event.EmployeeEventManager;
import edu.pharmacie.model.entity.Connection;
import edu.pharmacie.model.entity.Employee;
import edu.pharmacie.service.DataFixtures;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmployeeTableView extends TableView<Employee> {


    private final ObservableList<Employee> employees;

    public EmployeeTableView(EmployeeEventManager eventManager){
        employees = FXCollections.observableArrayList(DataFixtures.getInstance().getEmployees());
        setItems(employees);
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
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
            return connection != null ? new SimpleStringProperty(connection.isActive() ? "Oui" : "Non") : new SimpleStringProperty("Non");
        });

        TableColumn<Employee, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(col-> new EmployeeActionCell(eventManager));

        getColumns().addAll(
                idColumn,
                firstNameColumn,
                lastNameColumn,
                emailColumn,
                functionColumn,
                salaryColumn,
                hoursColumn,
                roleColumn,
                activeColumn,
                actionColumn
        );
        getStyleClass().add("employeeTable");
    }


    public ObservableList<Employee> getEmployees(){
        return employees;
    }
}
