package tests;

import BD.MyDataBase;
import Models.Vehicule;
import Models.client;
import services.ClientService;
import services.VehiculeServices;
import services.zone_livServices;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyDataBase bd = MyDataBase.getInstance();
        VehiculeServices vs = new VehiculeServices();
        zone_livServices zs = new zone_livServices();
        ClientService Cs = new ClientService();

        try {
            Vehicule newVehicule = new Vehicule("Moto");
            vs.ajouter(newVehicule);
            System.out.println("Véhicule ajouté avec succès.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            vs.modifier(new Vehicule("Véhicule"));
            System.out.println("Véhicule modifié !!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            vs.supprimer(7);
            System.out.println("Véhicule supprimé !!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            List<Vehicule> vehicules = vs.afficher();
            for (Vehicule v : vehicules) {
                System.out.println("ID : " + v.getId() + ", Type : " + v.getType());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des véhicules : " + e.getMessage());
        }
    }
}
