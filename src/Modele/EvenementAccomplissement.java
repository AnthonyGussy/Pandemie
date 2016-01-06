package Modele;

import Vue.EvenementArticle;

public class EvenementAccomplissement extends Evenement implements java.io.Serializable {
	
	private int point; // Calcul� en fonction de la t�che


    public EvenementAccomplissement(Modele.Departement departement, Modele.Tache tache,Modele.Jeu jeu){

        super(departement,"T�che termin�e !","Une t�che a �t� termin�e dans le d�partement : "+departement.getNom(),jeu);
        point = (int)(Math.random()*(tache.getTempsInitial()/10))+1;
        appliquerEffet(jeu);

    }

    public void appliquerEffet(Modele.Jeu jeu){ jeu.setPtsCompetence(point); }
    
    public int getPoint() { return point; }

    public boolean isAccomplissement(){ return true;}
   
}
