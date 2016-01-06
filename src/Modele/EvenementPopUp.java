package Modele;

import java.util.Random;

public class EvenementPopUp extends Evenement implements java.io.Serializable {

    private int duree;
    private int point;
    private Vue.EvenementPopUp event;

    public EvenementPopUp(Modele.Departement departement,Modele.Jeu jeu){

        super(departement);
        duree = 10;
        point = (int)(Math.random()*2)+1;
        event = new Vue.EvenementPopUp(this,jeu);

    }

    public void appliquerEffet(Modele.Jeu jeu){ jeu.setPtsCompetence(point); }

    public Vue.EvenementPopUp getVue() { return event;}
    
    public int getDuree() { return duree; }

}
