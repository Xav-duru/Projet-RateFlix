package Java.BDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionBDD {
 
    private static Connection connexion;

    public void openConnection() {
        // Informations de connexion à la base de données
            String url = "jdbc:mysql://localhost:8889/rateflix";
            String login = "root";
            String password = "root";
 
        try {
            // Charger le pilote JDBC
            //Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(url, login, password); 
        /*} catch (ClassNotFoundException e) {
            e.printStackTrace();*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connexion;
    }
}
