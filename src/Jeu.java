import Modele.ArbreDeCompetenceModele;
import Vue.ArbreDeCompetenceVue;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Jeu extends Application {
    /*List<Vue.Menu> menus;
    List<Departement> departements;
    List<Vue.Compteur> compteurs;
    List<Evenement> evenements;*/
    public static Group root;
    public static Scene scene;

    void sauvegarder(){}
    void charger(){}
    void affichage(){}

    @Override
    public void start(Stage primaryStage) {

        root = new Group();

        scene = new Scene(root, 1024, 768);

        primaryStage.setTitle("Coucou!");
        primaryStage.setScene(scene);
        ArbreDeCompetence arbreV = new ArbreDeCompetence(".\\competence.txt");
        arbreV.affichage();

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }


}

