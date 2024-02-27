package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import Models.Vehicule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.VehiculeServices;

public class G_Véhicules {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField TypeV;

    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TableColumn<?, ?> typeCol;
    @FXML
    private TableColumn<Vehicule, Void> columnActions;

    @FXML
    private TableView<Vehicule> vtab;

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void Retourbu(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Homepage.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void okaddVehicule(ActionEvent event) {
        String typeVehicule = TypeV.getText().trim();
        List<String> typesAcceptes = Arrays.asList("Fourgon", "Voiture", "Scooter", "Vélo", "Moto");

        if (typeVehicule.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ vide");
            alert.setContentText("Veuillez saisir un type de véhicule.");
            alert.showAndWait();
            return;
        } else if (!typesAcceptes.contains(typeVehicule)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Type de véhicule non valide");
            alert.setContentText("Le type de véhicule doit être parmi : Fourgons, Voiture, Scooter, Vélo, Moto.");
            alert.showAndWait();
            return;
        }
        Vehicule V = new Vehicule(TypeV.getText());
        VehiculeServices VS = new VehiculeServices();

        try {
            VS.ajouter(V);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Véhicule ajouté avec succès");
            alert.show();
            vtab.getItems().add(V);
            vtab.refresh();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        VehiculeServices VS = new VehiculeServices();

        try {
            List<Vehicule> vehicule = VS.afficher();
            ObservableList<Vehicule> data = FXCollections.observableArrayList(vehicule);
            vtab.setItems(data);
            vtab.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addModifyAndDeleteButtonsToTable();
    }

    private void addModifyAndDeleteButtonsToTable() {
        columnActions.setCellFactory(param -> new TableCell<>() {
            private final Button btnModify = new Button("Modifier");
            private final Button btnDelete = new Button("Supprimer");
            private final HBox pane = new HBox(btnModify, btnDelete);

            {
                btnModify.setOnAction(event -> {
                    Vehicule vehicule = getTableView().getItems().get(getIndex());
                    handleModifyAction(vehicule);
                });
                btnDelete.setOnAction(event -> {
                    Vehicule vehicule = getTableView().getItems().get(getIndex());
                    handleDeleteAction(vehicule);
                });
                pane.setSpacing(10);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : pane);
            }
        });
    }

    VehiculeServices VS = new VehiculeServices();

    private void handleDeleteAction(Vehicule vehicule) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + vehicule.getType() + "?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            try {
                VS.supprimer(vehicule.getId());
                vtab.refresh();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void handleModifyAction(Vehicule vehicule) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/MODV.fxml"));
            Parent root = loader.load();
            MODV controller = loader.getController();
            controller.setType(vehicule.getType());
            controller.setUpdateAction(event -> {
                String newType = controller.getType();

                if (newType != null && !newType.isEmpty()) {
                    vehicule.setType(newType);
                    VehiculeServices VS = new VehiculeServices();

                    try {
                        VS.modifier(vehicule);
                        vtab.refresh();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}










