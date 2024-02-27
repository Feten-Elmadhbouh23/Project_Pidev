package services;

import Models.Vehicule;
import BD.MyDataBase;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculeServices implements IService<Vehicule> {
    private Connection connection;

    public VehiculeServices() {
        this.connection = MyDataBase.getInstance().getconn();
    }

    @Override
    public void ajouter(Vehicule vehicule) throws SQLException {
        if (vehicule.getType() != null && !vehicule.getType().isEmpty()) {
            if (existeDeja(vehicule)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Véhicule déjà existant");
                alert.setHeaderText(null);
                alert.setContentText("Le véhicule " + vehicule.getType() + " existe déjà dans la base de données. Veuillez changer le type ou retourner à la page précédente.");
                alert.showAndWait();
            } else {
                String req = "INSERT INTO vehicule (type) VALUES(?)";
                try (PreparedStatement pst = connection.prepareStatement(req)) {
                    pst.setString(1, vehicule.getType());
                    pst.executeUpdate();
                }
            }
        } else {
            // Si le type de véhicule est vide, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Le type de véhicule ne peut pas être vide.");
            alert.showAndWait();
        }
    }

    private boolean existeDeja(Vehicule vehicule) throws SQLException {
        String req = "SELECT COUNT(*) FROM vehicule WHERE type = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, vehicule.getType());
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    @Override
    public void modifier(Vehicule vehicule) throws SQLException {
        String req = "UPDATE vehicule SET type = ? WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, vehicule.getType());
            pst.setInt(2, vehicule.getId());
            pst.executeUpdate();
        }
    }

    @Override
    public void supprimer(int id) throws SQLException {
        String req = "DELETE FROM vehicule WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        }
    }

    @Override
    public List<Vehicule> afficher() throws SQLException {
        String req = "SELECT * FROM vehicule";
        List<Vehicule> vehicules = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement(req);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Vehicule v = new Vehicule();
                v.setId(rs.getInt("id"));
                v.setType(rs.getString("type"));
                vehicules.add(v);
            }
        }
        return vehicules;
    }
}