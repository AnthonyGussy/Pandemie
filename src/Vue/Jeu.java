package Vue;

import Modele.*;
import Modele.PopUp;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import Constantes.Constantes;

import java.util.ArrayList;

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
    private Stage primaryStage;
    private Coin coin;

    // Constructeur
    public Jeu(Stage primaryStage, Modele.Jeu modele) {
        this.primaryStage = primaryStage;
        this.modele = modele;
        root = new Group();
        scene = new Scene(root, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
        liste = new ImageView(new Image("file:image\\Liste.jpg"));
        texte = new Text("Départements :");
        liste.setVisible(false);
        texte.setVisible(false);
        root.getChildren().addAll(liste, texte);

        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);

        primaryStage.show();
        coin = new Coin(this);
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> modele.redimensionner());
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> modele.redimensionner());
        //scene.setOnMouseClicked(mouseEvent -> System.out.println(scene.getWidth() + " " + scene.getHeight()));
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
    public void  affichagePlateau(int afficher) {
        switch(afficher) {
            case 0:
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieDep.jpg"), 0, 0, 1, 1, true));
                for(Modele.Departement departement : modele.getDepartements()) {
                    departement.getVue().affichage(modele, 0);
                }
                liste.setX(scene.getWidth() * Constantes.POS_X_LISTE);
                liste.setY(scene.getHeight() * Constantes.POS_Y_LISTE);
                liste.setFitWidth(scene.getWidth() * Constantes.LARGEUR_LISTE);
                liste.setFitHeight(scene.getHeight() * Constantes.HAUTEUR_LISTE);
                texte.setX(scene.getWidth() * Constantes.POS_X_TEXTE);
                texte.setY(scene.getHeight() * Constantes.POS_Y_TEXTE);
                texte.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE));
                liste.setVisible(true);
                texte.setVisible(true);
                coin.affichage(this, 0);
                break;
            case 1:
                affichagePlateau(4);
                for(Modele.Departement departement : modele.getDepartements()) {
                    departement.getVue().affichage(modele, 1);
                    departement.getArbre().getVue().affichage(modele, 1);
                }
                break;
            case 2:
                liste.setVisible(true);
                texte.setVisible(true);
                break;
            case 3:
                coin.affichage(this, 3);
                for(Modele.Departement departement : modele.getDepartements()) {
                    departement.getVue().affichage(modele, 2);
                }
                for(Modele.Departement departement : modele.getDepartements()) {
                    departement.getArbre().getVue().affichage(modele,2);
                }
                liste.setX(scene.getWidth() * Constantes.POS_X_LISTE);
                liste.setY(scene.getHeight() * Constantes.POS_Y_LISTE);
                liste.setFitWidth(scene.getWidth() * Constantes.LARGEUR_LISTE);
                liste.setFitHeight(scene.getHeight() * Constantes.HAUTEUR_LISTE);
                texte.setX(scene.getWidth() * Constantes.POS_X_TEXTE);
                texte.setY(scene.getHeight() * Constantes.POS_Y_TEXTE);
                texte.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE));
                break;
            default:
                liste.setVisible(false);
                texte.setVisible(false);
        }
    }

    public void affichageMenuJeu(int afficher) {
        switch(afficher) {
            case 0:
                modele.getMenuJeu().affichage(modele, 0);
                break;
            default:
                modele.getMenuJeu().affichage(modele, 1);
        }
    }

    public void affichagePopUp(int afficher) {
        ArrayList<Modele.PopUp> popUps = modele.getPopUps();
        switch(afficher) {
            case 0:
                for(Modele.PopUp popUp : popUps) {
                    popUp.getVue().affichage(modele, 0);
                }
                break;
            case 1:
                for(Modele.PopUp popUp : popUps) {
                    popUp.getVue().affichage(modele, 1);
                }
                break;
            case 2:
                Platform.runLater(() -> {
                    for(Modele.PopUp popUp : popUps) {
                        popUp.getVue().affichage(modele, 2);
                        popUp.setDuree(modele);
                    }
                    modele.enleverPopUp();
                });
        }
    }

    public void affichageEvenement(int afficher) {
        switch(afficher) {
            case 0:
                for(Modele.Evenement evenement : modele.getEvenements()) {
                    evenement.getVue().affichage(modele, 0);
                }
                break;
            case 1:
                for(Modele.Evenement evenement : modele.getEvenements()) {
                    evenement.getVue().affichage(modele, 1);
                }
                break;
            case 2:
                if (modele.getEvenements().size() > 0) {
                    Platform.runLater(() -> {
                        if (modele.getEvenements().get(0).getDuree() == 0) modele.getEvenements().remove(0);
                    });
                    modele.getEvenements().get(0).setDuree(modele);
                }
        }
    }
}
