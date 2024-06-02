package controllers;

import javafx.scene.web.WebView;
import Models.client;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.ClientService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Adresses {

    @FXML
    private ResourceBundle resources;

    @FXML
    private TextField affichez;

    @FXML
    private TableView<client> tableViewClients;
    @FXML
    private TableColumn<client, Integer> columnId;
    @FXML
    private TableColumn<client, String> columnNom, columnPrenom, columnEmail, columnUsername, columnAdresse;
    @FXML
    private TableColumn<client, Integer> columnNumTel;
    @FXML
    private TableColumn<client, Void> columnActions;

    private ClientService clientService = new ClientService();
    @FXML
    private WebView mapView;

    private String selectedZone;

    @FXML
    void RetourG(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Zone_liv.fxml"));
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
        assert affichez != null : "fx:id=\"affichez\" was not injected: check your FXML file 'Adresses.fxml'.";
        assert mapView != null : "fx:id=\"mapView\" was not injected: check your FXML file 'Adresses.fxml'.";

        setupTableColumns();
        loadClientsData();
    }

    public void receiveZone(String zone) {
        selectedZone = zone;
        affichez.setText(zone);
        loadFilteredClientsData();
    }

    private void loadFilteredClientsData() {
        try {
            List<client> clientsList = clientService.getClientsByZone(selectedZone);
            ObservableList<client> observableList = FXCollections.observableArrayList(clientsList);
            tableViewClients.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setupTableColumns() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        columnNumTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));

        columnActions.setCellFactory(param -> new TableCell<>() {
            final Button btnMap = new Button("Consulter dans Maps");

            {
                btnMap.setOnAction(event -> {
                    client client = getTableView().getItems().get(getIndex());
                    String adresse = client.getAdresse();
                    openInMaps(adresse);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnMap);
                }
            }
        });
    }

    private void openInMaps(String adresse) {
        // Implémentez la logique pour afficher l'adresse dans Google Maps
    }

    private void loadClientsData() {
        try {
            List<client> clientsList = clientService.afficher();
            ObservableList<client> observableList = FXCollections.observableArrayList(clientsList);
            tableViewClients.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
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
