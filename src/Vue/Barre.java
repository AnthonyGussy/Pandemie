package Vue;


import Enumerations.CompteurType;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Barre extends Compteur {
    Barre(int c, CompteurType t){
        super(c, t);
    }
    Barre(int c, int vMax, CompteurType t){
        super(c, vMax, t);
    }
    Group g;
    final int largeur = 100;
    final int hauteur = 10;
    public void affichage(int x, int y){
        Rectangle barre = new Rectangle(x, y, largeur, hauteur);
        barre.setFill(Color.DARKGRAY);
        Rectangle progression = new Rectangle(x + 1, y + 1, ((float)compte/valeurMax)*(largeur-2), hauteur-2);
        progression.setFill(Color.ALICEBLUE);
        g = new Group();
        g.getChildren().add(barre);
        g.getChildren().add(progression);
        Jeu.root.getChildren().add(g);
    }
    public void affichage(){
        Jeu.root.getChildren().remove(g);
    }
}
