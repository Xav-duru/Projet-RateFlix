package Java.Vue;
import javax.imageio.ImageIO;
import javax.swing.*;

import Java.BDD.RequeteProfil;
import Java.Controleur.ChangerPanneau;
import Java.Item.ItemProfil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GestionProfils extends JPanel {

    private MainFrame mainFrame;
    private JPanel mainPanel;
    public ItemProfil currentProfil;



    public GestionProfils(MainFrame mainFrame) throws IOException {

        this.mainFrame = mainFrame;

        // Utilisation d'un layout pour la JFrame
        setLayout(new BorderLayout());

        // Ajuster la taille de la fenêtre
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

        // Liens Gestion des Profils
        JLabel gestionProfilsLabel = new JLabel("Gestion des profils");
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

        // Ajouter la zone de recherche
        JPanel recherchePanel = new JPanel();
        recherchePanel.setBackground(Color.WHITE);

        JTextField rechercheField = new JTextField(20);
        JButton rechercheButton = new JButton("Rechercher");

        recherchePanel.add(rechercheField);
        recherchePanel.add(rechercheButton);
        recherchePanel.setBounds(0, 35, 600, 35);
        add(recherchePanel, BorderLayout.CENTER);


        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 2, 10, 10));
        mainPanel.setBackground(Color.WHITE);
        //mainPanel.setBounds(0, 90, 600, 400);

        // Dossier contenant les images
        //File folder = new File("Vue/Profil_Image/");
        RequeteProfil requeteProfil = new RequeteProfil();
        List<ItemProfil> listItemProfil = requeteProfil.getUser();

        // Afficher chaque image dans une ligne
        if (listItemProfil != null) {
            for (int i = 0; i < listItemProfil.size(); i++) {
                currentProfil = listItemProfil.get(i);
                File file = new File("Vue/Profil_Image/" + currentProfil.getImage());
                isImageFile(file);
                    BufferedImage bufferedImage = ImageIO.read(file);

                    // Redimensionner l'image
                    int newWidth = 100;
                    int newHeight = 100;

                    Image scaledImage = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                    ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                    JLabel imageLabel = new JLabel();
                    imageLabel.setIcon(scaledImageIcon);

                    // Ajouter le nom d'utilisateur sous chaque image
                    String username = currentProfil.getUsername();
                    JButton userLabel = new JButton("<html><u style='text-decoration:none;'>" + username + "</u></html>");
                    userLabel.setForeground(Color.BLACK);
                    userLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    userLabel.setBorderPainted(false);
                    userLabel.setContentAreaFilled(false);
                    userLabel.setFocusPainted(false);
                    userLabel.setOpaque(false);


                    userLabel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ChangerPanneau nouveauPanneau = new ChangerPanneau(mainFrame, "Profil: " + currentProfil.getUsername(), "UN_PROFIL",currentProfil);
                            nouveauPanneau.actionPerformed(e);
                        }
                    });

                    // Panneau pour chaque paire image/username
                    JPanel pairPanel = new JPanel();
                    //pairPanel.setLayout(new GridLayout(1, 2));
                    pairPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                    pairPanel.add(imageLabel);
                    pairPanel.add(userLabel);
                    pairPanel.setBackground(Color.WHITE);
                    //pairPanel.setBounds(i % 2 == 0 ? 0 : 300, (i / 2) * 100, 300, 100);

                    // Ajouter chaque paire au panneau principal
                    mainPanel.add(pairPanel);
            }
        }

        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 5, 0, 5)); ///espace entre zone de recherche et panneau images


        // Utilisation d'une JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Ajout du panneau d'images à la partie centrale
        add(scrollPane, BorderLayout.CENTER);

        rechercheButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = rechercheField.getText().trim().toLowerCase();
                rechercherProfils(searchTerm);
            }
        });

        // // Rendre la fenêtre visible
        // setVisible(true);
        this.mainFrame.setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Vérifier si le fichier est une image
    private boolean isImageFile(File file) {
        String name = file.getName();
        return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
    }

   private void initButton(JPanel bandeNoirePanel) {
        // Liens Gestion des Films
        JButton gestionFilmsLabel = new JButton(new ChangerPanneau(this.mainFrame, "Gestion des films", "FILMS",null));
        gestionFilmsLabel.setForeground(Color.LIGHT_GRAY);
        gestionFilmsLabel.setContentAreaFilled(false); // Rend la zone de contenu transparente
        gestionFilmsLabel.setBorderPainted(false);
        gestionFilmsLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        bandeNoirePanel.add(gestionFilmsLabel);
    }
    private void rechercherProfils(String searchTerm) {
        // Réinitialiser le panneau principal
        mainPanel.removeAll();

        RequeteProfil requeteProfil = new RequeteProfil();
        List<ItemProfil> listItemProfil = requeteProfil.getUser();

        if (listItemProfil != null) {
            for (int i = 0; i < listItemProfil.size(); i++) {
                ItemProfil currentProfil = listItemProfil.get(i);

                // Vérifier si le profil correspond au terme de recherche
                if (currentProfil.getUsername().toLowerCase().contains(searchTerm)) {
                    // Code de création d'une paire image/username pour le résultat de recherche
                    File file = new File("Vue/Profil_Image/" + currentProfil.getImage());
                    if (isImageFile(file)) {
                        try {
                            BufferedImage bufferedImage = ImageIO.read(file);

                            // Redimensionner l'image
                            int newWidth = 100;
                            int newHeight = 100;
                            Image scaledImage = bufferedImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
                            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

                            JLabel imageLabel = new JLabel();
                            imageLabel.setIcon(scaledImageIcon);

                            // Ajouter le nom d'utilisateur sous chaque image
                            String username = currentProfil.getUsername();
                            JButton userLabel = new JButton("<html><u style='text-decoration:none;'>" + username + "</u></html>");
                            userLabel.setForeground(Color.BLACK);
                            userLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            userLabel.setBorderPainted(false);
                            userLabel.setContentAreaFilled(false);
                            userLabel.setFocusPainted(false);
                            userLabel.setOpaque(false);

                            userLabel.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    ChangerPanneau nouveauPanneau = new ChangerPanneau(mainFrame, "Profil: " + currentProfil.getUsername(), "UN_PROFIL", currentProfil);
                                    nouveauPanneau.actionPerformed(e);
                                }
                            });

                            // Panneau pour chaque paire image/username
                            JPanel pairPanel = new JPanel();
                            pairPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
                            pairPanel.add(imageLabel);
                            pairPanel.add(userLabel);
                            pairPanel.setBackground(Color.WHITE);

                            // Ajouter chaque paire au panneau principal
                            mainPanel.add(pairPanel);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

        // Mettre à jour l'affichage
        mainPanel.revalidate();
        mainPanel.repaint();
    }

}
