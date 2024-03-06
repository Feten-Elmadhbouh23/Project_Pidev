package controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MapViewWindow {
    private Stage stage;
    @FXML
    private WebView mapView;


    public MapViewWindow() {
        stage = new Stage();
        stage.setTitle("Google Maps");
        mapView = new WebView();
        WebEngine webEngine = mapView.getEngine();
        Scene scene = new Scene(mapView, 800, 600);
        stage.setScene(scene);
    }

    public void showMap(String adresse) {
        try {
            String encodedAdresse = URLEncoder.encode(adresse, StandardCharsets.UTF_8.toString());
            WebEngine webEngine = mapView.getEngine();
            String url = "https://www.google.com/maps/@36.7532832,10.3362708,11.75z?entry=ttu" + encodedAdresse + "&AIzaSyDCSIt4BHmCbkIhOw1kfOFRAWayOQ9qdVI";
            webEngine.load(url);
            stage.show();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            afficherAlerte("Impossible de charger l'adresse dans Google Maps");
        }
    }

    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
