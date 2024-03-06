package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Models.client;
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
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.scene.control.Button;
public class ContactClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<client, Integer> columnId;

    @FXML
    private TableColumn<client, String> columnNom;

    @FXML
    private TableColumn<client, String> columnPrenom;

    @FXML
    private TableColumn<client, String> columnEmail;

    @FXML
    private TableColumn<client, String> columnUsername;

    @FXML
    private TableColumn<client, String> columnAdresse;

    @FXML
    private TableColumn<client, String> columnNumTel;

    @FXML
    private TableView<client> tableViewClients;

    private final String ACCOUNT_SID = "AC82754c7e6f5661b5f635d204f0a3db7a";
    private final String AUTH_TOKEN = "4e39646026a5bc38523283642aca1ee5";
    private final String FROM_NUMBER = "+12346010049";

    private services.ClientService clientService;
    private ComboBox<Object> columnActions;

    @FXML
    void initialize() {
        assert columnId != null : "fx:id=\"columnId\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnNom != null : "fx:id=\"columnNom\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnPrenom != null : "fx:id=\"columnPrenom\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnEmail != null : "fx:id=\"columnEmail\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnUsername != null : "fx:id=\"columnUsername\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnAdresse != null : "fx:id=\"columnAdresse\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnNumTel != null : "fx:id=\"columnNumTel\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert tableViewClients != null : "fx:id=\"tableViewClients\" was not injected: check your FXML file 'ContactClient.fxml'.";

        clientService = new services.ClientService();
        setupTableColumns();
        loadClientsData();
    }

    private void setupTableColumns() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        columnAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        columnNumTel.setCellValueFactory(new PropertyValueFactory<>("numTel"));
//        columnActions.setCellFactory(param -> new ListCell<Object>() {
//            final Button btnCan = new Button("Contacter");
//
//            {
//                btnCan.setOnAction(event -> {
//                    client selectedClient = tableViewClients.getItems().get(getIndex());
//                    sendDeliveryMessage(selectedClient);
//                });
//            }
//
//            @Override
//            protected void updateItem(Object item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(btnCan);
//                }
//            }
//        });

    }

    private void loadClientsData() {
        try {
            List<client> clientsList = clientService.afficher();
            ObservableList<client> observableList = FXCollections.observableArrayList(clientsList);
            tableViewClients.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
            afficherAlerte("Erreur lors du chargement des données clients.");
        }
    }

//    @FXML
//    void sendDeliveryMessage(client selectedClient) {
//        if (selectedClient != null) {
//            String messageBody = "Bonjour " + selectedClient.getPrenom() + ", votre livraison est en route. Merci de votre confiance!";
//            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//            String clientPhoneNumber = String.valueOf(selectedClient.getNumTel());
//            Message message = Message.creator(new PhoneNumber(clientPhoneNumber), new PhoneNumber(FROM_NUMBER), messageBody).create();
//            afficherAlerte("Message envoyé avec succès au client " + selectedClient.getPrenom());
//        } else {
//            afficherAlerte("Veuillez sélectionner un client pour envoyer un message.");
//        }
//    }
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
            afficherAlerte("Erreur lors de l'ouverture de la page d'accueil.");
        }
    }

    @FXML
    private void afficherAlerte(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
