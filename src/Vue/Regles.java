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
    private Polygon flecheOmbre;
    private double[] posFleche;
    private double[] posRetour;
    private ImagePattern page;
    private int pageActive;
    private Scene scene;
    private Modele.Jeu jeu;
    private boolean affiche;
    private Polygon retour;

    public Regles(Modele.Jeu jeu){
        fleche = new Polygon();
        flecheOmbre = new Polygon() ;
        retour = new Polygon();
        posFleche = new double[2];
        posRetour = new double[2];
        this.jeu = jeu;
        retour.getPoints().addAll(Constantes.POLYGONE_RETOUR);
        retour.setFill(new ImagePattern(new Image("file:image\\RetourMenu.jpg"), 0, 0, 1, 1, true));
        posRetour[0]=Constantes.POS_X_RETOUR;
        posRetour[1]=Constantes.POS_Y_RETOUR;

        retour.setOnMouseEntered(mouseEvent -> eventRetour(1));
        retour.setOnMouseExited(mouseEvent -> eventRetour(2));
        retour.setOnMouseClicked(mouseEvent -> eventRetour(3));
        fleche.setOnMouseEntered(mouseEvent -> eventMouvement(1));
        fleche.setOnMouseExited(mouseEvent -> eventMouvement(2));
        fleche.setOnMouseClicked(mouseEvent -> eventMouvement(3));
        flecheOmbre.setOnMouseEntered(mouseEvent -> eventMouvement(1));
        flecheOmbre.setOnMouseExited(mouseEvent -> eventMouvement(2));
        flecheOmbre.setOnMouseClicked(mouseEvent -> eventMouvement(3));

        fleche.setVisible(false);
        flecheOmbre.setVisible(false);
        retour.setVisible(false);
        scene = jeu.getVue().getScene();
        jeu.getVue().getRoot().getChildren().addAll(flecheOmbre, fleche, retour);
    }
    private void eventRetour(int event){
        switch (event){
            case 1:
                retour.setFill(new ImagePattern(new Image("file:image\\RetourMenuActif.jpg"), 0, 0, 1, 1, true));
                break;
            case 2:
                retour.setFill(new ImagePattern(new Image("file:image\\RetourMenu.jpg"), 0, 0, 1, 1, true));
                break;
            case 3:
                affichage(jeu,1,pageActive);
                //Retour au jeu
                break;
        }
    }
    private void eventMouvement(int event){
        switch(event){
            case 1:
                flecheOmbre.setVisible(true);
                fleche.setTranslateX(scene.getWidth() * posFleche[0] +2);
                fleche.setTranslateY(scene.getHeight() * posFleche[1] -2);
                break;
            case 2 :
                flecheOmbre.setVisible(false);
                fleche.setTranslateX(scene.getWidth() * posFleche[0]);
                fleche.setTranslateY(scene.getHeight() * posFleche[1] );
                break;
            case 3 :
                switch (pageActive){
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
                        fleche.setFill(new ImagePattern(new Image("file:image\\FlecheP1.png"), 0, 0, 1, 1, true));
                        flecheOmbre.getPoints().clear();
                        flecheOmbre.getPoints().addAll(Constantes.POLYGONE_RPAGE1);
                        flecheOmbre.setFill(new ImagePattern(new Image("file:image\\FlecheP1Ombre.png"), 0, 0, 1, 1, true));
                        posFleche[0]=Constantes.POS_X_FLECHENEXTRPAGE1;
                        posFleche[1]=Constantes.POS_Y_FLECHENEXTRPAGE1;
                        pageActive = 1;
                        break;
                    case 2 :
                        page= new ImagePattern(new Image("file:image\\PandemieRegleP2.jpg"), 0, 0, 1, 1, true);
                        fleche.getPoints().clear();
                        fleche.getPoints().addAll(Constantes.POLYGONE_RPAGE2);
                        fleche.setFill(new ImagePattern(new Image("file:image\\FlecheP2.png"), 0, 0, 1, 1, true));
                        flecheOmbre.getPoints().clear();
                        flecheOmbre.getPoints().addAll(Constantes.POLYGONE_RPAGE2);
                        flecheOmbre.setFill(new ImagePattern(new Image("file:image\\FlecheP2Ombre.png"), 0, 0, 1, 1, true));
                        posFleche[0]=Constantes.POS_X_FLECHENEXTRPAGE2;
                        posFleche[1]=Constantes.POS_Y_FLECHENEXTRPAGE2;
                        pageActive = 2;
                        break;
                }
                scene.setFill(page);
                fleche.setTranslateX(scene.getWidth() * posFleche[0]);
                fleche.setTranslateY(scene.getHeight() * posFleche[1]);
                flecheOmbre.setTranslateX(scene.getWidth() * posFleche[0]);
                flecheOmbre.setTranslateY(scene.getHeight() * posFleche[1]);
                retour.setTranslateX(scene.getWidth() * posRetour[0]);
                retour.setTranslateY(scene.getHeight() * posRetour[1]);

                retour.setVisible(true);
                fleche.setVisible(true);
                break;
            case 1 :
                affiche = false;
                fleche.setVisible(false);
                retour.setVisible(false);
                break;
            default :
                fleche.setTranslateX(scene.getWidth() * posFleche[0]);
                fleche.setTranslateY(scene.getHeight() * posFleche[1]);
                break;
        }
    }
}
