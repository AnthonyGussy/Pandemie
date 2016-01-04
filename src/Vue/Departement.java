package Vue;

import Enumerations.DepartementNom;
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

import java.util.ArrayList;

/**
 *
 */
public class Departement{

    //private ArrayList<Modele.Departement> departements;
    private boolean affiche = false;
    private Text information;
    private ImageView liste;
    private Text nomR;
    /*private Polygon info;
    private Polygon imsi;
    private Polygon energie;
    private Polygon gmc;
    private Polygon edim;
    private Group infoPersonne;
    private Group imsiPersonne;
    private Group energiePersonne;
    private Group gmcPersonne;
    private Group edimPersonne;*/
    private Polygon departementPoly;
    private Group personne;
    private double posX;
    private double posY;
    Modele.Departement departement;

    public Departement(Modele.Departement departement/*ArrayList<Modele.Departement> departs*/){
        /*departements = new ArrayList<>();
        departements = departs ;*/
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
        personne = new Group();
        /*// Poly info
        info = new Polygon();
        info.getPoints().addAll(15.0, 35.0,
                104.0, 5.0,
                227.0, 19.0,
                258.0, 91.0,
                236.0, 148.0,
                94.0, 154.0,
                14.0, 125.0);
        info.setFill(new ImagePattern(new Image("file:image\\InformatiqueDep.jpg"), 0, 0, 1, 1, true));

        // Poly imsi
        imsi = new Polygon();
        imsi.getPoints().addAll(3.0, 19.0,
                86.0, 2.0,
                172.0, 3.0,
                254.0, 44.0,
                248.0, 122.0,
                180.0, 138.0,
                94.0, 124.0,
                25.0, 132.0);
        imsi.setFill(new ImagePattern(new Image("file:image\\ImsiDep.jpg"), 0, 0, 1, 1, true));

        // Poly energie
        energie = new Polygon();
        energie.getPoints().addAll(20.0, 32.0,
                93.0, 4.0,
                234.0, 3.0,
                267.0, 80.0,
                198.0, 104.0,
                95.0, 162.0,
                4.0, 146.0);
        energie.setFill(new ImagePattern(new Image("file:image\\EnergieDep.jpg"), 0, 0, 1, 1, true));

        // Poly gmc
        gmc = new Polygon();
        gmc.getPoints().addAll(4.0, 10.0,
                79.0, 11.0,
                122.0, 30.0,
                167.0, 26.0,
                186.0, 0.0,
                239.0, 2.0,
                243.0, 111.0,
                6.0, 116.0);
        gmc.setFill(new ImagePattern(new Image("file:image\\GMCDep.jpg"), 0, 0, 1, 1, true));

        // Poly edim
        edim = new Polygon();
        edim.getPoints().addAll(4.0, 21.0,
                77.0, 2.0,
                133.0, 72.0,
                225.0, 68.0,
                229.0, 160.0,
                6.0, 161.0);
        edim.setFill(new ImagePattern(new Image("file:image\\EdimDep.jpg"), 0, 0, 1, 1, true));*/

        liste = new ImageView(new Image("file:image\\Liste.jpg"));
        nomR = new Text("Departements :");
        nomR.setFont(Font.loadFont("file:Font.ttf", 24));

        /*infoPersonne = new Group();
        imsiPersonne = new Group();
        energiePersonne = new Group();
        gmcPersonne = new Group();
        edimPersonne = new Group();*/
    }

