package Modele;

import Enumerations.DepartementNom;

/**
 * Created by Victor on 04/12/2015.
 */
public class EvenementAccomplissement extends Evenement {
	
	private int effet; // Calcul� en fonction de la t�che
	
    public void affichage(){
    	super.affichage();
    }
    
    public void appliquerEffet(){}
    
    public int getEffet() { return effet; }
   
}
