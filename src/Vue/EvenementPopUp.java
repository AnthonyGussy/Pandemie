package Vue;

import Constantes.Constantes;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * Created by Anthony on 06/01/2016.
 */
public class EvenementPopUp {

    private double posX;
    private double posY;
    protected Circle popUp;
    protected Modele.EvenementPopUp event;

    public EvenementPopUp(Modele.EvenementPopUp modele, Modele.Jeu jeu) {
        event = modele;

        switch (event.getDepartement().getNom()) {
            case "Informatique":
                posX = Constantes.POS_X_INFO;
                posY = Constantes.POS_Y_INFO;
                break;
            case "Energie":
                posX = Constantes.POS_X_ENERGIE;
                posY = Constantes.POS_Y_ENERGIE;
                break;
            case "Imsi":
                posX = Constantes.POS_X_IMSI;
                posY = Constantes.POS_Y_IMSI;
                break;
            case "Gmc":
                posX = Constantes.POS_X_GMC;
                posY = Constantes.POS_Y_GMC;
                break;
            default:
                posX = Constantes.POS_X_EDIM;
                posY = Constantes.POS_Y_EDIM;

        }

        popUp = new Circle();
        popUp.setFill(new ImagePattern(new Image("file:image\\PopUp.png"), 0, 0, 1, 1, true));
        Platform.runLater(() -> jeu.getVue().getRoot().getChildren().addAll(popUp));
    }

    public void affichage(Modele.Jeu jeu) {

        Scene scene = jeu.getVue().getScene();
        popUp.setRadius(22);
        popUp.setCenterX(scene.getWidth() * posX);
        popUp.setCenterY(scene.getHeight() * posY);
        popUp.setOnMouseClicked(event1 -> {
            event.appliquerEffet(jeu);
            jeu.getVue().getRoot().getChildren().removeAll(popUp);
        });

    }
}
