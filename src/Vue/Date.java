package Vue;

import Enumerations.CompteurType;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.util.concurrent.TimeUnit;

public class Date extends Compteur implements java.io.Serializable {
    Text text;
    public Date(int c, CompteurType t){
        super(c, t);
    }
    public Date(int c, int vMax, CompteurType t, Vue.Jeu jeu){
        super(c, vMax, t);
        text = new Text("");
        text.setVisible(false);
        jeu.getRoot().getChildren().add(text);
    }
    public void affichage(Scene scene, int afficher){
        switch(afficher) {
            case 0:
                affichage(scene, 2);
                text.setVisible(true);
                break;
            case 1:
                text.setVisible(false);
                break;
            default:
/*                int day = (int) TimeUnit.SECONDS.toDays(compte);
                long hours = TimeUnit.SECONDS.toHours(compte) - (day * 24);
                long minute = TimeUnit.SECONDS.toMinutes(compte) - (TimeUnit.SECONDS.toHours(compte) * 60);
                long second = TimeUnit.SECONDS.toSeconds(compte) - (TimeUnit.SECONDS.toMinutes(compte) * 60);
                text.setText(day + ":" + hours + ":" + minute + ":" + second);*/
        }
    }
}
