package Vue;

import Enumerations.CompteurType;
import Enumerations.DepartementNom;
import Modele.ArbreDeCompetenceModele;
import Modele.Departement;
import Modele.EvenementArticleModele;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Jeu{
    List<Menu> menus;
    List<Departement> departements;
    List<Compteur> compteurs;
    //List<Evenement> evenements;
    public static Group root;
    public static Scene scene;
    void sauvegarder(){}
    void charger(){}

    public Jeu(Stage primaryStage) {
        menus = new ArrayList<>();
        root = new Group();

        departements = new ArrayList<>();

        scene = new Scene(root, 1024, 768);

        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);

        String[] boutons = new String[]{"Jouer", "Charger", "Quitter"};
        menus.add(new Menu(boutons, this));
        menus.get(0).affichage(0);
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            menus.get(0).affichage(0);
        });
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            menus.get(0).affichage(0);
        });
        primaryStage.show();
    }

    public void commencerPartie() {
        List<DepartementNom> departementNoms = new ArrayList<>(Arrays.asList(DepartementNom.Edim, DepartementNom.Energie, DepartementNom.Gmc, DepartementNom.Imsi, DepartementNom.Informatique));
        for(int i = 0; i<5; ++i) {
            int alea = (int)(Math.random()*departementNoms.size());
            departements.add(new Departement(departementNoms.get(alea)));
            departementNoms.remove(alea);
        }
    	/*
        ArbreDeCompetenceModele arbre = new ArbreDeCompetenceModele("Gmc");
        ArbreDeCompetenceVue arbreV = new ArbreDeCompetenceVue(arbre);
        arbreV.affichage();
        */
    	
    	EvenementArticleModele evArticleM = new EvenementArticleModele(DepartementNom.Gmc, "Facile", 0);
    	EvenementArticleVue evArticleV = new EvenementArticleVue(evArticleM);
    	evArticleV.affichage();
    }

}

