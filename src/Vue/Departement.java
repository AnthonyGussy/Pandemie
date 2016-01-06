package Vue;

import Constantes.Constantes;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

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
    private Modele.Departement departement;
    private ImagePattern pointInfecte;
    private ImagePattern pointNormal;

    public Departement(Modele.Departement departement, Modele.Jeu jeu){
        this.departement = departement;
        departementPoly = new Polygon();
        switch(departement.getNom()) {
            case "Informatique":
                posX = Constantes.POS_X_INFO;
                posY = Constantes.POS_Y_INFO;
                departementPoly.getPoints().addAll(Constantes.POLYGONE_INFO);
                break;
            case "Energie":
                posX = Constantes.POS_X_ENERGIE;
                posY = Constantes.POS_Y_ENERGIE;
                departementPoly.getPoints().addAll(Constantes.POLYGONE_ENERGIE);
                break;
            case "Imsi":
                posX = Constantes.POS_X_IMSI;
                posY = Constantes.POS_Y_IMSI;
                departementPoly.getPoints().addAll(Constantes.POLYGONE_IMSI);
                break;
            case "Gmc":
                posX = Constantes.POS_X_GMC;
                posY = Constantes.POS_Y_GMC;
                departementPoly.getPoints().addAll(Constantes.POLYGONE_GMC);
                break;
            default:
                posX = Constantes.POS_X_EDIM;
                posY = Constantes.POS_Y_EDIM;
                departementPoly.getPoints().addAll(Constantes.POLYGONE_EDIM);
        }
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

    public void affichage(Scene scene, int afficher) {
        switch(afficher) {
            case 0:
                personne.setVisible(true);
                information.setVisible(true);
                nomR.setVisible(true);
                personne.getChildren().clear();
                departementPoly.setTranslateX(scene.getWidth() * posX);
                departementPoly.setTranslateY(scene.getHeight() * posY);
                personne.getChildren().add(departementPoly);
                personne.getChildren().add(genePoint(scene));
                break;
            default:
                personne.setVisible(false);
                information.setVisible(false);
                nomR.setVisible(false);
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
        jeu.getVue().affichagePlateau(2);
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
        information.setVisible(false);
        nomR.setVisible(false);
        String nom = departement.getNom();
        departementPoly.setFill(new ImagePattern(new Image("file:image\\" + nom + "Dep.jpg"), 0, 0, 1, 1, true));
        jeu.getVue().affichagePlateau(0);
    }
    private void eventArbreDeCompetence(Modele.Jeu jeu){
        jeu.getVue().affichagePlateau(1);
        departement.getArbre().getVue().affichage(jeu, 0);
    }
}
