package Vue;


import Constantes.Constantes;
import Enumerations.CompteurType;
import Modele.Jeu;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Barre extends Compteur implements java.io.Serializable {
    private Rectangle barre;
    private Rectangle progression;
    private Text texte;
    public Barre(int c, int vMax, CompteurType t, Modele.Jeu jeu){
        super(c, vMax, t);
        texte = new Text("Avancement du projet :");
        barre = new Rectangle();
        barre.setFill(Color.DARKGRAY);
        progression = new Rectangle();
        progression.setFill(Color.ALICEBLUE);
        jeu.getVue().getRoot().getChildren().addAll(barre, progression, texte);
    }

    public void affichage(Scene scene, int afficher) {
        switch(afficher) {
            case 0:
                affichage(scene, 2);
                barre.setVisible(true);
                progression.setVisible(true);
                texte.setVisible(true);
                break;
            case 1:
                barre.setVisible(false);
                progression.setVisible(false);
                texte.setVisible(false);
                break;
            default:
                barre.setX(scene.getWidth() * Constantes.POS_X_PROGRESSION);
                barre.setY(scene.getHeight() * Constantes.POS_Y_PROGRESSION);
                barre.setWidth(scene.getWidth() *  Constantes.LARGEUR_PROGRESSION);
                barre.setHeight(scene.getHeight() * Constantes.HAUTEUR_PROGRESSION);
                progression.setX(scene.getWidth() * Constantes.POS_X_PROGRESSION + 2);
                progression.setY(scene.getHeight() * Constantes.POS_Y_PROGRESSION + 2);
                progression.setWidth((double)(valeurMax - compte) / valeurMax * barre.getWidth());
                progression.setHeight(scene.getHeight() * Constantes.HAUTEUR_PROGRESSION - 4);
                texte.setX(scene.getWidth() * Constantes.POS_X_TEXTE_PROGRESSION);
                texte.setY(scene.getHeight() * Constantes.POS_Y_TEXTE_PROGRESSION);
                texte.setFont(Font.loadFont("file:Font.ttf", Constantes.TAILLE_POLICE * scene.getHeight()));
        }
    }
}
