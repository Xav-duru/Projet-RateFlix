package Java.Controleur;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;

import Java.Item.ItemProfil;
import Java.Vue.MainFrame;

public class ChangerPanneau extends AbstractAction {

    private MainFrame mainFrame;
    private String nouveauPanneau;

    private ItemProfil itemProfil;

    public ChangerPanneau(MainFrame mainJFrame, String nom, String nouveauPanneau, ItemProfil itemProfil) {
        super(nom);
        this.mainFrame = mainJFrame;
        this.nouveauPanneau = nouveauPanneau;
        this.itemProfil = itemProfil;
    }

    
    @Override
    public void actionPerformed(ActionEvent event) {
        try {
            this.mainFrame.setContentPane(this.nouveauPanneau, this.itemProfil);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}

