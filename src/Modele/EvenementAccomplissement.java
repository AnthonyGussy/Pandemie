package Modele;

import Enumerations.DepartementNom;

public class EvenementAccomplissement extends Evenement implements java.io.Serializable {
	
	private int point; // Calcul� en fonction de la t�che

    public EvenementAccomplissement(Modele.Tache tache){

        point = (int)(Math.random()*(tache.getTempsInitial()/10))+1;

    }

    public void appliquerEffet(Modele.Jeu jeu){ jeu.setPtsCompetence(point); }
    
    public int getPoint() { return point; }
   
}
