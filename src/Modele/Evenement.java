package Modele;

import Enumerations.DepartementNom;

class Evenement implements java.io.Serializable {
    protected DepartementNom departement;
    
    public Evenement() {
    	this.departement = null;
    }
    
    public Evenement(DepartementNom departement) {
    	this.departement = departement;
    }
    
    public void affichage(){
    	System.out.println("lieu : "+departement);
    }
    
    public DepartementNom getDepartement() { return departement; }
}
