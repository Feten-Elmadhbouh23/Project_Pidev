package Models;

public class Zone_liv {
    private int id;
    private String zone;
    public Zone_liv(int id, String zone) {
        this.id = id;
        this.zone = zone;
    }

    public Zone_liv() {
    }

    public Zone_liv(String zone) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }
}