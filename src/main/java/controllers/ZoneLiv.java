package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
import Models.Zone_liv;
import services.zone_livServices;

public class ZoneLiv {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Zone_liv> Ztab;
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

    @FXML
    private void addChoiceButtonsToTable() {
        TableColumn<Zone_liv, Void> colBtn = new TableColumn<>("Choisir");

        colBtn.setCellFactory(param -> new TableCell<>() {
            private final Button btn = new Button("Choisir");

            {
                btn.setOnAction(event -> {
                    Zone_liv zone_liv = getTableView().getItems().get(getIndex());
                    handlechoiceAction(zone_liv.getId());
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

    @FXML
    private void handlechoiceAction(int zoneId) {
        sendZoneToAdresses(zoneId);
    }

    @FXML
    private void sendZoneToAdresses(int zoneId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Adresses.fxml"));
            Parent root = loader.load();
            Adresses controller = loader.getController();
            controller.receiveZoneId(zoneId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
