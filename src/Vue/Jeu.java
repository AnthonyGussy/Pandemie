package Vue;

import Modele.ArbreDeCompetenceModele;
import Modele.Compteur;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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
    void affichage(){}

    @Override
    public void start(Stage primaryStage) {
        root = new Group();

        scene = new Scene(root, 1024, 768);

        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);
        //menus.add(new Menu());
        ArbreDeCompetenceModele arbre = new ArbreDeCompetenceModele(".\\competence.txt");
        ArbreDeCompetenceVue arbreV = new ArbreDeCompetenceVue(arbre);
        arbreV.affichage();
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }

}