    public void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        switch(afficher) {
            case 0:
                affiche = true;
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieDep.jpg"), 0, 0, 1, 1, true));
                departementPoly.setTranslateX(scene.getWidth() * posX);
                departementPoly.setTranslateY(scene.getWidth() * posY);
                /*info.setTranslateX(scene.getWidth() * 26.9 / 100);
                info.setTranslateY(scene.getHeight() * 52.8 / 100);
                
                imsi.setTranslateX(scene.getWidth() * 53.7 / 100);
                imsi.setTranslateY(scene.getHeight() * 37.5 / 100);
                
                energie.setTranslateX(scene.getWidth() * 6.9 / 100);
                energie.setTranslateY(scene.getHeight() * 36.1 / 100);

                gmc.setTranslateX(scene.getWidth() * 50.0 / 100);
                gmc.setTranslateY(scene.getHeight() * 79.8 / 100);

                edim.setTranslateX(scene.getWidth() * 4.6 / 100);
                edim.setTranslateY(scene.getHeight() * 72.9 / 100);*/

                liste.setTranslateX(scene.getWidth() * 84.5 / 100);
                liste.setTranslateY(scene.getHeight() * 50.8 / 100);

                nomR.setX((scene.getWidth() * 83.5) / 100);
                nomR.setY((scene.getHeight() * 45) / 100);
                nomR.setWrappingWidth((scene.getWidth() * 14) / 100);

                personne.getChildren().add(departementPoly);
                personne.getChildren().add(genePoint(jeu, departementPoly));
                personne.setOnMouseEntered(mouseEvent -> eventInformation(jeu, departementPoly));
                personne.setOnMouseExited(mouseEvent -> eventRemoveInformation(jeu, departementPoly));
                personne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(jeu));
                root.getChildren().addAll(nomR, liste, personne);
                /*infoPersonne.getChildren().add(info);
                infoPersonne.getChildren().add(genePoint(jeu, DepartementNom.Informatique, info, 26.9 / 100, 52.8 / 100));

                imsiPersonne.getChildren().add(imsi);
                imsiPersonne.getChildren().add(genePoint(jeu, DepartementNom.Imsi, imsi, 53.7 / 100, 37.5 / 100));

                energiePersonne.getChildren().add(energie);
                energiePersonne.getChildren().add(genePoint(jeu, DepartementNom.Energie, energie, 6.9 / 100, 36.1 / 100));

                gmcPersonne.getChildren().add(gmc);
                gmcPersonne.getChildren().add(genePoint(jeu, DepartementNom.Gmc, gmc, 50.0 / 100, 79.8 / 100));

                edimPersonne.getChildren().add(edim);
                edimPersonne.getChildren().add(genePoint(jeu, DepartementNom.Edim, edim, 4.6 / 100, 72.9 / 100));

                infoPersonne.setOnMouseEntered(mouseEvent -> eventInformation(jeu, rechercheDep(DepartementNom.Informatique), info));
                infoPersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(jeu, rechercheDep(DepartementNom.Informatique), info));
                infoPersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(jeu, rechercheDep(DepartementNom.Informatique)));

                imsiPersonne.setOnMouseEntered(mouseEvent -> eventInformation(jeu, rechercheDep(DepartementNom.Imsi), imsi));
                imsiPersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(jeu, rechercheDep(DepartementNom.Imsi), imsi));
                imsiPersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(jeu, rechercheDep(DepartementNom.Imsi)));

                energiePersonne.setOnMouseEntered(mouseEvent -> eventInformation(jeu, rechercheDep(DepartementNom.Energie), energie));
                energiePersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(jeu, rechercheDep(DepartementNom.Energie), energie));
                energiePersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(jeu, rechercheDep(DepartementNom.Energie)));

                gmcPersonne.setOnMouseEntered(mouseEvent -> eventInformation(jeu, rechercheDep(DepartementNom.Gmc), gmc));
                gmcPersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(jeu, rechercheDep(DepartementNom.Gmc), gmc));
                gmcPersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(jeu, rechercheDep(DepartementNom.Gmc)));

                edimPersonne.setOnMouseEntered(mouseEvent -> eventInformation(jeu, rechercheDep(DepartementNom.Edim), edim));
                edimPersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(jeu, rechercheDep(DepartementNom.Edim), edim));
                edimPersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(jeu, rechercheDep(DepartementNom.Edim)));
                root.getChildren().addAll(nomR, liste, infoPersonne, imsiPersonne, energiePersonne, gmcPersonne, edimPersonne);*/
                break;
            case 1:
                if(affiche) {
                    affiche = false;
                    //root.getChildren().removeAll(nomR, liste, infoPersonne, imsiPersonne, energiePersonne, gmcPersonne, edimPersonne);
                    root.getChildren().removeAll(nomR, liste, personne);
                    if(root.getChildren().contains(information)) root.getChildren().remove(information);
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
        Group root = jeu.getVue().getRoot();
        Group depPersonne = new Group();
        for (int i = 0; i < departement.getNbPersonne(); i++) {
            Circle circle = new Circle();
            circle.setRadius(4);
            if (i < departement.getNbActif()) {
                circle.setFill(new ImagePattern(new Image("file:image\\PointInfecte.png")));
            } else {
                circle.setFill(new ImagePattern(new Image("file:image\\PointNormal.png")));
            }
            circle.setCenterX(scene.getWidth() * posX + Math.random() * polygon.getLayoutBounds().getWidth());
            circle.setCenterY(scene.getHeight() * posY + Math.random() * polygon.getLayoutBounds().getHeight());
            if (polygon.contains(circle.getCenterX() - scene.getWidth() * posX, circle.getCenterY() - scene.getHeight() * posY)) {
                depPersonne.getChildren().add(circle);
            } else {
                --i;
            }
        }
        return depPersonne;
    }

    private void eventInformation(Modele.Jeu jeu, Polygon forme){
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
        forme.setFill(new ImagePattern(new Image("file:image\\"+ nom +"DepSelec.jpg"), 0, 0, 1, 1, true));
        root.getChildren().add(information);
    }
    private void eventRemoveInformation(Modele.Jeu jeu, Polygon forme){
        if(affiche) {
            Scene scene = jeu.getVue().getScene();
            Group root = jeu.getVue().getRoot();
            root.getChildren().remove(information);
            nomR.setText("Departements :");
            root.getChildren().add(liste);
            String nom = departement.getNom();
            forme.setFill(new ImagePattern(new Image("file:image\\" + nom + "Dep.jpg"), 0, 0, 1, 1, true));
        }
    }
    private void eventArbreDeCompetence(Modele.Jeu jeu){
        this.affichage(jeu, 1);
        Vue.ArbreDeCompetence ac = new ArbreDeCompetence(departement.getArbre());
        ac.affichage(jeu, 0);
    }
}
