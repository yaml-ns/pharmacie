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
        EmployeeTableView tableView = new EmployeeTableView(eventManager);
        mainContainer.getChildren().add(tableView.getTableView());
        eventManager.addCreateListener(this::handleCreate);
        eventManager.addUpdateListener(this::handleUpdate);
        eventManager.addDeleteListener(this::handleDelete);

    }

    private void handleDelete(EmployeeEvent employeeEvent) {
        System.out.println("DELETE " + employeeEvent.getEmployee());
    }

    private void handleUpdate(EmployeeEvent employeeEvent) {
        System.out.println("UPDATE " + employeeEvent.getEmployee());
    }

    private void handleCreate(EmployeeEvent employeeEvent) {
        System.out.println("CREATE " + employeeEvent.getEmployee());
    }

    public EmployeeEventManager getEventManager(){
        return eventManager;
    }

}
