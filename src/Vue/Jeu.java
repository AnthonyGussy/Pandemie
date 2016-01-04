package Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Partie Vue de la classe Jeu
 * Cette classe sert à instancier l'affichage du jeu
 */

public class Jeu {

    // Champs
    private Modele.Jeu modele;
    private static Group root;
    private static Scene scene;

    // Constructeur
    public Jeu(Stage primaryStage, Modele.Jeu modele) {
        this.modele = modele;
        root = new Group();
        scene = new Scene(root, 1024, 768);
        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            modele.redimensionner();
        });
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            modele.redimensionner();
        });
        primaryStage.show();
    }

    // Méthodes
    public Group getRoot() { return root; }
    public Scene getScene() { return scene; }

}
