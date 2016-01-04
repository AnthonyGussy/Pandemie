package Vue;

import Enumerations.DepartementNom;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Scorp' on 02/01/2016.
 */
public class Departement{

    private ArrayList<Modele.Departement> departements;
    private boolean affichee;
    private Text information;
    private ImageView liste;
    private Text nomR;

    public Departement(ArrayList<Modele.Departement> departs){
        departements = new ArrayList<>();
        departements = departs ;
        affichee=false;
    }

    private Modele.Departement rechercheDep(DepartementNom recherche){
        for(Modele.Departement test : departements){
            if(test.getNomEnum()==recherche){
                return test;
            }
        }
        return null;
    }

    public void affichage(int action){
        Jeu.scene.setFill(new ImagePattern(new Image("file:image\\PandemieDep.jpg"), 0, 0, 1, 1, true));

        //Poly info
        Polygon info = new Polygon();
        info.getPoints().addAll(15.0, 35.0,
                104.0, 5.0,
                227.0, 19.0,
                258.0, 91.0,
                236.0, 148.0,
                94.0, 154.0,
                14.0, 125.0);
        info.setFill(new ImagePattern(new Image("file:image\\InformatiqueDep.jpg"), 0, 0, 1, 1, true));
        info.setTranslateX(Jeu.scene.getWidth()*26.9/100);
        info.setTranslateY(Jeu.scene.getHeight()*52.8/100);
        //Poly imsi
        Polygon imsi = new Polygon();
        imsi.getPoints().addAll(3.0, 19.0,
                86.0, 2.0,
                172.0, 3.0,
                254.0, 44.0,
                248.0, 122.0,
                180.0, 138.0,
                94.0, 124.0,
                25.0, 132.0);
        imsi.setFill(new ImagePattern(new Image("file:image\\ImsiDep.jpg"), 0, 0, 1, 1, true));
        imsi.setTranslateX(Jeu.scene.getWidth()*53.7/100);
        imsi.setTranslateY(Jeu.scene.getHeight()*37.5/100);
        //Poly energie
        Polygon energie = new Polygon();
        energie.getPoints().addAll(20.0, 32.0,
                93.0, 4.0,
                234.0, 3.0,
                267.0, 80.0,
                198.0, 104.0,
                95.0, 162.0,
                4.0, 146.0);
        energie.setFill(new ImagePattern(new Image("file:image\\EnergieDep.jpg"), 0, 0, 1, 1, true));
        energie.setTranslateX(Jeu.scene.getWidth()*6.9/100);
        energie.setTranslateY(Jeu.scene.getHeight()*36.1/100);
        //Poly gmc
        Polygon gmc = new Polygon();
        gmc.getPoints().addAll(4.0, 10.0,
                79.0, 11.0,
                122.0, 30.0,
                167.0, 26.0,
                186.0, 0.0,
                239.0, 2.0,
                243.0, 111.0,
                6.0, 116.0);
        gmc.setFill(new ImagePattern(new Image("file:image\\GMCDep.jpg"), 0, 0, 1, 1, true));
        gmc.setTranslateX(Jeu.scene.getWidth()*50.0/100);
        gmc.setTranslateY(Jeu.scene.getHeight()*79.8/100);
        //Poly edim
        Polygon edim = new Polygon();
        edim.getPoints().addAll(4.0, 21.0,
                77.0, 2.0,
                133.0, 72.0,
                225.0, 68.0,
                229.0, 160.0,
                6.0, 161.0);
        edim.setFill(new ImagePattern(new Image("file:image\\EdimDep.jpg"), 0, 0, 1, 1, true));
        edim.setTranslateX(Jeu.scene.getWidth()*4.6/100);
        edim.setTranslateY(Jeu.scene.getHeight()*72.9/100);

        liste = new ImageView(new Image("file:image\\Liste.jpg"));
        liste.setTranslateX(Jeu.scene.getWidth()*84.5/100);
        liste.setTranslateY(Jeu.scene.getHeight()*50.8/100);

        nomR = new Text("Departements :");
        nomR.setFont(Font.loadFont("file:Font.ttf", 24));
        nomR.setX((Jeu.scene.getWidth() * 83.5) / 100);
        nomR.setY((Jeu.scene.getHeight() * 45) / 100);
        nomR.setWrappingWidth((Jeu.scene.getWidth() * 14) / 100);

        Group infoPersonne = new Group();
        infoPersonne.getChildren().add(info);
        infoPersonne.getChildren().add(genePoint(DepartementNom.Informatique, info, 26.9 / 100, 52.8 / 100));

        Group imsiPersonne = new Group();
        imsiPersonne.getChildren().add(imsi);
        imsiPersonne.getChildren().add(genePoint(DepartementNom.Imsi, imsi, 53.7 / 100, 37.5 / 100));

        Group energiePersonne = new Group();
        energiePersonne.getChildren().add(energie);
        energiePersonne.getChildren().add(genePoint(DepartementNom.Energie,energie, 6.9 / 100, 36.1 / 100));

        Group gmcPersonne = new Group();
        gmcPersonne.getChildren().add(gmc);
        gmcPersonne.getChildren().add(genePoint(DepartementNom.Gmc,gmc, 50.0 / 100, 79.8 / 100));

        Group edimPersonne = new Group();
        edimPersonne.getChildren().add(edim);
        edimPersonne.getChildren().add(genePoint(DepartementNom.Edim,edim, 4.6 / 100, 72.9 / 100));

        if(action==0) {
            Jeu.root.getChildren().addAll(nomR, liste, infoPersonne, imsiPersonne, energiePersonne, gmcPersonne, edimPersonne);
            affichee=true;
        }
        else if(action==1 && affichee){
            Jeu.root.getChildren().addAll(nomR, liste, infoPersonne, imsiPersonne, energiePersonne, gmcPersonne, edimPersonne);
        }
        else if(action==2){
            Jeu.root.getChildren().removeAll(nomR, liste, infoPersonne, imsiPersonne, energiePersonne, gmcPersonne, edimPersonne);
            affichee=false;
        }

        infoPersonne.setOnMouseEntered(mouseEvent -> eventInformation(rechercheDep(DepartementNom.Informatique), info));
        infoPersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(rechercheDep(DepartementNom.Informatique), info));
        infoPersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(rechercheDep(DepartementNom.Informatique)));

        imsiPersonne.setOnMouseEntered(mouseEvent -> eventInformation(rechercheDep(DepartementNom.Imsi), imsi));
        imsiPersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(rechercheDep(DepartementNom.Imsi), imsi));
        imsiPersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(rechercheDep(DepartementNom.Imsi)));

        energiePersonne.setOnMouseEntered(mouseEvent -> eventInformation(rechercheDep(DepartementNom.Energie), energie));
        energiePersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(rechercheDep(DepartementNom.Energie), energie));
        energiePersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(rechercheDep(DepartementNom.Energie)));

        gmcPersonne.setOnMouseEntered(mouseEvent -> eventInformation(rechercheDep(DepartementNom.Gmc), gmc));
        gmcPersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(rechercheDep(DepartementNom.Gmc), gmc));
        gmcPersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(rechercheDep(DepartementNom.Gmc)));

        edimPersonne.setOnMouseEntered(mouseEvent -> eventInformation(rechercheDep(DepartementNom.Edim), edim));
        edimPersonne.setOnMouseExited(mouseEvent -> eventRemoveInformation(rechercheDep(DepartementNom.Edim), edim));
        edimPersonne.setOnMouseClicked(mouseEvent -> eventArbreDeCompetence(rechercheDep(DepartementNom.Edim)));
    }
    private Group genePoint(DepartementNom departementNom, Polygon polygon, double width, double height){
        Group depPersonne = new Group();
        for(int i = 0 ;i<rechercheDep(departementNom).getNbPersonne();i++) {
            Circle personne = new Circle();
            personne.setRadius(4);
            if(i<rechercheDep(departementNom).getNbActif()) {
                personne.setFill(new ImagePattern(new Image("file:image\\PointInfecte.png")));
            }
            else{
                personne.setFill(new ImagePattern(new Image("file:image\\PointNormal.png")));
            }
            personne.setCenterX(Jeu.scene.getWidth() * width + Math.random() * polygon.getLayoutBounds().getWidth());
            personne.setCenterY(Jeu.scene.getHeight() * height + Math.random() * polygon.getLayoutBounds().getHeight());
            if(polygon.contains(personne.getCenterX()-Jeu.scene.getWidth() * width,personne.getCenterY()-Jeu.scene.getHeight() * height)){
                depPersonne.getChildren().add(personne);
            }
            else{
                --i;
            }
        }
        return depPersonne;
    }

    private void eventInformation(Modele.Departement departement, Polygon forme){
        Jeu.root.getChildren().remove(liste);
        String nom = departement.getNom();
        nomR.setText(nom);
        int efficacite = departement.getEfficacite();
        int morale = departement.getMorale();
        int taches = departement.getNbTaches();
        int infecte = departement.getNbActif();
        information = new Text("Efficacite : " + Integer.toString(efficacite) + "%\nMorale : " + Integer.toString(morale) + "%\n" +
                "Nb taches : " + Integer.toString(taches) + "\n" + "Nb infectes : " + Integer.toString(infecte));
        information.setFont(Font.loadFont("file:Font.ttf", 24));
        information.setX((Jeu.scene.getWidth() * 83.5) / 100);
        information.setY((Jeu.scene.getHeight() * 52) / 100);
        information.setWrappingWidth((Jeu.scene.getWidth() * 14) / 100);
        forme.setFill(new ImagePattern(new Image("file:image\\"+ nom +"DepSelec.jpg"), 0, 0, 1, 1, true));
        Jeu.root.getChildren().add(information);
    }
    private void eventRemoveInformation(Modele.Departement departement, Polygon forme){
        Jeu.root.getChildren().remove(information);
        nomR.setText("Departements :");
        Jeu.root.getChildren().add(liste);
        String nom = departement.getNom();
        forme.setFill(new ImagePattern(new Image("file:image\\" + nom + "Dep.jpg"), 0, 0, 1, 1, true));
    }
    private void eventArbreDeCompetence(Modele.Departement departement){
        Vue.ArbreDeCompetence ac = new ArbreDeCompetence(departement.getArbre());
        ac.affichage();
    }
}
