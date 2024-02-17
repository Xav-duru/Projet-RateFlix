package Java.BDD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import Java.Controleur.*;
import Java.Item.ItemProfil;

public class RequeteProfil {
    private ConnexionBDD dbConnection;
    private SupprimerProfil supprimerUnProfil;

    public void supprimerAvis(SupprimerProfil supprimerUnProfil){
        this.supprimerUnProfil = supprimerUnProfil;
        dbConnection.openConnection();
        try {
            String query = "DELETE FROM User WHERE userId = ?";
            PreparedStatement stmt = ConnexionBDD.getConnection().prepareStatement(query);
            stmt.setInt(1, this.supprimerUnProfil.getNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
    }

    public List<ItemProfil> getUser(){
        ConnexionBDD dbConnection = new ConnexionBDD();
        dbConnection.openConnection();

        List<ItemProfil> listUser = new ArrayList<ItemProfil>();
        try {
            String query = "SELECT userId, username, email, birthdate, image FROM User";
            PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String username = rs.getString("username");
                String email = rs.getString("email");
                Date birthdate = rs.getDate("birthdate");
                String image = rs.getString("image");

                // Créer une instance d'ItemProfil avec les données récupérées
                ItemProfil itemProfil = new ItemProfil(userId, username, email, birthdate, image);

                listUser.add(
                        new ItemProfil(userId,username,email,birthdate,image)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return listUser;
    }

    public List<Integer>  getUserId(){
        dbConnection.openConnection();

        List<Integer> listUserid = new ArrayList<>();
        try {
            String query = "SELECT userId FROM User";
            PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listUserid.add(rs.getInt("Id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return listUserid;
    }

    public List<String>  getUsername(){
        dbConnection.openConnection();
        List<String> listNom = new ArrayList<>();
        try {
            String query = "SELECT username FROM User";
            PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listNom.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return listNom;
    }

    public List<String>  getEmail(){
        dbConnection.openConnection();
        List<String> listEmail = new ArrayList<>();
        try {
            String query = "SELECT email FROM User";
            PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listEmail.add(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return listEmail;
    }

    public List<Date>  getBirthDate(){
        dbConnection.openConnection();
        List<Date> listBirthDate = new ArrayList<>();
        try {
            String query = "SELECT birthdate FROM User";
            PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listBirthDate.add(rs.getDate("birthdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return listBirthDate;
    }

    public List<String>  getImage(){
        dbConnection.openConnection();
        List<String> listImage = new ArrayList<>();
        try {
            String query = "SELECT image FROM User";
            PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listImage.add(rs.getString("image"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnection.closeConnection();
        }
        return listImage;
    }
    
}

