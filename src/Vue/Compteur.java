package Vue;

import Enumerations.CompteurType;
import Modele.Jeu;
import javafx.scene.text.Text;

public class Compteur implements java.io.Serializable {
    int compte;
    int valeurMax;
    Text text;
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
    public void affichage(int x, int y){
        text = new Text(String.valueOf(compte));
        //text.setFill(Color.AQUA);
        text.setX(x);
        text.setY(y);
        Jeu.root.getChildren().add(text);
    }
    public void affichage(){
        Jeu.root.getChildren().remove(text);
    }

    public void modifCompte(int valeur){
        compte += valeur;
        if(compte < 0) compte = 0;
        else if(compte > valeurMax) compte = valeurMax;
    }

    public int getCompte(){ return compte; }

}
