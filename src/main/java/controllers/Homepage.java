package controllers;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Homepage {

    @FXML
    void Gestion_véhicules(ActionEvent event) {
        navigateTo("/G_véhicules.fxml", event);
    }
    @FXML
    public void handleContactClient(ActionEvent event) {
        navigateTo("/ContactClient.fxml",event );
    }

    @FXML
    void zone_livraison(ActionEvent event) {
        navigateTo("/Zone_liv.fxml", event);
    }

    private void navigateTo(String fxmlPath, ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        try {
            Parent root = loader.load();
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }



}

