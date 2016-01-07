package Modele;

import javafx.application.Platform;

public class Evenement implements java.io.Serializable {

    protected Vue.EvenementArticle event;

    protected String nom;
    protected String description;
    protected int duree;

    public Evenement(String nom, String description,Modele.Jeu jeu) {
        this.nom = nom;
        this.description = description;
        duree = 15;
        //event = new Vue.EvenementArticle(this, jeu);
    }


    public Vue.EvenementArticle getVue() { return event; }

    public int getDuree() { return duree;}

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public boolean isAccomplissement(){ return false;}

    public void setDuree(Modele.Jeu jeu){
        --duree;
        if(duree == 0) Platform.runLater(() -> event.affichage(jeu, 1));
    }
}
