// commande_clientserves.java
package services;

import Models.commande_client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class commande_clientserves {
    private Connection connection;

    public commande_clientserves(Connection connection) {
        this.connection = connection;
    }

    public void ajouter(commande_client commandeClient) throws SQLException {
        // Code pour ajouter une commande client dans la base de données
    }

    public List<commande_client> afficher() throws SQLException {
        String req = "SELECT * FROM commande_client";
        List<commande_client> commandes = new ArrayList<>();

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(req)) {

            while (rs.next()) {
                commande_client commande = new commande_client();
                commande.setId_plat(rs.getInt("id_plat"));
                commande.setId_commande(rs.getInt("id_commande"));
                commande.setPrix(rs.getDouble("prix"));
                commande.setQuantite(rs.getInt("quantite"));
                commande.setDate(rs.getInt("date"));
                commande.setStatus(rs.getString("status"));

                commandes.add(commande);
            }
        }

        return commandes;
    }
}
