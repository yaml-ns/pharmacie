package edu.pharmacie.component;

import edu.pharmacie.Main;
import edu.pharmacie.event.EmployeeEvent;
import edu.pharmacie.event.EmployeeEventManager;
import edu.pharmacie.model.entity.Employee;
import javafx.scene.control.*;
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

        MenuItem updateItem = new MenuItem("Modifier");

        Image updateImage =new Image(Main.class.getResourceAsStream("/icons/menu-edit.png"));
        ImageView updateIcon = new ImageView(updateImage);
        updateIcon.setFitWidth(15);
        updateIcon.setFitHeight(15);
        updateItem.setGraphic(updateIcon);

        MenuItem showItem = new MenuItem("Détails");

        Image showImage =new Image(Main.class.getResourceAsStream("/icons/menu-show.png"));
        ImageView showIcon = new ImageView(showImage);
        showIcon.setFitWidth(15);
        showIcon.setFitHeight(15);
        showItem.setGraphic(showIcon);

        MenuItem deleteItem = new MenuItem("Supprimer");

        Image deleteImage =new Image(Main.class.getResourceAsStream("/icons/menu-red-delete.png"));
        ImageView deleteIcon = new ImageView(deleteImage);
        deleteIcon.setFitWidth(15);
        deleteIcon.setFitHeight(15);
        deleteItem.setGraphic(deleteIcon);
        deleteItem.getStyleClass().add("menu-delete");

        SeparatorMenuItem separator = new SeparatorMenuItem();
        contextMenu.getItems().addAll(showItem,updateItem,separator,deleteItem);


        actionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            double screenX = actionButton.localToScreen(actionButton.getBoundsInLocal()).getMinX();
            double screenY = actionButton.localToScreen(actionButton.getBoundsInLocal()).getMaxY();

            contextMenu.show(actionButton, screenX, screenY);

            double adjustedX = screenX - contextMenu.getWidth() + (actionButton.getWidth()/2);
            contextMenu.setX(adjustedX);
            contextMenu.setY(screenY);
        });

        showItem.setOnAction(event -> {
            Employee employee = getTableView().getItems().get(getIndex());
            eventManager.fireEmployeeEvent(EmployeeEvent.SHOW,employee);
        });
        updateItem.setOnAction(event -> {
            Employee employee = getTableView().getItems().get(getIndex());
            eventManager.fireEmployeeEvent(EmployeeEvent.UPDATE,employee);
        });

        deleteItem.setOnAction(event -> {
            Employee employee = getTableView().getItems().get(getIndex());
            eventManager.fireEmployeeEvent(EmployeeEvent.DELETE, employee);
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
