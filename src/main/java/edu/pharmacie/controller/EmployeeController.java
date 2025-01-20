package edu.pharmacie.controller;

import edu.pharmacie.component.EmployeeTableView;
import edu.pharmacie.event.EmployeeEvent;
import edu.pharmacie.event.EmployeeEventManager;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class EmployeeController {

    @FXML
    private VBox mainContainer;

    private EmployeeEventManager eventManager = new EmployeeEventManager();

    public void initialize(){
        EmployeeTableView tableView = new EmployeeTableView();
        mainContainer.getChildren().add(tableView.getTableView());
        eventManager.addCreateListener(this::handleCreate);
        eventManager.addUpdateListener(this::handleUpdate);
        eventManager.addDeleteListener(this::handleDelete);

    }

    private void handleDelete(EmployeeEvent employeeEvent) {
        System.out.println("DELETE " + employeeEvent);
    }

    private void handleUpdate(EmployeeEvent employeeEvent) {
        System.out.println("UPDATE " + employeeEvent);
    }

    private void handleCreate(EmployeeEvent employeeEvent) {
        System.out.println("CREATE " + employeeEvent);
    }

    public EmployeeEventManager getEventManager(){
        return eventManager;
    }

}
