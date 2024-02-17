package Java.BDD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequeteConnexion {

    private ConnexionBDD dbConnection;

    public RequeteConnexion(){
        //this.dbConnection = new ConnexionBDD();
        ConnexionBDD dbConnection = new ConnexionBDD();
    }

    public static boolean verifierAdmin(String username, String password) {
        ConnexionBDD dbConnection = new ConnexionBDD();
        dbConnection.openConnection();
        String query = "SELECT admin FROM User WHERE username = ? AND password = ? LIMIT 0, 25";

        try (PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return resultSet.getString("admin").contentEquals("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Assurez-vous de fermer la connexion ici (si nécessaire)
        }

        return false;
    }

}
