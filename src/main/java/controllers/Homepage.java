package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Homepage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nomliv;

    @FXML
    private TextField prenomliv;

    @FXML
    void Gestion_véhicules(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/G_véhicules.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        newStage.show();
    }

    @FXML
    void Liste_commandes(ActionEvent event) {


    }

    @FXML
    void zone_livraison(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Zone_liv.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        newStage.show();
    }


    @FXML
    void initialize() {
        assert nomliv != null : "fx:id=\"nomliv\" was not injected: check your FXML file 'Homepage.fxml'.";
        assert prenomliv != null : "fx:id=\"prenomliv\" was not injected: check your FXML file 'Homepage.fxml'.";

    }

}

