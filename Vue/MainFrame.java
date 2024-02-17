package Java.Vue;

import Java.Item.ItemProfil;

import java.io.IOException;
import javax.swing.*;

public class MainFrame extends JFrame {


    PanneauAccueil panneauAccueil = new PanneauAccueil(this);
    Connection connexion;
    GestionProfils profils;
    GestionFilms films;
    PanneauProfil unProfil;
    PanneauFilm unFilm;

    public MainFrame() throws IOException {
        this.setContentPane("CONNEXION", null);
        this.setSize(600, 400);
    }

    public void setContentPane(String nomPage, ItemProfil itemProfil) throws IOException {
        switch (nomPage) {
            case "CONNEXION":
                this.setTitle("RateFlix - Connexion");
                connexion = new Connection(this);
                this.setContentPane(this.connexion);
                break;
            case "ACCUEIL":
                this.setTitle("RateFlix - Accueil");
                panneauAccueil = new PanneauAccueil(this);
                this.setContentPane(this.panneauAccueil);
                this.setSize(600, 400);
                break;
            case "PROFILS":
                this.setTitle("RateFlix - Gestion des profils");
                profils = new GestionProfils(this);
                this.setContentPane(this.profils);
                this.setSize(600, 400);
                break;
            case "FILMS":
                this.setTitle("RateFlix - Gestion des films");
                films = new GestionFilms(this);
                this.setContentPane(this.films);
                this.setSize(600, 400);
                break;   
            case "UN_PROFIL":
                this.setTitle("RateFlix - Profil");
                unProfil = new PanneauProfil(this, itemProfil);
                this.setContentPane(this.unProfil);
                this.setSize(600, 400);
                break;
            // case "UN_FILM":
            //     this.setTitle("RateFlix - Film");
            //     unFilm = new PanneauFilm(this);
            //     this.setContentPane(this.unFilm);
            //     this.setSize(600, 400);
            //     break;          
        }
        this.revalidate(); // Actualise le contenu du frame
        this.repaint();    // Redessine le frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame;
            try {
                mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}





