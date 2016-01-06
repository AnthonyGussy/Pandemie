package Modele;

import java.util.Random;

public class EvenementPopUp extends Evenement implements java.io.Serializable {

    private int duree;
    private int point;

    public EvenementPopUp(){

        duree = 10;
        point = (int)(Math.random()*2)+1;

    }

    public void appliquerEffet(Modele.Jeu jeu){ jeu.setPtsCompetence(point); }
    
    public int getDuree() { return duree; }

}
