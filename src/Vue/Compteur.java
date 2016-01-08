package Vue;

import Enumerations.CompteurType;
import javafx.scene.text.Text;

public class Compteur implements java.io.Serializable {
    int compte;
    int valeurMax;
    transient Text text;
    CompteurType type;
    public Compteur(int c, CompteurType t){
        compte = c;
        valeurMax = Integer.MAX_VALUE;
        type = t;
    }
    public Compteur(int c, int vMax, CompteurType t){
        compte = c;
        valeurMax = vMax;
        type = t;
    }
    public void affichage(int x, int y, Modele.Jeu jeu){
        text = new Text(String.valueOf(compte));
        text.setX(x);
        text.setY(y);
        jeu.getVue().getRoot().getChildren().add(text);
    }
    public void affichage(Modele.Jeu jeu){
        jeu.getVue().getRoot().getChildren().remove(text);
    }

    public void modifCompte(int valeur){
        compte += valeur;
        if(compte < 0) compte = 0;
        else if(compte > valeurMax) compte = valeurMax;
    }

    public void setCompte(int c) { compte = c; }

    public int getCompte(){ return compte; }

}
