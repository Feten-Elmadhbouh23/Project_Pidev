package services;

import BD.MyDataBase;
import Models.client;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService implements IService<client> {
    private Connection connection;

    public ClientService() {
        this.connection = MyDataBase.getInstance().getconn();
    }

    @Override
    public void ajouter(client client) throws SQLException {
        if (client.getNom() != null && !client.getNom().isEmpty()) {
            if (existeDeja(client)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Client déjà existant");
                alert.setHeaderText(null);
                alert.setContentText("Le client " + client.getNom() + " existe déjà dans la base de données. Veuillez changer le nom ou retourner à la page précédente.");
                alert.showAndWait();
            } else {
                String req = "INSERT INTO client (nom, prenom, email, password, username, adresse, num_tel) VALUES(?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = connection.prepareStatement(req)) {
                    pst.setString(1, client.getNom());
                    pst.setString(2, client.getPrenom());
                    pst.setString(3, client.getEmail());
                    pst.setString(4, client.getPassword());
                    pst.setString(5, client.getUsername());
                    pst.setString(6, client.getAdresse());
                    pst.setString(7, String.valueOf(client.getNumTel()));
                    pst.executeUpdate();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nom du client ne peut pas être vide.");
            alert.showAndWait();
        }
    }

    @Override
    public void modifier(client client) throws SQLException {
        if (client.getNom() != null && !client.getNom().isEmpty()) {
            if (existeDeja(client)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Client déjà existant");
                alert.setHeaderText(null);
                alert.setContentText("Le client " + client.getNom() + " existe déjà dans la base de données. Veuillez changer le nom ou retourner à la page précédente.");
                alert.showAndWait();
            } else {
                String req = "UPDATE client SET nom = ?, prenom = ?, email = ?, password = ?, username = ?, adresse = ?, num_tel = ? WHERE id = ?";
                try (PreparedStatement pst = connection.prepareStatement(req)) {
                    pst.setString(1, client.getNom());
                    pst.setString(2, client.getPrenom());
                    pst.setString(3, client.getEmail());
                    pst.setString(4, client.getPassword());
                    pst.setString(5, client.getUsername());
                    pst.setString(6, client.getAdresse());
                    pst.setString(7, String.valueOf(client.getNumTel()));

                    pst.executeUpdate();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le nom du client ne peut pas être vide.");
            alert.showAndWait();
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    @Override
    public List<client> afficher() throws SQLException {
        List<client> clients = new ArrayList<>();
        String req = "SELECT * FROM client";
        try (PreparedStatement pst = connection.prepareStatement(req);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                client client = new client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("adresse"),
                        rs.getInt("num_tel")

                );
                clients.add(client);
            }
        }
        return clients;
    }

    private boolean existeDeja(client client) throws SQLException {
        String req = "SELECT COUNT(*) FROM client WHERE nom = ? AND prenom = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, client.getNom());
            pst.setString(2, client.getPrenom());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    ///////////////////////////////////////////////////////////////////////////////
    public List<client> getClientsByZone(String zone) throws SQLException {
        List<client> clients = new ArrayList<>();
        String req = "SELECT * FROM client WHERE adresse LIKE ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, "%" + zone + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    client client = new client(
                            rs.getInt("id"),
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("username"),
                            rs.getString("adresse"),
                            rs.getInt("num_tel")
                    );
                    clients.add(client);
                }
            }
        }
        return clients;
    }



}