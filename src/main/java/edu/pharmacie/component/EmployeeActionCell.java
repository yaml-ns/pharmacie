package edu.pharmacie.component;

import edu.pharmacie.Main;
import edu.pharmacie.event.EmployeeEvent;
import edu.pharmacie.event.EmployeeEventManager;
import edu.pharmacie.model.entity.Employee;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class EmployeeActionCell extends TableCell<Employee, Void> {

    private HBox actions;

    private EmployeeEventManager eventManager;
    public EmployeeActionCell(EmployeeEventManager eventManager){
        this.eventManager = eventManager;
        actions = new HBox();
        Image image = new Image(Main.class.getResourceAsStream("/icons/more.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        Button actionButton = new Button("",imageView);
        actionButton.getStyleClass().add("context-button");

        ContextMenu contextMenu = new ContextMenu();
        MenuItem updateItem = new MenuItem("Update");
        MenuItem deleteItem = new MenuItem("Delete");
        contextMenu.getItems().addAll(updateItem, deleteItem);

        actionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            contextMenu.show(actionButton, event.getScreenX(), event.getScreenY());
        });

        updateItem.setOnAction(event -> {
            Employee employee = getTableView().getItems().get(getIndex());
            System.out.println("UPDATE : " + employee);
//            EventBus.getInstance().post(new EmployeeEvent(EmployeeEvent.UPDATE, employee));
        });

        deleteItem.setOnAction(event -> {
            Employee employee = getTableView().getItems().get(getIndex());
//            EventBus.getInstance().post(new EmployeeEvent(EmployeeEvent.DELETE, employee));
            System.out.println("UPDATE : " + employee);
        });

        actions.getChildren().add(actionButton);
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
