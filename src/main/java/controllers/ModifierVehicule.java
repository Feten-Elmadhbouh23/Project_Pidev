package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import Models.Vehicule;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.VehiculeServices;

public class ModifierVehicule {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TextField Type;

    @FXML
    private Button butonM;
    @FXML
    private Vehicule currentVehicule;
    @FXML
    private String newType;



    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public void setType(String type) {Type.setText(type);
    }
    @FXML
    public void setUpdateAction(EventHandler<ActionEvent> eventHandler) {
        butonM.setOnAction(eventHandler);
    }

    public String getType() {
        return Type.getText();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void ModifierV(ActionEvent event) {
        String newType = Type.getText().trim();

        if (newType.isEmpty()) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champ vide");
                alert.setContentText("Veuillez saisir un type de véhicule.");
                alert.showAndWait();
            });
            return;
        }
        currentVehicule.setType(Type.getText());
        VehiculeServices VS = new VehiculeServices();

        try {
            VS.modifier(currentVehicule);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Véhicule modifié avec succès");
            alert.show();
            Stage currentStage = (Stage) butonM.getScene().getWindow();
            currentStage.close();
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    void initialize() {
        assert Type != null : "fx:id=\"Type\" was not injected: check your FXML file 'ModifierVehicule.fxml'.";
        assert butonM != null : "fx:id=\"butonM\" was not injected: check your FXML file 'ModifierVehicule.fxml'.";
        currentVehicule = new Vehicule();
    }



}
