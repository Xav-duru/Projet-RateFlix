package Java.Vue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Java.BDD.*;
import Java.Controleur.ChangerPanneau;

public class Connection extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private MainFrame mainFrame;
    private JButton loginButton; 

    public Connection(MainFrame mainFrame) {
        //this.mainFrame = mainFrame;

        // Utiliser un null layout
        setLayout(null);

        // Bande noire avec texte "Rateflix"
        JPanel bandeNoirePanel = new JPanel();
        bandeNoirePanel.setBackground(Color.BLACK);
        bandeNoirePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Texte "Rateflix" en rouge en haut à gauche
        JLabel rateflixLabel = new JLabel("Rateflix");
        rateflixLabel.setFont(new Font("American Typewriter", Font.BOLD, 20));
        rateflixLabel.setForeground(Color.RED);
        bandeNoirePanel.add(rateflixLabel);

        // Positionner la bande noire en haut de la fenêtre avec toute la largeur
        bandeNoirePanel.setBounds(0, 0, 600, 40);
        add(bandeNoirePanel);

        // Positionner les autres composants
        JLabel titleLabel = new JLabel("Connexion");
        titleLabel.setFont(new Font("American Typewriter", Font.BOLD, 24));
        titleLabel.setBounds(235, 90, 200, 30);
        add(titleLabel);

        JLabel adminLabel = new JLabel("Partie administrateur");
        adminLabel.setFont(new Font("American Typewriter Semibold", Font.ITALIC, 12));
        adminLabel.setForeground(Color.BLACK);
        adminLabel.setBounds(238, 120, 200, 20);
        add(adminLabel);

        JLabel usernameLabel = new JLabel("Nom d'utilisateur :");
        usernameLabel.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        usernameLabel.setForeground(Color.BLACK);
        usernameLabel.setBounds(185, 180, 150, 20);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 25));
        usernameField.setBounds(300, 180, 150, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setFont(new Font("American Typewriter", Font.PLAIN, 13));
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBounds(185, 210, 150, 20);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        passwordField.setBounds(300, 210, 150, 25);
        add(passwordField);

        loginButton = new JButton("Connexion");
        loginButton.setBounds(200, 250, 200, 30);
        add(loginButton);

        initButton(mainFrame);

        // JButton loginButton = new JButton("Connexion");
        // loginButton.setBounds(200, 250, 200, 30);
        // add(loginButton);
        // loginButton.addActionListener(new ActionListener() {
        //     private Object mainFrame;

        //     @Override
        //     // public void actionPerformed(ActionEvent e) {
            //     String username = usernameField.getText();
            //     char[] password = passwordField.getPassword();

            //     try {
            //         String password_String = String.valueOf(password);
            //         if (RequeteConnexion.verifierAdmin(username, password_String)) {
            //             JOptionPane.showMessageDialog(null, "Login successful!");
            //             new ChangerPanneau(this.mainFrame, "accueil", "ACCUEIL");
            //         } else {
            //             JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            //         }
            //     } catch (Exception ex) {
            //         JOptionPane.showMessageDialog(null, "Error reading data from the database.");
            //         ex.printStackTrace(); // Affichez les détails de l'exception dans la console (facultatif)
            //     }

            //     // Efface les champs après la tentative de connexion
            //     usernameField.setText("");
            //     passwordField.setText("");
            // }
        }

        // JLabel forgotLabel = new JLabel("Identifiants oubliés?");
        // forgotLabel.setFont(new Font("American Typewriter Semibold", Font.ITALIC, 12));
        // forgotLabel.setForeground(Color.BLACK);
        // forgotLabel.setBounds(242, 280, 200, 20);
        // add(forgotLabel);
    

    private void initButton(MainFrame mainFrame) {
        loginButton.addActionListener(new ActionListener() {
            //private MainFrame mainFrame;

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();

                try {
                    String password_String = String.valueOf(password);
                    if (RequeteConnexion.verifierAdmin(username, password_String)) {
                        //JOptionPane.showMessageDialog(null, "Login successful!");
                        ChangerPanneau nouveauPanneau = new ChangerPanneau(mainFrame, "accueil", "ACCUEIL",null);
                        nouveauPanneau.actionPerformed(e);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error reading data from the database.");
                    ex.printStackTrace(); // Affichez les détails de l'exception dans la console (facultatif)
                }

                // Efface les champs après la tentative de connexion
                usernameField.setText("");
                passwordField.setText("");
            }
        });
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

