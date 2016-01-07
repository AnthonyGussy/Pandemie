package Modele;

import javafx.application.Platform;

public class PopUp implements java.io.Serializable {

    private int duree;
    private int point;
    private Vue.PopUp event;
    private Modele.Departement departement;

    public PopUp(Modele.Departement depart, Modele.Jeu jeu){

        departement = depart;
        duree = 20;
        point = (int)(Math.random()*2)+1;
        event = new Vue.PopUp(this,jeu);

    }

    public void appliquerEffet(Modele.Jeu jeu){ jeu.setPtsCompetence(point); }

    public Vue.PopUp getVue() { return event;}

    public void setDuree(Modele.Jeu jeu){
        --duree;
        if(duree == 0) Platform.runLater(() -> event.affichage(jeu, 1));
    }
    
    public int getDuree() { return duree; }

    public Modele.Departement getDepartement() { return departement;}

}
