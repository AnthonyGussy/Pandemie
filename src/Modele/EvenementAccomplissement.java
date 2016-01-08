package Modele;

public class EvenementAccomplissement extends Evenement implements java.io.Serializable {
	
	private int point; // Calcul� en fonction de la t�che


    public EvenementAccomplissement(Modele.Departement departement, Modele.Tache tache, Modele.Jeu jeu){

        super("Tâche terminée !","Une tâche a été terminée dans le département : "+departement.getNom(),jeu);
        point = (int)(Math.random()*(tache.getTempsInitial()/10))+1;
        appliquerEffet(jeu,tache);
        event = new Vue.Evenement(this,jeu);

    }

    public void appliquerEffet(Modele.Jeu jeu, Modele.Tache tache){

        jeu.setPtsCompetence(point);
        jeu.getDepartements().get(0).getTaches().get(0).setAvancement(-tache.getTempsInitial());

    }
    
    public int getPoint() { return point; }

    public boolean isAccomplissement(){ return true;}
   
}
