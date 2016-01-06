package Vue;

import Constantes.Constantes;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

/**
 *
 */
public class Regles {
    private Polygon fleche;
    private double[] posFleche;
    private ImagePattern page;
    private int PageActive;
    private Scene scene;
    private Modele.Jeu jeu;
    private boolean affiche;

    public Regles(Modele.Jeu jeu){
        fleche = new Polygon();
        posFleche = new double[2];
        this.jeu = jeu;
        fleche.setOnMouseEntered(mouseEvent -> eventMouvement(1));
        fleche.setOnMouseExited(mouseEvent -> eventMouvement(2));
        fleche.setOnMouseClicked(mouseEvent -> eventMouvement(3));
        fleche.setVisible(false);
        scene = jeu.getVue().getScene();
        jeu.getVue().getRoot().getChildren().addAll(fleche);
    }
    private void eventMouvement(int event){
        switch(event){
            case 1:
                fleche.setTranslateX(scene.getWidth() * posFleche[0] +2);
                fleche.setTranslateY(scene.getHeight() * posFleche[1] -2);
                break;
            case 2 :
                fleche.setTranslateX(scene.getWidth() * posFleche[0]);
                fleche.setTranslateY(scene.getHeight() * posFleche[1] );
                break;
            case 3 :
                switch (PageActive){
                    case 1 :
                        affichage(jeu,0,2);
                        break;
                    case 2 :
                        affichage(jeu,0,1);
                }
        }
    }
    public void affichage(Modele.Jeu jeu, int afficher, int pageAffiche) {
        switch (afficher) {
            case 0:
                affiche = true;
                switch(pageAffiche){
                    case 1 :
                        page= new ImagePattern(new Image("file:image\\PandemieRegleP1.jpg"), 0, 0, 1, 1, true);
                        fleche.getPoints().clear();
                        fleche.getPoints().addAll(Constantes.POLYGONE_RPAGE1);
                        fleche.setFill(new ImagePattern(new Image("file:image\\FlecheP1.jpg"), 0, 0, 1, 1, true));
                        posFleche[0]=Constantes.POS_X_FLECHENEXTRPAGE1;
                        posFleche[1]=Constantes.POS_Y_FLECHENEXTRPAGE1;
                        PageActive = 1;
                        break;
                    case 2 :
                        page= new ImagePattern(new Image("file:image\\PandemieRegleP2.jpg"), 0, 0, 1, 1, true);
                        fleche.getPoints().clear();
                        fleche.getPoints().addAll(Constantes.POLYGONE_RPAGE2);
                        fleche.setFill(new ImagePattern(new Image("file:image\\FlecheP2.jpg"), 0, 0, 1, 1, true));
                        posFleche[0]=Constantes.POS_X_FLECHENEXTRPAGE2;
                        posFleche[1]=Constantes.POS_Y_FLECHENEXTRPAGE2;
                        PageActive = 2;
                        break;
                }
                scene.setFill(page);
                fleche.setTranslateX(scene.getWidth() * posFleche[0]);
                fleche.setTranslateY(scene.getHeight() * posFleche[1]);
                fleche.setVisible(true);
                break;
            case 1 :
                affiche = false;
                fleche.setVisible(false);
                break;
        }
    }
}
