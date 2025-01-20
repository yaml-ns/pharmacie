package edu.pharmacie.component;

import edu.pharmacie.event.EmployeeEvent;
import edu.pharmacie.event.EmployeeEventManager;
import edu.pharmacie.model.entity.Employee;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.layout.HBox;

public class EmployeeActionCell extends TableCell<Employee, Void> {

    private HBox actions;

    private EmployeeEventManager eventManager;
    public EmployeeActionCell(){
        actions = new HBox();
        Button update = new Button("M");
        Button delete = new Button("S");
        actions.getChildren().addAll(update,delete);
        update.setOnAction(event -> {
            Employee employee = getTableView().getItems().get(getIndex());
            eventManager.fireEmployeeEvent(EmployeeEvent.UPDATE, employee);
        });
        delete.setOnAction(event -> {
            Employee employee = getTableView().getItems().get(getIndex());
            eventManager.fireEmployeeEvent(EmployeeEvent.UPDATE, employee);
        });
    }

    @Override
    public void updateItem(Void item, boolean empty){
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(actions);
        }
    }
}
