package Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Constantes.Constantes;

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
        scene = new Scene(root, 1024, 650);
        liste = new ImageView(new Image("file:image\\Liste.jpg"));
        texte = new Text("Départements :");

        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
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

    /**
     * Méthode qui affiche le "plateau de jeu"
     * @param afficher Entier qui détermine l'action à effectuer. 0 pour afficher, 1 pour désafficher, 2 pour mettre à jour l'affichage
     *                 et 3 pour enlever uniquement les informations, pas les départements
     */
    public void affichage(int afficher) {
        switch(afficher) {
            case 0:
                root.getChildren().removeAll(liste, texte);
                affiche = true;
                liste.setTranslateX(scene.getWidth() * Constantes.POS_X_LISTE);
                liste.setTranslateY(scene.getHeight() * Constantes.POS_Y_LISTE);
                liste.setFitWidth(scene.getWidth() * Constantes.LARGEUR_LISTE);
                liste.setFitHeight(scene.getHeight() * Constantes.HAUTEUR_LISTE);
                texte.setX(scene.getWidth() * Constantes.POS_X_TEXTE);
                texte.setY(scene.getHeight() * Constantes.POS_Y_TEXTE);
                texte.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE));
                //texte.setWrappingWidth((scene.getWidth() * 14) / 100);
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
