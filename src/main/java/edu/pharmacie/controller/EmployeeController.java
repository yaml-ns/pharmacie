package edu.pharmacie.controller;

import edu.pharmacie.component.EmployeeTableView;
import edu.pharmacie.event.EmployeeEvent;
import edu.pharmacie.event.EmployeeEventManager;
import edu.pharmacie.model.entity.Employee;
import edu.pharmacie.service.DataFixtures;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.concurrent.atomic.AtomicBoolean;

public class EmployeeController {

    @FXML
    private VBox mainContainer;

    private final EmployeeEventManager eventManager = new EmployeeEventManager();
    private ObservableList<Employee> employeeList;

    public void initialize(){
        EmployeeTableView tableView = new EmployeeTableView(eventManager);
        employeeList = tableView.getEmployees();
        mainContainer.getChildren().add(tableView.getTableView());
        eventManager.addShowListener(this::handleShow);
        eventManager.addCreateListener(this::handleCreate);
        eventManager.addUpdateListener(this::handleUpdate);
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

    private void handleUpdate(EmployeeEvent employeeEvent){
        try {
            Stage employeeFormModal = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/pharmacie/views/parts/employee-form-modal.fxml"));
            Scene scene = new Scene(loader.load());
            EmployeeFormController controller = loader.getController();
            controller.initFields(employeeEvent.getEmployee());
            employeeFormModal.setTitle("Modifier de l'employé <"+employeeEvent.getEmployee().getFirstname() + " "+employeeEvent.getEmployee().getLastname()+">");
            employeeFormModal.setScene(scene);
            employeeFormModal.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("UPDATE " + employeeEvent.getEmployee());
    }



    private void handleCreate(EmployeeEvent employeeEvent) {
        System.out.println("CREATE " + employeeEvent.getEmployee());
    }
 private void handleShow(EmployeeEvent employeeEvent) {
        System.out.println("SHOW " + employeeEvent.getEmployee());
    }

    public EmployeeEventManager getEventManager(){
        return eventManager;
    }

}
