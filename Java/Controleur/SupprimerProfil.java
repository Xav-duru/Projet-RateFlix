package Java.Controleur;

import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Java.BDD.RequeteProfil;
import Java.Vue.*;
import javafx.event.ActionEvent;

public class SupprimerProfil {

    private List<Integer> listUserId;
    private PanneauProfil panneauProfil;
    private MainFrame mainFrame;
    private int number;
    
    public SupprimerProfil(MainFrame mainJFrame, PanneauProfil panneauProfil){
        this.panneauProfil = panneauProfil;
        this.mainFrame = mainFrame;
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String name = source.getName();

        listUserId = this.panneauProfil.getListNumber();
        this.number = listUserId.get(Integer.valueOf(name));

        System.out.println(number);

        RequeteProfil requeteProfil = new RequeteProfil();
        requeteProfil.supprimerAvis(this);
        JOptionPane.showMessageDialog(null, "L'avis a bien été supprimé de la base de données", "Information", JOptionPane.INFORMATION_MESSAGE);
        try {
            this.mainFrame.setContentPane("PROFILS", null);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public int getNumber() {
        return number;
    }
}
