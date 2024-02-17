package Java.Vue;

import javax.swing.*;

import Java.Controleur.ChangerPanneau;

import java.awt.*;

public class PanneauAccueil extends JPanel {

    private MainFrame mainFrame;

    public PanneauAccueil(MainFrame mainJFrame) {
        this.mainFrame = mainJFrame;
        setLayout(null);

        super.setPreferredSize(new Dimension(600,400));

        // Barre noire en haut
        JPanel bandeNoirePanel = new JPanel();
        bandeNoirePanel.setBackground(Color.BLACK);
        bandeNoirePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
        bandeNoirePanel.setBounds(0, 0, 600, 40);
        add(bandeNoirePanel);

        // Texte "Rateflix" à gauche en rouge
        JLabel rateflixLabel = new JLabel("Rateflix");
        rateflixLabel.setFont(new Font("American Typewriter", Font.BOLD, 20));
        rateflixLabel.setForeground(Color.RED);
        bandeNoirePanel.add(rateflixLabel);

        // Espace entre Rateflix et Accueil
        JLabel separatorLabel = new JLabel("   ");
        bandeNoirePanel.add(separatorLabel);

        // Texte "Accueil" à côté en blanc et gras
        JLabel accueilLabel = new JLabel("Accueil");
        accueilLabel.setFont(new Font("American Typewriter", Font.BOLD, 12));
        accueilLabel.setForeground(Color.WHITE);
        bandeNoirePanel.add(accueilLabel);

        // Ajouter un trait blanc en dessous de la bande noire
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.WHITE);
        separator.setBounds(0, 40, 600, 2);
        add(separator);

        // Panneau principal avec BoxLayout pour centrer verticalement
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(0, 42, 600, 358);

        // Appel à la méthode initButton()
        this.initButton(mainPanel);
        mainPanel.add(Box.createVerticalGlue()); // Espace vertical en haut
        mainPanel.add(Box.createVerticalGlue()); // Espace vertical en bas
        add(mainPanel);
    }

    private void initButton(JPanel mainPanel) {
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        centerPanel.setBackground(Color.BLACK);

        JButton gestionProfilsButton = new JButton(new ChangerPanneau(this.mainFrame, "Gestion des profils", "PROFILS",null));
        gestionProfilsButton.setText("Gestion des profils");
        gestionProfilsButton.setForeground(Color.BLACK);
        gestionProfilsButton.setBackground(Color.WHITE);
        centerPanel.add(gestionProfilsButton);

        JButton gestionFilmsButton = new JButton(new ChangerPanneau(this.mainFrame, "Gestion des films", "FILMS",null));
        gestionFilmsButton.setText("Gestion des films");
        gestionFilmsButton.setForeground(Color.BLACK);
        gestionFilmsButton.setBackground(Color.WHITE);
        centerPanel.add(gestionFilmsButton);

        // Ajouter le centerPanel au mainPanel
        mainPanel.add(centerPanel);
    }
}
