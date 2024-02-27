package services;
import Models.Vehicule;
import Models.Zone_liv;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import BD.MyDataBase;

public class zone_livServices implements IZliv<Zone_liv> {
    private final Connection connection;

    public zone_livServices() {
        this.connection = MyDataBase.getInstance().getconn();
    }


    @Override
    public void ajouter(Zone_liv zone_liv) throws SQLException {
        String req = "INSERT INTO zone_liv (zone) VALUES(?)";
        try (PreparedStatement pst = connection.prepareStatement(req)) {
            pst.setString(1, zone_liv.getZone());
            pst.executeUpdate();
        }
    }



    public List<Zone_liv> afficher() throws SQLException {
        String req = "select * from Zone_liv";
        List<Zone_liv> zonesLivraison = new ArrayList<>();

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Zone_liv z = new Zone_liv();
            z.setId(rs.getInt("id"));
            z.setZone(rs.getString("zone"));
            zonesLivraison.add(z);
        }
        return zonesLivraison;
    }
}