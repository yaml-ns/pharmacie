package edu.pharmacie.controller;

import edu.pharmacie.model.entity.Medicament;
import edu.pharmacie.model.entity.Type;
import edu.pharmacie.service.DataFixtures;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DrugController {
    @FXML
    private BorderPane mainContainer;

    @FXML
    private VBox tableContainer;

    private TableView<Medicament> tableView;
    private ObservableList<Medicament> medicamentList;

    public void initialize() {
        ObservableList<Medicament> medicaments = FXCollections
                .observableArrayList(DataFixtures.getInstance().getMedicaments());

        TableColumn<Medicament, Long> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> new SimpleLongProperty(cellData.getValue().getId()).asObject());

        TableColumn<Medicament, String> nameColumn = new TableColumn<>("Nom");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));

        TableColumn<Medicament, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));

        TableColumn<Medicament, Double> priceColumn = new TableColumn<>("Prix");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getPrix()).asObject());

        TableColumn<Medicament, Void> actionColumn = new TableColumn<>("Action");
        actionColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Supprimer");
            private final Button updateButton = new Button("Modifier");
            private final HBox hBox = new HBox(deleteButton, updateButton);

            {
                hBox.setSpacing(5);
                deleteButton.setStyle("-fx-background-color:rgb(245, 20, 4); -fx-text-fill: white");
                updateButton.setStyle("-fx-background-color:rgb(89, 114, 196); -fx-text-fill: white");
                deleteButton.setOnAction(event -> {

                    Medicament medicament = getTableView().getItems().get(getIndex());
                    showDeleteConfirmationDialog(medicament);
                });
                updateButton.setOnAction(event -> {
                    Medicament medicament = getTableView().getItems().get(getIndex());
                    showEditPopup(medicament);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hBox);
                }
            }
        });

        tableView = new TableView<>(medicaments);
        tableView.getColumns().addAll(idColumn, nameColumn, typeColumn, priceColumn, actionColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        medicamentList = tableView.getItems();
        tableContainer.getChildren().add(tableView);
        VBox.setVgrow(tableView, javafx.scene.layout.Priority.ALWAYS);

    }

    private void showDeleteConfirmationDialog(Medicament medicament) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("voulez-vous vraiment supprimer ce produit ?");
        alert.setContentText(medicament.getNom());

        ButtonType okButton = new ButtonType("Oui, je veux supprimer",
                javafx.scene.control.ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Non", javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton, cancelButton);

        alert.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                DataFixtures.getInstance().removeMedicament(medicament.getId());
                tableView.getItems().remove(medicament);
                tableView.refresh();
            }
        });
    }

    private void showEditPopup(Medicament medicament) {
        Stage popupstage = new Stage();
        popupstage.setTitle("Modifier un produit");

        VBox popupVBox = new VBox();
        popupVBox.setSpacing(10);
        popupVBox.setPadding(new javafx.geometry.Insets(10));

        Label idLabel = new Label("ID : " + medicament.getId());
        popupVBox.getChildren().add(idLabel);

        Label nameLabel = new Label("Nom :");
        TextField nameField = new TextField(medicament.getNom());
        popupVBox.getChildren().addAll(nameLabel, nameField);

        Label typeLabel = new Label("Type :");
        TextField typeField = new TextField(medicament.getType().toString());
        popupVBox.getChildren().addAll(typeLabel, typeField);

        Label priceLabel = new Label("Prix :");
        TextField priceField = new TextField(String.valueOf(medicament.getPrix()));
        popupVBox.getChildren().addAll(priceLabel, priceField);

        Button saveButton = new Button("Enregistrer");
        saveButton.setOnAction(event -> {
            medicament.setNom(nameField.getText());
            medicament.setType(Type.valueOf(typeField.getText()));
            medicament.setPrix(Double.parseDouble(priceField.getText()));
            DataFixtures.getInstance().updateMedicament(medicament);
            tableView.refresh();
            popupstage.close();
        });
        popupVBox.getChildren().add(saveButton);

        popupstage.setScene(new Scene(popupVBox));
        popupstage.showAndWait();
    }
}
