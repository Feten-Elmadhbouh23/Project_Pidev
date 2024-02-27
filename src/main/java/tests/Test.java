package tests;

import BD.MyDataBase;
import Models.Vehicule;
import Models.Zone_liv;
import services.VehiculeServices;
import services.zone_livServices;

import java.sql.SQLException;
import java.util.List;


public class Test {
    public Test() {}
    public static void main (String[]args) {
        MyDataBase bd = MyDataBase.getInstance();
        VehiculeServices vs = new VehiculeServices();
        zone_livServices zs = new zone_livServices();
        try {
          Vehicule newVehicule = new Vehicule("Moto");
          vs.ajouter(newVehicule);
          System.out.println("Vehicule added successfully.");
        } catch (SQLException e) {
          throw new RuntimeException(e);
         }
        try {
            vs.modifier(new Vehicule("vehicule "));
            System.out.println("Vehicule modifiée!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            vs.supprimer(7);
            System.out.println("Vehicule supprimée!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            List<Vehicule> vehicule = vs.afficher() ;
            for (Vehicule v : vehicule) {
                System.out.println("ID : " + v.getId() + ", Type : " + v.getType());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des véhicules : " + e.getMessage());
        }

          /* try {
         Zone_liv newZone_liv = new Zone_liv( 1,"Bizerte");
             zs.ajouter(newZone_liv);
            System.out.println("zone added successfully.");
          } catch (SQLException e) {
           throw new RuntimeException(e);
            }
        try {
            List<Zone_liv> zone_livs = zs.afficher() ;
            for (Zone_liv z : zone_livs) {
                System.out.println("ID : " + z.getId()+ ", zone : " + z.getZone());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'affichage des zones : " + e.getMessage());
        }
    }*/


}}