package Modele;

import Enumerations.DepartementNom;

class Evenement {
    DepartementNom departement;
    
    public Evenement() {
    	this.departement = null;
    }
    
    public Evenement(DepartementNom departement) {
    	this.departement = departement;
    }
    
    void affichage(){
    	System.out.println("lieu : "+departement);
    }
}
