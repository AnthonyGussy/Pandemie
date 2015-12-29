package Vue;

import Modele.ArbreDeCompetenceModele;
import Modele.Compteur;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Jeu extends Application {
    List<Menu> menus;
    //List<Departement> departements;
    List<Compteur> compteurs;
    //List<Evenement> evenements;
    public static Group root;
    public static Scene scene;
    void sauvegarder(){}
    void charger(){}

    @Override
    public void start(Stage primaryStage) {
        menus = new ArrayList<Menu>();
        root = new Group();

        scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);
        //menus.add(new Menu());
        String[] boutons = new String[]{"Commencer", "Charger", "Quitter"};
        menus.add(new Menu(boutons));
        menus.get(0).affichage(true);
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                menus.get(0).affichage(true);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                menus.get(0).affichage(true);
            }
        });

        primaryStage.show();
    }

    public static void commencerPartie() {
        ArbreDeCompetenceModele arbre = new ArbreDeCompetenceModele("info");
        ArbreDeCompetenceVue arbreV = new ArbreDeCompetenceVue(arbre);
        arbreV.affichage();
    }

    public static void main(String[] args) { launch(args); }

}

