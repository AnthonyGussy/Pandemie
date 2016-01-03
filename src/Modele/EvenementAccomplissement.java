package Modele;

import Enumerations.DepartementNom;

public class EvenementAccomplissement extends Evenement implements java.io.Serializable {
	
	private int effet; // Calculé en fonction de la tâche
	
    public void affichage(){
    	super.affichage();
    }
    
    public void appliquerEffet(){}
    
    public int getEffet() { return effet; }
   
}
