package Java.Vue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import Java.BDD.RequeteProfil;
import Java.Controleur.ChangerPanneau;
import Java.Item.ItemProfil;

public class PanneauProfil extends JPanel {

    List<Integer> listNumber;
    private MainFrame mainFrame;
    private ItemProfil itemProfil;


    public PanneauProfil(MainFrame mainFrame, ItemProfil itemProfil) throws IOException {
        this.mainFrame = mainFrame;
        this.itemProfil = itemProfil;

        // Utilisation d'un layout pour la JFrame
        setLayout(new BorderLayout());

        // Ajuster la taille de la fenêtre
        super.setPreferredSize(new Dimension(600, 400));

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

        // Liens Gestion des Profils
        JLabel gestionProfilsLabel = new JLabel("Profil");
        gestionProfilsLabel.setForeground(Color.WHITE);
        gestionProfilsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        bandeNoirePanel.add(gestionProfilsLabel);

        // Espace entre GF et GP
        bandeNoirePanel.add(new JLabel("   "));

        // // Liens Gestion des Films
        // JLabel gestionFilmsLabel = new JLabel("Gestion des films");
        // gestionFilmsLabel.setForeground(Color.LIGHT_GRAY);
        // gestionFilmsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        // bandeNoirePanel.add(gestionFilmsLabel);

        initButton(bandeNoirePanel);

        // Ajouter le bandeau en haut de la page
        add(bandeNoirePanel, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        RequeteProfil requeteProfil = new RequeteProfil();
        List<ItemProfil> listItemProfil = requeteProfil.getUser();
        for (int i = 0; i < listItemProfil.size(); i++) {
            ItemProfil currentProfil = new GestionProfils(mainFrame).currentProfil;
            currentProfil = listItemProfil.get(i);
            File file = new File("Vue/Profil_Image/" + currentProfil.getImage());
            isImageFile(file);
            BufferedImage bufferedImage = ImageIO.read(file);

            // Redimensionner l'image
            int newWidth = 100;
            int newHeight = 100;

            Image scaledImage = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

            JLabel profilImageLabel = new JLabel();
            infoPanel.add(profilImageLabel, gbc);

            // Ajouter les informations du profil à droite
            gbc.gridx = 1; // Passe à la colonne suivante
            gbc.anchor = GridBagConstraints.NORTHWEST;


            // Supposons que les informations du profil sont stockées dans l'objet itemProfil
            JLabel pseudoLabel = new JLabel("Pseudo: " + new GestionProfils(mainFrame).currentProfil.getUsername());
            infoPanel.add(pseudoLabel, gbc);

            JLabel emailLabel = new JLabel("Email: " + new GestionProfils(mainFrame).currentProfil.getEmail());
            gbc.gridy = 1; // Passe à la ligne suivante
            infoPanel.add(emailLabel, gbc);

            JLabel birthdateLabel = new JLabel("Birthdate: " + new GestionProfils(mainFrame).currentProfil.getBirthDate());
            gbc.gridy = 2; // Passe à la ligne suivante
            infoPanel.add(birthdateLabel, gbc);

            // Ajouter le panneau d'informations à la page
            add(infoPanel, BorderLayout.CENTER);


        }
    }

        private void initButton (JPanel bandeNoirePanel){
            // Liens Gestion des Films
            JButton GestionProfilsLabel = new JButton(new ChangerPanneau(this.mainFrame, "Gestion des films", "FILMS", null));
            GestionProfilsLabel.setForeground(Color.LIGHT_GRAY);
            GestionProfilsLabel.setContentAreaFilled(false); // Rend la zone de contenu transparente
            GestionProfilsLabel.setBorderPainted(false);
            GestionProfilsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
            bandeNoirePanel.add(GestionProfilsLabel);

            JPanel boutonRetourPanel = new JPanel(new BorderLayout());
            JButton retour = new JButton(new ChangerPanneau(this.mainFrame, "Retour", "PROFILS", null));
            retour.setForeground(Color.BLACK);
            retour.setBorderPainted(false);
            retour.setFont(new Font("American Typewriter", Font.BOLD, 12));
            boutonRetourPanel.add(retour, BorderLayout.EAST);
            bandeNoirePanel.add(boutonRetourPanel);

        }

    private boolean isImageFile(File file) {
        String name = file.getName();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

    public List<Integer> getListNumber() {
        return listNumber;
    }

}
