package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Models.Zone_liv;
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
import services.zone_livServices;

public class ListCommandes {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Zone_liv> Ztab; // Correction du nom de TableView

    @FXML
    private TextField affichez_C;

    @FXML
    private TableColumn<Zone_liv, Integer> idCol; // Spécification du type de données pour les colonnes
    @FXML
    private TableColumn<Zone_liv, Integer> prixCol;
    @FXML
    private TableColumn<Zone_liv, Integer> quancol;
    @FXML
    private TableColumn<Zone_liv, String> statuscol;
    @FXML
    private TableColumn<Zone_liv, Integer> datecol;

    @FXML
    void RetourG(ActionEvent event) {
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
    void ValiderCommende(ActionEvent event) {

    }

//    @FXML
//    void initialize() {
//        idCol.setCellValueFactory(new PropertyValueFactory<>("id_plat"));
//        idCol.setCellValueFactory(new PropertyValueFactory<>("id_commande"));
//        prixCol.setCellValueFactory(new PropertyValueFactory<>("prix"));
//        quancol.setCellValueFactory(new PropertyValueFactory<>("quantité"));
//        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
//        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
//
//        zone_livServices ZS = new zone_livServices();
//
//        try {
//            List<Zone_liv> zone_liv = ZS.afficher();
//            ObservableList<Zone_liv> data = FXCollections.observableArrayList(zone_liv);
//            Ctab.setItems(data);
//            Ctab.refresh();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        addChoiceButtonsToTable();
//    }
//
//    private void addChoiceButtonsToTable() {
//        TableColumn<Zone_liv, Void> colBtn = new TableColumn<>("Choisir");
//
//        colBtn.setCellFactory(param -> new TableCell<>() {
//            private final Button btn = new Button("Choisir");
//
//            {
//                btn.setOnAction(event -> {
//                    Zone_liv zone_liv = getTableView().getItems().get(getIndex());
//                    handlechoiceAction(zone_liv);
//                });
//            }
//
//            @Override
//            public void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(btn);
//                }
//            }
//        });
//
//        Ztab.getColumns().add(colBtn);
//    }
//
//    private void handlechoiceAction(Zone_liv zone_liv) {
//        affichez_C.setText(zone_liv.getZone()); // Correction du nom de TextField
//    }

}
