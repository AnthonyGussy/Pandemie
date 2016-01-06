package Modele;

import Enumerations.DepartementNom;

public class Evenement implements java.io.Serializable {
    protected Modele.Departement departement;
    
    public Evenement() {
    	this.departement = null;
    }
    
    public Evenement(Modele.Departement departement) {
    	this.departement = departement;
    }

    public Modele.Departement getDepartement() {return departement;}
}
