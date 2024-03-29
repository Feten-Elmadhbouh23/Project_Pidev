
package Models;

import java.sql.Date;

public class commande_client {
    private int id;
    private int id_plat;
    private int id_commande;
    private double prix;
    private int quantite;
    private Date date;
    private String status;

    public commande_client(int id, int id_plat, int id_commande, double prix, int quantite, Date date, String status) {
        this.id = id;
        this.id_plat = id_plat;
        this.id_commande = id_commande;
        this.prix = prix;
        this.quantite = quantite;
        this.date = date;
        this.status = status;
    }

    public commande_client() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_plat() {
        return id_plat;
    }

    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

