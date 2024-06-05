package services;

import Models.Livreur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreurService implements IService<Livreur> {
    // Mettez ici vos informations de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/votre_base_de_donnees";
    private static final String USERNAME = "votre_nom_utilisateur";
    private static final String PASSWORD = "votre_mot_de_passe";

    // Requêtes SQL
    private static final String SELECT_ALL_QUERY = "SELECT * FROM Livreur";
    // Ajoutez d'autres requêtes SQL pour les opérations CRUD selon vos besoins

    @Override
    public void ajouter(Livreur livreur) throws SQLException {
        // Code pour ajouter un livreur à la base de données
    }

    @Override
    public void modifier(Livreur livreur) throws SQLException {
        // Code pour modifier un livreur dans la base de données
    }

    @Override
    public void supprimer(int id) throws SQLException {
        // Code pour supprimer un livreur de la base de données
    }

    @Override
    public List<Livreur> afficher() throws SQLException {
        List<Livreur> livreurs = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String adresse = resultSet.getString("adresse");
                int idVehicule = resultSet.getInt("idVehicule");
                int idZoneLivraison = resultSet.getInt("idZoneLivraison");
                int numTel = resultSet.getInt("numTel");
                Timestamp date = resultSet.getTimestamp("date");
                Livreur livreur = new Livreur(id, nom, prenom, email, password, adresse, idVehicule, idZoneLivraison, numTel, date);
                livreurs.add(livreur);
            }
        }
        return livreurs;
    }
}
