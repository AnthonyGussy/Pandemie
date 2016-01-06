package Modele;

import Enumerations.DepartementNom;
import Vue.*;
import Vue.EvenementArticle;

public class EvenementAccomplissement extends Evenement implements java.io.Serializable {
	
	private int point; // Calcul� en fonction de la t�che
    private Vue.EvenementArticle event;

    public EvenementAccomplissement(Modele.Departement departement, Modele.Tache tache,Modele.Jeu jeu){

        super(departement);
        point = (int)(Math.random()*(tache.getTempsInitial()/10))+1;
        event = new EvenementArticle(this,jeu);
        appliquerEffet(jeu);

    }

    public void appliquerEffet(Modele.Jeu jeu){ jeu.setPtsCompetence(point); }
    
    public int getPoint() { return point; }

    public Vue.EvenementArticle getVue() { return event; }
   
}
