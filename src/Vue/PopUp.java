package Vue;

import Constantes.Constantes;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 *
 */
public class PopUp {

    private double posX;
    private double posY;
    private double randomX;
    private double randomY;
    protected Circle popUp;
    protected Modele.PopUp modele;

    public PopUp(Modele.PopUp modele, Modele.Jeu jeu) {
        this.modele = modele;
        switch (this.modele.getDepartement().getNom()) {
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
        Platform.runLater(() -> jeu.getVue().getRoot().getChildren().add(popUp));
    }

    public void affichage(Modele.Jeu jeu,int affichage) {
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        switch (affichage){
            case 0:
                popUp.setRadius(scene.getWidth() * Constantes.RAYON);
                popUp.setOnMouseClicked(event1 -> {
                    modele.appliquerEffet(jeu);
                    root.getChildren().remove(popUp);
                });
                break;
            case 1:
                root.getChildren().remove(popUp);
                break;
            default:
                popUp.setCenterX(scene.getWidth() * posX + randomX * modele.getDepartement().getVue().getDepartementPoly().getLayoutBounds().getWidth());
                popUp.setCenterY(scene.getHeight() * posY + randomY * modele.getDepartement().getVue().getDepartementPoly().getLayoutBounds().getHeight());
        }


    }

    public void genePopUp(Scene scene) {
        do {
            randomX = Math.random();
            randomY = Math.random();
            popUp.setCenterX(scene.getWidth() * posX + randomX * modele.getDepartement().getVue().getDepartementPoly().getLayoutBounds().getWidth());
            popUp.setCenterY(scene.getHeight() * posY + randomY * modele.getDepartement().getVue().getDepartementPoly().getLayoutBounds().getHeight());
        }
        while (!modele.getDepartement().getVue().getDepartementPoly().contains(popUp.getCenterX() - scene.getWidth() * posX, popUp.getCenterY() - scene.getHeight() * posY));
    }
}
