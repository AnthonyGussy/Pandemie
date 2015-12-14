import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Victor on 04/12/2015.
 */
public class Jeu extends Application {
    List<Menu> menus;
    List<Departement> departements;
    List<Compteur> compteurs;
    List<Evenement> evenements;
    ArbreDeCompetence arbre;
    static Group root;

    void sauvegarder(){}
    void charger(){}
    void affichage(){}

    @Override
    public void start(Stage primaryStage) {

        root = new Group();

        Scene scene = new Scene(root, 1000, 1000);

        primaryStage.setTitle("Coucou!");
        primaryStage.setScene(scene);

        ArbreDeCompetence arbre = new ArbreDeCompetence("competence.txt");
        arbre.affichage();

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}

