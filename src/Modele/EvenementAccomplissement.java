package Modele;

public class EvenementAccomplissement extends Evenement implements java.io.Serializable {
	
	private int point; // Calculé en fonction de la tâche


    public EvenementAccomplissement(Modele.Departement departement, Modele.Tache tache, Modele.Jeu jeu){

        super("Tâche terminée !","Une tâche a été terminée dans le département : "+departement.getNom(),jeu);
        point = (int)(Math.random()*(tache.getTempsInitial()/10))+1;
        appliquerEffet(jeu);
        event = new Vue.Evenement(this, jeu);
    }

    public void appliquerEffet(Modele.Jeu jeu){ jeu.setPtsCompetence(point); }
    
    public int getPoint() { return point; }

    public boolean isAccomplissement(){ return true;}
   
}
