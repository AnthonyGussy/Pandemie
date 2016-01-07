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
public class PopUp {

    private double posX;
    private double posY;
    protected Circle popUp;
    protected Modele.PopUp event;

    public PopUp(Modele.PopUp modele, Modele.Jeu jeu) {

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
        genePopUp(jeu.getVue().getScene());
        Platform.runLater(() -> jeu.getVue().getRoot().getChildren().addAll(popUp));
    }

    public void affichage(Modele.Jeu jeu,int affichage) {

        switch (affichage){
            case 0:
                popUp.setRadius(22);
                popUp.setOnMouseClicked(event1 -> {
                    event.appliquerEffet(jeu);
                    jeu.getVue().getRoot().getChildren().removeAll(popUp);
                    jeu.setPopUp(false);
                });
                break;
            case 1:
                jeu.getVue().getRoot().getChildren().removeAll(popUp);
                break;
            default:
                genePopUp(jeu.getVue().getScene());

        }


    }

    public void genePopUp(Scene scene) {
        do {
            popUp.setCenterX(scene.getWidth() * posX + Math.random() * event.getDepartement().getVue().getDepartementPoly().getLayoutBounds().getWidth());
            popUp.setCenterY(scene.getHeight() * posY + Math.random() * event.getDepartement().getVue().getDepartementPoly().getLayoutBounds().getHeight());
        }
        while (!event.getDepartement().getVue().getDepartementPoly().contains(popUp.getCenterX() - scene.getWidth() * posX, popUp.getCenterY() - scene.getHeight() * posY));

    }
}
