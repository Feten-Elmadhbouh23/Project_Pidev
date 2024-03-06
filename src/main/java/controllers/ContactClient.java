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
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.event.ActionEvent;
public class ContactClient {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columnActions;

    @FXML
    private TableColumn<?, ?> columnAdresse;

    @FXML
    private TableColumn<?, ?> columnEmail;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnNom;

    @FXML
    private TableColumn<?, ?> columnNumTel;

    @FXML
    private TableColumn<?, ?> columnPrenom;

    @FXML
    private TableColumn<?, ?> columnUsername;

    @FXML
    private TableView<client> tableViewClients;
    @FXML
    private services.ClientService ClientService;
    private final String ACCOUNT_SID = "Votre_SID_de_compte_Twilio";
    private final String AUTH_TOKEN = "Votre_token_d'authentification_Twilio";
    private final String FROM_NUMBER = "Votre_numéro_Twilio";
    //////////////////////////////////////////////////////////////////////////////////////
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
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////





    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    void initialize() {
        assert columnActions != null : "fx:id=\"columnActions\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnAdresse != null : "fx:id=\"columnAdresse\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnEmail != null : "fx:id=\"columnEmail\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnId != null : "fx:id=\"columnId\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnNom != null : "fx:id=\"columnNom\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnNumTel != null : "fx:id=\"columnNumTel\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnPrenom != null : "fx:id=\"columnPrenom\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert columnUsername != null : "fx:id=\"columnUsername\" was not injected: check your FXML file 'ContactClient.fxml'.";
        assert tableViewClients != null : "fx:id=\"tableViewClients\" was not injected: check your FXML file 'ContactClient.fxml'.";
        ClientService = new services.ClientService();
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
}
    private void loadClientsData() {
        try {
            List<client> clientsList = ClientService.afficher();
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
