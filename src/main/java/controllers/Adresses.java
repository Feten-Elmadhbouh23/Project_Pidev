package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Models.client;
import javafx.stage.Stage;
import services.ClientService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.util.Callback;

public class Adresses {
    @FXML
    private TableView<client> tableView;

    @FXML
    private TableColumn<client, Integer> idColumn;

    @FXML
    private TableColumn<client, String> nomColumn;

    @FXML
    private TableColumn<client, String> prenomColumn;

    @FXML
    private TableColumn<client, String> emailColumn;

    @FXML
    private TableColumn<client, String> usernameColumn;

    @FXML
    private TableColumn<client, String> adresseColumn;

    @FXML
    private TableColumn<client, Integer> numTelColumn;

    private ClientService clientService;

    public Adresses() {
        this.clientService = new ClientService();
    }
    private int selectedZoneId;

    public void receiveZoneId(int zoneId) {
        this.selectedZoneId = zoneId;
        loadClientsByZone();
    }

    private void loadClientsByZone() {
        try {
            List<client> clients = clientService.getClientsByZone(selectedZoneId);
            ObservableList<client> clientList = FXCollections.observableArrayList(clients);
            tableView.setItems(clientList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des clients", e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        numTelColumn.setCellValueFactory(new PropertyValueFactory<>("numTel"));

        TableColumn<client, Void> livrerColumn = new TableColumn<>("Action");
        livrerColumn.setCellFactory(new Callback<TableColumn<client, Void>, TableCell<client, Void>>() {
                @Override
                public TableCell<client, Void> call(TableColumn<client, Void> param) {
                    return new TableCell<client, Void>() {
                        private final Button livrerButton = new Button("Livrer");

                        {
                            livrerButton.setOnAction(event -> {
                                client currentClient = getTableView().getItems().get(getIndex());
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/livraison.fxml"));
                                    Parent root = loader.load();
                                    // Get the controller for the livraison page
                                    Livraison controller = loader.getController();
                                    controller.initData(currentClient); // Custom method to pass client data
                                    // Create a new scene
                                    Scene scene = new Scene(root);
                                    Stage stage = new Stage();
                                    stage.setScene(scene);
                                    stage.setTitle("Page de livraison");
                                    stage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }

                        @Override
                        protected void updateItem(Void item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setGraphic(null);
                            } else {
                                setGraphic(livrerButton);
                            }
                        }
                    };
                }
            });

tableView.getColumns().add(livrerColumn);

            loadClients();
    }

    private void loadClients() {
        try {
            List<client> clients = clientService.afficher();
            ObservableList<client> clientList = FXCollections.observableArrayList(clients);
            tableView.setItems(clientList);
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Erreur lors du chargement des clients", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    @FXML
    void handleClientSelection() throws IOException {
        // Récupérez le client sélectionné
        client clientSelectionne = tableView.getSelectionModel().getSelectedItem();

        // Chargez la vue de détails de livraison
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/livraison.fxml"));
        Parent root = loader.load();

        // Obtenez le contrôleur de la vue de détails de livraison
        Livraison controller = loader.getController();

        // Passez les informations du client sélectionné au contrôleur de la vue de détails de livraison
        controller.afficherDetailsClient(clientSelectionne);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void RetourG(ActionEvent actionEvent) {
    }
}
