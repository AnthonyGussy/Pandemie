package Modele;

import Constantes.Constantes;
import Enumerations.DepartementNom;

public class Evenement implements java.io.Serializable {

    protected Modele.Departement departement;
    protected Vue.EvenementArticle event;

    protected String nom;
    protected String description;
    protected int duree;

    public Evenement() {
    	this.departement = null;
    }

    public Evenement(Modele.Departement departement,String nom,String description,Modele.Jeu jeu) {

    	this.departement = departement;
        this.nom = nom;
        this.description = description;
        duree = 10;
        event = new Vue.EvenementArticle(this,jeu);

    }

    public Modele.Departement getDepartement() {return departement;}

    public Vue.EvenementArticle getVue() { return event; }

    public int getDuree() { return duree;}

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isAccomplissement(){ return false;}
}
