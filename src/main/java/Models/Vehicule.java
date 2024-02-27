package Models;

public class Vehicule {
    private int id;
    private String type;


    public Vehicule(int id,String type) {
        this.id = id;
        this.type = type;
    }

    public Vehicule(String type) {
        this.type = type;
    }

    public Vehicule() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
