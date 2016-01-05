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
    private boolean affiche = false;
    private Text information;
    private ImageView liste;
    private Text nomR;
    private Polygon departementPoly;
    private Group personne;
    private double posX;
    private double posY;
    Modele.Departement departement;

    public Departement(Modele.Departement departement){
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
    }

    public void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        switch(afficher) {
            case 0:
                affiche = true;
                personne = new Group();
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieDep.jpg"), 0, 0, 1, 1, true));
                departementPoly.setTranslateX(scene.getWidth() * posX);
                departementPoly.setTranslateY(scene.getHeight() * posY);

                nomR.setX((scene.getWidth() * 83.5) / 100);
                nomR.setY((scene.getHeight() * 45) / 100);
                nomR.setWrappingWidth((scene.getWidth() * 14) / 100);
                personne.getChildren().add(departementPoly);
                personne.getChildren().add(genePoint(jeu, departementPoly));
                personne.setOnMouseEntered(mouseEvent -> eventInformation(jeu));
                personne.setOnMouseExited(mouseEvent -> eventRemoveInformation(jeu));
                personne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(jeu));
                root.getChildren().addAll(nomR, liste, personne);
                break;
            case 1:
                if(affiche) {
                    affiche = false;
                    root.getChildren().removeAll(nomR, personne, information);
                    //root.getChildren().clear();
                }
                break;
            default:
                if(affiche) {
                    affichage(jeu, 0);
                }
        }
    }
    private Group genePoint(Modele.Jeu jeu, Polygon polygon) {
        Scene scene = jeu.getVue().getScene();
        Group depPersonne = new Group();
        for (int i = 0; i < departement.getNbPersonne(); i++) {
            Circle circle = new Circle();
            circle.setRadius(4);
            if (i < departement.getNbActif()) {
                circle.setFill(new ImagePattern(new Image("file:image\\PointInfecte.png")));
            } else {
                circle.setFill(new ImagePattern(new Image("file:image\\PointNormal.png")));
            }
            do {
                circle.setCenterX(scene.getWidth() * posX + Math.random() * polygon.getLayoutBounds().getWidth());
                circle.setCenterY(scene.getHeight() * posY + Math.random() * polygon.getLayoutBounds().getHeight());
            } while(!polygon.contains(circle.getCenterX() - scene.getWidth() * posX, circle.getCenterY() - scene.getHeight() * posY));
            depPersonne.getChildren().add(circle);
        }
        return depPersonne;
    }

    private void eventInformation(Modele.Jeu jeu){
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        root.getChildren().remove(liste);
        String nom = departement.getNom();
        nomR.setText(nom);
        int efficacite = departement.getEfficacite();
        int morale = departement.getMorale();
        int taches = departement.getNbTaches();
        int infecte = departement.getNbActif();
        information = new Text("Efficacite : " + Integer.toString(efficacite) + "%\nMorale : " + Integer.toString(morale) + "%\n" +
                "Nb taches : " + Integer.toString(taches) + "\n" + "Nb infectes : " + Integer.toString(infecte));
        information.setFont(Font.loadFont("file:Font.ttf", 24));
        information.setX((scene.getWidth() * 83.5) / 100);
        information.setY((scene.getHeight() * 52) / 100);
        information.setWrappingWidth((scene.getWidth() * 14) / 100);
        departementPoly.setFill(new ImagePattern(new Image("file:image\\"+ nom +"DepSelec.jpg"), 0, 0, 1, 1, true));
        root.getChildren().add(information);
    }
    private void eventRemoveInformation(Modele.Jeu jeu){
        if(affiche) {
            Group root = jeu.getVue().getRoot();
            root.getChildren().remove(information);
            String nom = departement.getNom();
            departementPoly.setFill(new ImagePattern(new Image("file:image\\" + nom + "Dep.jpg"), 0, 0, 1, 1, true));
        }
    }
    private void eventArbreDeCompetence(Modele.Jeu jeu){
        eventRemoveInformation(jeu);
        this.affichage(jeu, 1);
        Vue.ArbreDeCompetence ac = new ArbreDeCompetence(departement.getArbre());
        ac.affichage(jeu, 0);
    }
}
