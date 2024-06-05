package controllers;

import Models.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Models.Livreur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Livraison {

    @FXML
    private TableView<Livreur> livreurTable;

    @FXML
    private TableColumn<Livreur, String> nomLivreurColumn;

    @FXML
    private TableColumn<Livreur, String> prenomLivreurColumn;

    @FXML
    private Label nomClientLabel;

    @FXML
    private Label prenomClientLabel;

    private ObservableList<Livreur> livreurs = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        // Initialise les colonnes pour extraire les données des objets Livreur
        nomLivreurColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomLivreurColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // Associe la liste observable de livreurs à la TableView
        livreurTable.setItems(livreurs);

        // Charge les données des livreurs (à remplacer par votre logique pour récupérer les données depuis votre base de données, par exemple)
        loadLivreursData();
    }

    private void loadLivreursData() {
        // Exemple de données à ajouter à la liste observable
        livreurs.add(new Livreur("John", "Doe", "john.doe@example.com", "password", "123 Main St", 1, 1, 123456789));
        livreurs.add(new Livreur("Jane", "Smith", "jane.smith@example.com", "password", "456 Elm St", 2, 1, 987654321));

        // Ajoutez vos données de livreurs depuis votre source de données ici
    }

    public void showRoute(ActionEvent actionEvent) {
        // Méthode appelée lorsque le bouton "Consulter Maps" est cliqué
        // Implémentez ici la logique pour afficher la route du livreur
    }

    public void sendStatus(ActionEvent actionEvent) {
        // Méthode appelée lorsque le bouton "Envoyer le statut de la livraison" est cliqué
        // Implémentez ici la logique pour envoyer le statut de la livraison
    }

    public void afficherDetailsClient(client clientSelectionne) {
        // Méthode appelée pour afficher les détails du client sélectionné
        nomClientLabel.setText(client.getNom());
        prenomClientLabel.setText(client.getPrenom());
    }

    public void RetourG(ActionEvent actionEvent) {
        // Méthode appelée lorsque le bouton "Retour" est cliqué
        // Implémentez ici la logique pour retourner à la page d'accueil
    }

    public void initData(client currentClient) {
        // Méthode pour initialiser les données de la page avec le client courant
        // Vous pouvez implémenter cette méthode pour pré-remplir les données si nécessaire
    }
}
