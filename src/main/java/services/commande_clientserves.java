package services;

import BD.MyDataBase;
import Models.client;
import Models.commande_client;
import services.IZliv;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class commande_clientserves implements IZliv<commande_client> {
    private Connection connection;

    public commande_clientserves() {
        this.connection = MyDataBase.getInstance().getconn();
    }
    @Override
    public void ajouter(commande_client commandeClient) throws SQLException {
        String query = String.format("INSERT INTO commande_client (id_plat, id_commande, prix, quantite, status, date) VALUES (%d, %d, %f, %d, '%s', '%s')",
                commandeClient.getId_plat(), commandeClient.getId_commande(), commandeClient.getPrix(), commandeClient.getQuantite(),
                commandeClient.getStatus(), commandeClient.getDate());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        }
    }
    @Override
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
                commande.setDate(rs.getDate("date"));
                commande.setStatus(rs.getString("status"));

                commandes.add(commande);
            }
        }

        return commandes;
    }
}