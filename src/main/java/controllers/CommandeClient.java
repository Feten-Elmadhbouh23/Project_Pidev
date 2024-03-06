package controllers;

import Models.commande_client;
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
import services.commande_clientserves;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class CommandeClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<commande_client> commandeClientTableView;

    @FXML
    private TableColumn<commande_client, Integer> dateColumn;

    @FXML
    private TableColumn<commande_client, Integer> idCommandeColumn;

    @FXML
    private TableColumn<commande_client, Integer> idPlatColumn;

    @FXML
    private TableColumn<commande_client, Double> prixColumn;

    @FXML
    private TableColumn<commande_client, Integer> quantiteColumn;

    @FXML
    private TableColumn<commande_client, String> statusColumn;

    @FXML
    private TableColumn<commande_client, Void> columnActions;

    @FXML
    void ok(ActionEvent event) {
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

    @FXML
    void initialize() {
        assert commandeClientTableView != null : "fx:id=\"commandeClientTableView\" was not injected: check your FXML file 'commande_client.fxml'.";
        assert dateColumn != null : "fx:id=\"dateColumn\" was not injected: check your FXML file 'commande_client.fxml'.";
        assert idCommandeColumn != null : "fx:id=\"idCommandeColumn\" was not injected: check your FXML file 'commande_client.fxml'.";
        assert idPlatColumn != null : "fx:id=\"idPlatColumn\" was not injected: check your FXML file 'commande_client.fxml'.";
        assert prixColumn != null : "fx:id=\"prixColumn\" was not injected: check your FXML file 'commande_client.fxml'.";
        assert quantiteColumn != null : "fx:id=\"quantiteColumn\" was not injected: check your FXML file 'commande_client.fxml'.";
        assert statusColumn != null : "fx:id=\"statusColumn\" was not injected: check your FXML file 'commande_client.fxml'.";
        assert columnActions != null : "fx:id=\"columnActions\" was not injected: check your FXML file 'commande_client.fxml'.";

        setupTableColumns();
        loadClientsData();
    }

    private void setupTableColumns() {
        idPlatColumn.setCellValueFactory(new PropertyValueFactory<>("id_plat"));
        idCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    private final commande_clientserves commande_clients = new commande_clientserves();

    private void loadClientsData() {
        try {
            List<commande_client> commande_clientList = commande_clients.afficher();
            ObservableList<commande_client> observableList = FXCollections.observableArrayList(commande_clientList);
            commandeClientTableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
