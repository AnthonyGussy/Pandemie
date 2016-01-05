package Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Partie Vue de la classe Jeu
 * Cette classe sert à instancier l'affichage du jeu
 */

public class Jeu {

    // Champs
    private Modele.Jeu modele;
    private static Group root;
    private static Scene scene;
    private ImageView liste;
    private Text texte;
    private boolean affiche = false;

    // Constructeur
    public Jeu(Stage primaryStage, Modele.Jeu modele) {
        this.modele = modele;
        root = new Group();
        scene = new Scene(root, 1024, 768);
        liste = new ImageView(new Image("file:image\\Liste.jpg"));
        texte = new Text("Departements :");
        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> {
            modele.redimensionner();
        });
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> {
            modele.redimensionner();
        });
    }

    // Méthodes
    /**
     * Getter du groupe racine
     * @return Groupe racine
     */
    public Group getRoot() { return root; }

    /**
     * Getter de la scene
     * @return La scene
     */
    public Scene getScene() { return scene; }

    public void affichage(int afficher) {
        switch(afficher) {
            case 0:
                root.getChildren().removeAll(liste, texte);
                affiche = true;
                liste.setTranslateX(scene.getWidth() * 84.5 / 100);
                liste.setTranslateY(scene.getHeight() * 50.8 / 100);
                texte.setX((scene.getWidth() * 83.5) / 100);
                texte.setY((scene.getHeight() * 45) / 100);
                texte.setWrappingWidth((scene.getWidth() * 14) / 100);
                texte.setFont(Font.loadFont("file:Font.ttf", 24));
                root.getChildren().addAll(liste, texte);
                break;
            case 1:
                if(affiche) {
                    affiche = false;
                    for(Modele.Departement departement : modele.getDepartements()) {
                        departement.getVue().affichage(modele, 1);
                    }
                    root.getChildren().removeAll(liste, texte);
                }
                break;
            case 2:
                if(affiche) {
                    affichage(0);
                    for(Modele.Departement departement : modele.getDepartements()) {
                        departement.getVue().affichage(modele, 2);
                    }
                }
                break;
            case 3:
                if(affiche) {
                    root.getChildren().removeAll(liste, texte);
                }
        }
    }
}
