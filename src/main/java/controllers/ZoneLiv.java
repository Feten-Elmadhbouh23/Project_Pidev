package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import Models.Vehicule;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.VehiculeServices;
import services.zone_livServices;

import javax.security.auth.callback.Callback;

public class ZoneLiv {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Zone_liv> Ztab;

    @FXML
    private TextField affichez;

    @FXML
    private TableColumn<Zone_liv, Integer> idCol;
    @FXML
    private TableColumn<Zone_liv, String> typeCol;

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
    void initialize() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("zone"));
        zone_livServices ZS = new zone_livServices();

        try {
            List<Zone_liv> zone_liv = ZS.afficher();
            ObservableList<Zone_liv> data = FXCollections.observableArrayList(zone_liv);
            Ztab.setItems(data);
            Ztab.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addChoiceButtonsToTable();
    }

    private void addChoiceButtonsToTable() {
        TableColumn<Zone_liv, Void> colBtn = new TableColumn<>("Choisir");

        colBtn.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Choisir");

            {
                btn.setOnAction(event -> {
                    Zone_liv zone_liv = getTableView().getItems().get(getIndex());
                    handlechoiceAction(zone_liv);
                });
            }

            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        });

        Ztab.getColumns().add(colBtn);
    }

    private void handlechoiceAction(Zone_liv zone_liv) {
        affichez.setText(zone_liv.getZone());
    }
}
