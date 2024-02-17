package Java.Vue; 
import javax.imageio.ImageIO;
import javax.swing.*;

import Java.Controleur.ChangerPanneau;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;


public class GestionFilms extends JPanel{
    private MainFrame mainframe;

    public GestionFilms(MainFrame mainframe) throws IOException {
        this.mainframe=mainframe;
        // Utilisation de BorderLayout au lieu de setLayout(null)
        setLayout(new BorderLayout());

        super.setPreferredSize(new Dimension(600,400));

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
        JLabel gestionFilmsLabel = new JLabel("Gestion des films");
        gestionFilmsLabel.setForeground(Color.WHITE);
        gestionFilmsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        bandeNoirePanel.add(gestionFilmsLabel);

        // Ajout du bandeau en haut de la page
        add(bandeNoirePanel, BorderLayout.NORTH);

        // Zone de recherche + zone ajouter un film
        JPanel recherchePanel = new JPanel();
        recherchePanel.setBackground(Color.WHITE);

        JTextField rechercheField = new JTextField(20);
        JButton rechercheButton = new JButton("Rechercher");
        JButton ajouterFilmButton = new JButton("Ajouter un film");

        recherchePanel.add(rechercheField);
        recherchePanel.add(rechercheButton);
        recherchePanel.add(ajouterFilmButton);

        // Ajout de la zone de recherche
        recherchePanel.setBounds(0, 35, 600, 35);
        add(recherchePanel, BorderLayout.CENTER);

        // Panneau pour les images
        JPanel imagesPanel = new JPanel();
        imagesPanel.setBackground(Color.WHITE);
        imagesPanel.setLayout(new GridLayout(0, 4, 5, 5));  ///4 colonnes pour 4 films par lignes
        
        // Dossier contenant les images
        File folder = new File("Vue/Movie_Cover");
        File[] listOfFiles = folder.listFiles();

        // Afficher chaque image dans une colonne avec le titre en dessous et au milieu
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && isImageFile(file)) {
                    BufferedImage bufferedImage = ImageIO.read(file);

                    // Redimensionner l'image
                    int newWidth = 100;
                    int newHeight = 150;

                    Image scaledImage = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                    JLabel imageLabel = new JLabel();
                    imageLabel.setIcon(scaledImageIcon);

                    // Ajouter le titre du film en dessous et au milieu de l'image
                    String fileNameWithoutExtension = file.getName().replaceFirst("[.][^.]+$", ""); // Retirer l'extension du nom de fichier
                    fileNameWithoutExtension = fileNameWithoutExtension.replace("_", " "); // Remplacer les underscores par des espaces

                    JLabel titleLabel = new JLabel(fileNameWithoutExtension);
                    titleLabel.setHorizontalAlignment(JLabel.CENTER);

                    // Utiliser un GridLayout pour aligner l'image et le titre verticalement
                    JPanel imageWithTitlePanel = new JPanel();
                    imageWithTitlePanel.setLayout(new GridLayout(1, 1));
                    imageWithTitlePanel.add(imageLabel);
                    imageWithTitlePanel.add(titleLabel);

                    imagesPanel.add(imageWithTitlePanel);
                }
            }
        }

        ///  Définir les bounds pour le panneau d'images
        imagesPanel.setBorder(BorderFactory.createEmptyBorder(40, 5, 0, 5)); ///espace entre zone de recherche et panneau images

        // Utilisation d'une JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(imagesPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        // Ajout du panneau d'images à la partie centrale
        add(scrollPane, BorderLayout.CENTER);

        // Ajouter un écouteur d'événements au bouton de recherche
        rechercheButton.addActionListener(e -> {
        // Récupérer le texte saisi dans la zone de recherche
        String rechercheText = rechercheField.getText().toLowerCase(); // Convertir en minuscules pour la recherche insensible à la casse

        // Filtrer les films en fonction du texte de recherche
        imagesPanel.removeAll();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && isImageFile(file)) {
                    String fileNameWithoutExtension = file.getName().replaceFirst("[.][^.]+$", "").replace("_", " ").toLowerCase();

                // Vérifier si le titre du film correspond à la recherche
                    if (fileNameWithoutExtension.contains(rechercheText)) {
                        BufferedImage bufferedImage = null;
                        try {
                            bufferedImage = ImageIO.read(file);
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }

                    // Redimensionner l'image
                        int newWidth = 100;
                        int newHeight = 150;

                        Image scaledImage = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                        JLabel imageLabel = new JLabel();
                        imageLabel.setIcon(scaledImageIcon);

                        JLabel titleLabel = new JLabel(fileNameWithoutExtension);
                        titleLabel.setHorizontalAlignment(JLabel.CENTER);

                        JPanel imageWithTitlePanel = new JPanel();
                        imageWithTitlePanel.setLayout(new GridLayout(2, 1));
                        imageWithTitlePanel.add(imageLabel);
                        imageWithTitlePanel.add(titleLabel);

                        (imagesPanel).add(imageWithTitlePanel);
                    }
                }
            }
        }

        // Rafraîchir l'affichage
        imagesPanel.revalidate();
        imagesPanel.repaint();
    });
    }

    // Vérifier si le fichier est une image
    private boolean isImageFile(File file) {
        String name = file.getName();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

    private void initButton(JPanel bandeNoirePanel) {
        // Liens Gestion des Profils
        JButton gestionProfilsLabel = new JButton(new ChangerPanneau(this.mainframe, "Gestion des profils", "PROFILS",null));
        gestionProfilsLabel.setForeground(Color.LIGHT_GRAY);
        gestionProfilsLabel.setContentAreaFilled(false); // Rend la zone de contenu transparente
        gestionProfilsLabel.setBorderPainted(false);
        gestionProfilsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        bandeNoirePanel.add(gestionProfilsLabel);
    }

}