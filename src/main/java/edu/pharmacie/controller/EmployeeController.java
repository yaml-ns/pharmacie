package edu.pharmacie.controller;

import edu.pharmacie.Main;
import edu.pharmacie.component.EmployeeTableView;
import edu.pharmacie.event.EmployeeEvent;
import edu.pharmacie.event.EmployeeEventManager;
import edu.pharmacie.model.entity.Employee;
import edu.pharmacie.service.DataFixtures;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.w3c.dom.events.MouseEvent;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class EmployeeController {

    @FXML
    private BorderPane mainContainer;

    @FXML
    private VBox tableContainer;

    @FXML
    private VBox tableHeader;

    private EmployeeTableView tableView;
    private final EmployeeEventManager eventManager = new EmployeeEventManager();
    private ObservableList<Employee> employeeList;

    public void initialize(){
        tableView = new EmployeeTableView(eventManager);
        employeeList = tableView.getEmployees();
        tableContainer.getChildren().add(tableView);
        VBox.setVgrow(tableView, javafx.scene.layout.Priority.ALWAYS);

        HBox headerItems = new HBox();
        Button addEmployeeButton = new Button("Ajouter");
        addEmployeeButton.setOnAction(event -> handleAddButtonAction());
        headerItems.getChildren().add(addEmployeeButton);
        tableHeader.getChildren().add(headerItems);

        eventManager.addShowListener(this::handleShow);
        eventManager.addCreateListener(this::handleCreate);
        eventManager.addUpdateListener(this::handleUpdate);
        eventManager.addOpenDialogListener(this::handleOpenDialog);
        eventManager.addDeleteListener(this::handleDelete);

    }



    private boolean showConfirmationDialog() {
        AtomicBoolean confirm = new AtomicBoolean(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText("Confirmer la suppression");
        alert.setGraphic(null);
        alert.setContentText("Êtes-vous vraiment sûr de vouloir continuer ?");

        ButtonType yesButton = new ButtonType("Oui, je veux supprimer");
        ButtonType noButton = new ButtonType("Non");
        alert.getButtonTypes().setAll(yesButton, noButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                confirm.set(true);
            }
        });
        return confirm.getPlain();
    }

    private void handleDelete(EmployeeEvent employeeEvent) {
        if (showConfirmationDialog()){
            for (Employee employee : employeeList){
                if (employee.getId().equals(employeeEvent.getEmployee().getId())){
                    DataFixtures.getInstance().removeEmployee(employeeEvent.getEmployee().getId());
                    employeeList.remove(employee);
                    return;
                }
            }
        }

    }

    private void handleOpenDialog(EmployeeEvent employeeEvent){
        try {
            Stage employeeFormModal = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pharmacie/views/parts/employee-form-modal.fxml"));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(edu.pharmacie.Main.class.getResource("style.css").toExternalForm());
            EmployeeFormController controller = loader.getController();
            controller.setEventManager(eventManager);
            Employee employee = employeeEvent.getEmployee();
            controller.initFields(employeeEvent.getEmployee());
            if (employee != null){
            employeeFormModal.setTitle("Modifier de l'employé <"+employeeEvent.getEmployee().getFirstname() + " "+employeeEvent.getEmployee().getLastname()+">");
            }else{
            employeeFormModal.setTitle("Ajouter un nouvel employé ");
            }
            employeeFormModal.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("logo.jpeg"))));
            employeeFormModal.setScene(scene);
            employeeFormModal.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void handleUpdate(EmployeeEvent employeeEvent){
        tableView.setItems(FXCollections.observableArrayList(DataFixtures.getInstance().getEmployees()));
        tableView.refresh();
    }

    private void handleCreate(EmployeeEvent employeeEvent) {
        tableView.setItems(FXCollections.observableArrayList(DataFixtures.getInstance().getEmployees()));
        tableView.refresh();
        tableView.scrollTo(employeeList.size() - 1);
        tableView.setRowFactory(tv->new TableRow<Employee>(){
            @Override
            protected void updateItem(Employee employee, boolean b) {
                super.updateItem(employee, b);
                if (employee == null || b) {
                    setStyle("");
                } else {

                    if (getIndex() == tv.getItems().size() - 1) {
                        setStyle("-fx-background-color: rgb(6,215,177);");
                        applyFadeOutAnimation(this);
                    } else {
                        setStyle("");
                    }
                }
            }
        });


    }

    private void applyFadeOutAnimation(TableRow<Employee> row) {

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), row);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), row);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> fadeOut.play()),
                new KeyFrame(Duration.seconds(2), e -> {
                    row.setStyle("");
                    fadeIn.play();
                })
        );
        timeline.play();
    }

    private void handleShow(EmployeeEvent employeeEvent) {
        System.out.println("SHOW " + employeeEvent.getEmployee());
    }

    public EmployeeEventManager getEventManager(){
        return eventManager;
    }

    private void handleAddButtonAction() {
        this.handleOpenDialog(new EmployeeEvent(EmployeeEvent.OPEN_DIALOG, null));
    }

}


