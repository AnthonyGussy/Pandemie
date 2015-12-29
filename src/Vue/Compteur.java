package Vue;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Compteur {
    int compte;
    int valeurMax;
    Text text;
    Enumerations.CompteurType type;
    Compteur(){}
    Compteur(int c, Enumerations.CompteurType t){
        compte = c;
        valeurMax = Integer.MAX_VALUE;
        type = t;
    }
    Compteur(int c, int vMax, Enumerations.CompteurType t){
        compte = c;
        valeurMax = vMax;
        type = t;
    }
    void affichage(int x, int y){
        text = new Text(String.valueOf(compte));
        text.setFill(Color.AQUA);
        text.setX(x);
        text.setY(y);
        Jeu.root.getChildren().add(text);
    }
    void affichage(){
        Jeu.root.getChildren().remove(text);
    }
    void modifCompte(int valeur){
        compte += valeur;
        if(valeur < 0) valeur = 0;
        else if(valeur > valeurMax) valeur = valeurMax;
    }
    int getCompte(){ return compte; }

}
