package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyDataBase {
    private Connection conn;
    String url = "jdbc:mysql://localhost:3306/choubikloubiki";
    String user = "root";
    String pwd = "";
    private static MyDataBase instance;

    private MyDataBase() {
        try {
            this.conn = DriverManager.getConnection(this.url, this.user, this.pwd);
            System.out.println("Connected");
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
        }

    }

    public static MyDataBase getInstance() {
        if (instance == null) {
            instance = new MyDataBase();
        }

        return instance;
    }

    public Connection getconn() {
        return this.conn;
    }
}