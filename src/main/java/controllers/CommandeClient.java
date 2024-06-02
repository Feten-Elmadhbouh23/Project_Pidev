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
import javafx.scene.layout.HBox;
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

    private final commande_clientserves commande_clients = new commande_clientserves();

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

    @FXML
    void initialize() {
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

        addDeleteButtonToTable();
    }

    private void loadClientsData() {
        try {
            List<commande_client> commande_clientList = commande_clients.afficher();
            ObservableList<commande_client> observableList = FXCollections.observableArrayList(commande_clientList);
            commandeClientTableView.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addDeleteButtonToTable() {
        columnActions.setCellFactory(param -> new TableCell<>() {
            private final Button btnDelete = new Button("Marquer livraison ");
            private final HBox pane = new HBox(btnDelete);

            {
                btnDelete.setOnAction(event -> {
                    commande_client commande = getTableView().getItems().get(getIndex());
                    handleSupprimerAction(commande);
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

    private void handleSupprimerAction(commande_client commande) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Supprimer la commande " + commande.getId_commande() + " ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            try {
                commande_clients.supprimer(commande.getId_commande());
                commandeClientTableView.getItems().remove(commande);
                commandeClientTableView.refresh(); // Rafraîchir l'affichage de la table
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
