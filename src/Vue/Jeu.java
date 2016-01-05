package Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Constantes.Constantes;

/**
 * Partie Vue de la classe Jeu
 * Cette classe sert à instancier l'affichagePlateau du jeu
 */

public class Jeu {

    // Champs
    private Modele.Jeu modele;
    private static Group root;
    private static Scene scene;
    private ImageView liste;
    private Text texte;
    private boolean affichePlateau = false;
    private Stage primaryStage;

    // Constructeur
    public Jeu(Stage primaryStage, Modele.Jeu modele) {
        this.primaryStage = primaryStage;
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
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> modele.redimensionner());
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> modele.redimensionner());
        //scene.setOnMouseClicked(mouseEvent -> System.out.println(mouseEvent.getX() + " " + mouseEvent.getY()));
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

    public Stage getPrimaryStage() { return primaryStage; }

    /**
     * Méthode qui affichePlateau le "plateau de jeu"
     * @param afficher Entier qui détermine l'action à effectuer. 0 pour afficher, 1 pour désafficher, 2 pour mettre à jour l'affichagePlateau
     *                 et 3 pour enlever uniquement les informations, pas les départements
     */
    public void affichagePlateau(int afficher) {
        switch(afficher) {
            case 0:
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieDep.jpg"), 0, 0, 1, 1, true));
                affichePlateau = true;
                liste.setX(scene.getWidth() * Constantes.POS_X_LISTE);
                liste.setY(scene.getHeight() * Constantes.POS_Y_LISTE);
                liste.setFitWidth(scene.getWidth() * Constantes.LARGEUR_LISTE);
                liste.setFitHeight(scene.getHeight() * Constantes.HAUTEUR_LISTE);
                texte.setX(scene.getWidth() * Constantes.POS_X_TEXTE);
                texte.setY(scene.getHeight() * Constantes.POS_Y_TEXTE);
                texte.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE));
                if(root.getChildren().contains(liste)) root.getChildren().remove(liste);
                if(root.getChildren().contains(texte)) root.getChildren().remove(texte);
                //root.getChildren().addAll(liste, texte); Ligne qui pose problème n°3
                break;
            case 1:
                if(affichePlateau) {
                    for(Modele.Departement departement : modele.getDepartements()) {
                        departement.getVue().affichage(modele, 1);
                    }
                    affichagePlateau(3);
                    affichePlateau = false;
                }
                break;
            case 2:
                if(affichePlateau) {
                    affichagePlateau(0);
                    for(Modele.Departement departement : modele.getDepartements()) {
                        departement.getVue().affichage(modele, 2);
                    }
                }
                else {

                    for(Modele.Departement departement : modele.getDepartements()) {
                        departement.getArbre().getVue().affichage(modele, 2);
                    }

                }
                break;
            case 3:
                if(affichePlateau) {
                    if(root.getChildren().contains(liste)) root.getChildren().remove(liste);
                    if(root.getChildren().contains(texte)) root.getChildren().remove(texte);
                }
        }
    }
}
