package Vue;

import Constantes.Constantes;
import Enumerations.CompteurType;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Date extends Compteur implements java.io.Serializable {
    Text text;

    public Date(int c, CompteurType t, Vue.Jeu jeu){
        super(c, t);
        text = new Text("");
        text.setVisible(false);
        text.setFont(Font.loadFont("file:DJB Get Digital.ttf", jeu.getScene().getHeight() * Constantes.TAILLE_POLICE_DATE));
        text.setFill(Color.web("#3ab7e7"));
        text.setTranslateX(jeu.getScene().getWidth() * Constantes.POS_X_DATE);
        text.setTranslateY(jeu.getScene().getHeight() * Constantes.POS_Y_DATE);
        jeu.getRoot().getChildren().add(text);
    }
    public void affichage(Scene scene, int afficher) {
        switch(afficher) {
            case 0:
                text.setVisible(true);
                affichage(scene, 2);
                break;
            case 1:
                text.setVisible(false);
                break;
            default:
                int weeks = compte/24/7;
                int days = compte/24 - weeks*7 ;
                int hours = compte - 24*(days - 7*weeks);
                if(weeks<10 && hours<10)text.setText("0" + weeks + ":0"+ days + ":0" + hours);
                else if(weeks<10)text.setText("0" + weeks + ":0"+ days + ":" + hours);
                else if(hours<10) text.setText("" + weeks + ":0"+ days + ":0" + hours);
                else text.setText("" + weeks + ":0"+ days + ":" + hours);
        }
    }

    public void setCompte(int c){compte = c;}
}
