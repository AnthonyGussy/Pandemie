package Modele;

import Enumerations.DepartementNom;

public class EvenementAccomplissement extends Evenement implements java.io.Serializable {
	
	private int effet; // Calcul� en fonction de la t�che

    //public EvenementAccomplissement()
    
    public void appliquerEffet(){}
    
    public int getEffet() { return effet; }
   
}
