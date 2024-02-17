package Java.Vue;

import Java.Controleur.ChangerPanneau;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PanneauFilm extends JPanel{
    private MainFrame mainframe;
    public PanneauFilm(MainFrame mainframe) throws IOException{
        this.mainframe=mainframe;
        // Utilisation de BorderLayout au lieu de setLayout(null)
        setLayout(new BorderLayout());

        // Barre noire en haut
        JPanel bandeNoirePanel = new JPanel();
        bandeNoirePanel.setBackground(Color.BLACK);
        bandeNoirePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        // Texte "Rateflix" à gauche en rouge
        JLabel rateflixLabel = new JLabel("Rateflix");
        rateflixLabel.setFont(new Font("American Typewriter", Font.BOLD, 20));
        rateflixLabel.setForeground(Color.RED);
        bandeNoirePanel.add(rateflixLabel);

        // Espace entre Rateflix et GP
        bandeNoirePanel.add(new JLabel("   "));

        // // Liens Gestion des Profils
        // JLabel GestionFilmsLabel = new JLabel("Gestion des profils");
        // GestionFilmsLabel.setForeground(Color.LIGHT_GRAY);
        // GestionFilmsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        // bandeNoirePanel.add(GestionFilmsLabel);
        initButton(bandeNoirePanel);

        // Espace entre GF et GP
        bandeNoirePanel.add(new JLabel("   "));

        // Liens Gestion des Films
        //JLabel gestionFilmsLabel = new JLabel("Gestion des films");
        JButton gestionFilmsLabel = new JButton(new ChangerPanneau(this.mainframe, "Gestion des films", "FILMS", null));
        gestionFilmsLabel.setForeground(Color.WHITE);
        gestionFilmsLabel.setContentAreaFilled(false); // Rend la zone de contenu transparente
        gestionFilmsLabel.setBorderPainted(false);
        gestionFilmsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        bandeNoirePanel.add(gestionFilmsLabel);

        // Ajout du bandeau en haut de la page
        add(bandeNoirePanel, BorderLayout.NORTH);
    }

    private void initButton(JPanel bandeNoirePanel) {
        // Liens Gestion des Films
        JButton gestionProfilsLabel = new JButton(new ChangerPanneau(this.mainframe, "Gestion des profils", "PROFILS", null));
        gestionProfilsLabel.setForeground(Color.LIGHT_GRAY);
        gestionProfilsLabel.setContentAreaFilled(false); // Rend la zone de contenu transparente
        gestionProfilsLabel.setBorderPainted(false);
        gestionProfilsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        bandeNoirePanel.add(gestionProfilsLabel);
    }

}
