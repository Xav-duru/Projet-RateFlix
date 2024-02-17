package Java.BDD;

import Java.Item.ItemFilm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class RequeteFilm {


    public class RequeteProfil {
        private ConnexionBDD dbConnection;

        public List<ItemFilm> getMovie(){
            ConnexionBDD dbConnection = new ConnexionBDD();
            dbConnection.openConnection();

            List<ItemFilm> listMovie = new ArrayList<ItemFilm>();
            try {
                String query = "SELECT movieId, name, director, average, releaseDate, image FROM Movie";
                PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int movieId = rs.getInt("movieId");
                    String name = rs.getString("name");
                    String director = rs.getString("director");
                    int average = rs.getInt("average");
                    Date releaseDate = rs.getDate("releaseDate");
                    String image = rs.getString("image");

                    // Créer une instance d'ItemProfil avec les données récupérées
                    ItemFilm itemFilm = new ItemFilm(movieId, name, director, average, releaseDate, image);

                    listMovie.add(
                            new ItemFilm(movieId, name, director, average, releaseDate, image)
                    );
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.closeConnection();
            }
            return listMovie;
        }

        public List<Integer>  getMovieId(){
            dbConnection.openConnection();

            List<Integer> listMovieId = new ArrayList<>();
            try {
                String query = "SELECT movieId FROM Movie";
                PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    listMovieId.add(rs.getInt("movieId"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.closeConnection();
            }
            return listMovieId;
        }

        public List<String>  getName(){
            dbConnection.openConnection();
            List<String> listName = new ArrayList<>();
            try {
                String query = "SELECT name FROM Movie";
                PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    listName.add(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.closeConnection();
            }
            return listName;
        }

        public List<String>  getDirector(){
            dbConnection.openConnection();
            List<String> listDirector = new ArrayList<>();
            try {
                String query = "SELECT director FROM Movie";
                PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    listDirector.add(rs.getString("director"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.closeConnection();
            }
            return listDirector;
        }

        public List<Integer>  getAverage(){
            dbConnection.openConnection();

            List<Integer> listAverage = new ArrayList<>();
            try {
                String query = "SELECT average FROM Movie";
                PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    listAverage.add(rs.getInt("average"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.closeConnection();
            }
            return listAverage;
        }
        public List<Date>  getReleaseDate(){
            dbConnection.openConnection();
            List<Date> listReleaseDate = new ArrayList<>();
            try {
                String query = "SELECT releaseDate FROM Movie";
                PreparedStatement preparedStatement = ConnexionBDD.getConnection().prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    listReleaseDate.add(rs.getDate("releaseDate"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbConnection.closeConnection();
            }
            return listReleaseDate;
        }

        public List<String>  getImage(){
            dbConnection.openConnection();
            List<String> listImage = new ArrayList<>();
            try {
                String query = "SELECT image FROM Movie";
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
}
