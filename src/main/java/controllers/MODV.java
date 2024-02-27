
package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import Models.Vehicule;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.VehiculeServices;

public class MODV {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField Type;
    @FXML
    private Button updateButton;
    private Vehicule currentVehicule;

    @FXML
    private TableView<Vehicule> tableView;

    @FXML
    void Retourbu(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/G_véhicules.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 ///////////////////////////////////////////////////////////////////////////////////////////////////////
 public void setType(String type) {
     Type.setText(type);
 }

    public void setUpdateAction(EventHandler<ActionEvent> eventHandler) {
        updateButton.setOnAction(eventHandler);
    }
    public String getType() {
        return Type.getText();
    }
    @FXML
    void validerMod(ActionEvent event) {
        String newType = getType();
        String typeVehicule = newType .trim();
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

        if (newType != null && !newType.isEmpty()) {
            currentVehicule.setType(newType);
            VehiculeServices VS = new VehiculeServices();

            try {
                VS.modifier(currentVehicule);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Véhicule modifié avec succès");
                alert.showAndWait();

                Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText("Une erreur s'est produite lors de la modification du véhicule.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champ vide");
            alert.setContentText("Veuillez saisir un type de véhicule.");
            alert.showAndWait();
        }
    }
}




