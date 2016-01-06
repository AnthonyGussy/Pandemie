package Vue;

import Constantes.Constantes;
import Modele.Tache;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 *
 */
public class Departement{
    private Text information;
    private Text nomR;
    private Polygon departementPoly;
    private Group personne;
    private double posX;
    private double posY;
    ArrayList<Double> polygone;
    private Modele.Departement departement;
    private ImagePattern pointInfecte;
    private ImagePattern pointNormal;
    private boolean affiche = false;

    public Departement(Modele.Departement departement, Modele.Jeu jeu){
        this.departement = departement;
        Scene scene = jeu.getVue().getScene();
        departementPoly = new Polygon();
        switch(departement.getNom()) {
            case "Informatique":
                posX = Constantes.POS_X_INFO;
                posY = Constantes.POS_Y_INFO;
                polygone = Constantes.POLYGONE_INFO;
                break;
            case "Energie":
                posX = Constantes.POS_X_ENERGIE;
                posY = Constantes.POS_Y_ENERGIE;
                polygone = Constantes.POLYGONE_ENERGIE;
                break;
            case "Imsi":
                posX = Constantes.POS_X_IMSI;
                posY = Constantes.POS_Y_IMSI;
                polygone = Constantes.POLYGONE_IMSI;
                break;
            case "Gmc":
                posX = Constantes.POS_X_GMC;
                posY = Constantes.POS_Y_GMC;
                polygone = Constantes.POLYGONE_GMC;
                break;
            default:
                posX = Constantes.POS_X_EDIM;
                posY = Constantes.POS_Y_EDIM;
                polygone = Constantes.POLYGONE_EDIM;
        }
        departementPoly.getPoints().addAll(Constantes.adaptPolygone(polygone, scene));
        departementPoly.setFill(new ImagePattern(new Image("file:image\\" + departement.getNom() + "Dep.jpg"), 0, 0, 1, 1, true));
        information = new Text();
        nomR = new Text();
        personne = new Group();
        pointInfecte = new ImagePattern(new Image("file:image\\PointInfecte.png"));
        pointNormal = new ImagePattern(new Image("file:image\\PointNormal.png"));
        personne.setOnMouseEntered(mouseEvent -> eventInformation(jeu));
        personne.setOnMouseExited(mouseEvent -> eventRemoveInformation(jeu));
        personne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(jeu));
        personne.setVisible(false);
        information.setVisible(false);
        nomR.setVisible(false);
        jeu.getVue().getRoot().getChildren().addAll(information, nomR, personne);
    }

    public void affichage(Modele.Jeu jeu, int afficher) {
        switch(afficher) {
            case 0:
                affiche = true;
                personne.setVisible(true);
                affichage(jeu, 2);
                break;
            case 1:
                affiche = false;
                personne.setVisible(false);
                information.setVisible(false);
                nomR.setVisible(false);
                break;
            default:
                personne.getChildren().clear();
                departementPoly.getPoints().clear();
                departementPoly.getPoints().addAll(Constantes.adaptPolygone(polygone, jeu.getVue().getScene()));
                departementPoly.setTranslateX(jeu.getVue().getScene().getWidth() * posX);
                departementPoly.setTranslateY(jeu.getVue().getScene().getHeight() * posY);
                personne.getChildren().add(departementPoly);
                personne.getChildren().add(genePoint(jeu.getVue().getScene()));
                for(Tache t: departement.getTaches()) {
                    if (t.getEvent() != null) {

                        t.getEvent().getVue().affichage(jeu,2);

                    }
                }
        }
    }
    public Group genePoint(Scene scene) {
        Group depPersonne = new Group();
        for (int i = 0; i < departement.getNbPersonne(); i++) {
            Circle circle = new Circle();
            circle.setRadius(4);
            if (i < departement.getNbActif()) {
                circle.setFill(pointInfecte);
            } else {
                circle.setFill(pointNormal);
            }
            do {
                circle.setCenterX(scene.getWidth() * posX + Math.random() * departementPoly.getLayoutBounds().getWidth());
                circle.setCenterY(scene.getHeight() * posY + Math.random() * departementPoly.getLayoutBounds().getHeight());
            } while(!departementPoly.contains(circle.getCenterX() - scene.getWidth() * posX, circle.getCenterY() - scene.getHeight() * posY));
            if(depPersonne.getChildren().contains(circle)) depPersonne.getChildren().remove(circle);
            depPersonne.getChildren().add(circle);
        }
        return depPersonne;
    }

    private void eventInformation(Modele.Jeu jeu){
        jeu.getVue().affichagePlateau(4);
        Scene scene = jeu.getVue().getScene();
        String nom = departement.getNom();
        nomR.setText(nom);
        nomR.setX(scene.getWidth() * Constantes.POS_X_NOM_DEP);
        nomR.setY(scene.getHeight() * Constantes.POS_Y_NOM_DEP);
        nomR.setFont(Font.loadFont("file:Font.ttf", scene.getWidth() * Constantes.TAILLE_POLICE));
        int efficacite = departement.getEfficacite();
        int moral = departement.getMoral();
        int taches = departement.getNbTaches();
        int infecte = departement.getNbActif();
        information.setText("Efficacité : " + Integer.toString(efficacite) + "%\nMoral : " + Integer.toString(moral) + "%\n" +
                "Nb tâches : " + Integer.toString(taches) + "\n" + "Nb actifs : " + Integer.toString(infecte));
        information.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE));
        information.setX(scene.getWidth() * Constantes.POS_X_INFOS);
        information.setY(scene.getHeight() * Constantes.POS_Y_INFOS);
        departementPoly.setFill(new ImagePattern(new Image("file:image\\"+ nom +"DepSelec.jpg"), 0, 0, 1, 1, true));
        information.setVisible(true);
        nomR.setVisible(true);
    }
    private void eventRemoveInformation(Modele.Jeu jeu){
        String nom = departement.getNom();
        departementPoly.setFill(new ImagePattern(new Image("file:image\\" + nom + "Dep.jpg"), 0, 0, 1, 1, true));
        if (affiche) {
            information.setVisible(false);
            nomR.setVisible(false);
            jeu.getVue().affichagePlateau(2);
        }
    }
    private void eventArbreDeCompetence(Modele.Jeu jeu){
        jeu.getVue().affichagePlateau(1);
        departement.getArbre().getVue().affichage(jeu, 0);
    }
}
