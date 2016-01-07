package Modele;

import javafx.application.Platform;

public abstract class Evenement implements java.io.Serializable {

    protected Vue.Evenement event;

    protected String nom;
    protected String description;
    protected int duree;

    public Evenement(String nom, String description, Modele.Jeu jeu) {
        this.nom = nom;
        this.description = description;
        duree = 10;
    }

    public Vue.Evenement getVue() { return event; }

    public int getDuree() { return duree;}

    public void setDuree(int duree) { this.duree = duree; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public void diminuerTemps(Modele.Jeu jeu){
        --duree;
        if(duree == 0) Platform.runLater(() -> event.affichage(jeu, 1));
    }
}
