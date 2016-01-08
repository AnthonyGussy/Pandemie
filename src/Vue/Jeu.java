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
    private boolean affiche = false;
    private Text nbPoints;
    private ImageView vitesse1;
    private ImageView vitesse2;
    private ImageView vitesse3;

    // Constructeur
    public Jeu(Stage primaryStage, Modele.Jeu modele) {
        this.primaryStage = primaryStage;
        this.modele = modele;
        root = new Group();
        scene = new Scene(root, Constantes.LARGEUR_FENETRE, Constantes.HAUTEUR_FENETRE);
        liste = new ImageView(new Image("file:image\\Liste.jpg"));
        texte = new Text("Départements :");
        nbPoints = new Text("Nombre de points : : 0");
        nbPoints.setVisible(false);
        liste.setVisible(false);
        texte.setVisible(false);

        vitesse1 = new ImageView(new Image("file:image\\FlecheP1.jpg"));
        vitesse1.setOnMouseClicked(event -> Timeline.vitesse = 1000);
        vitesse1.setOnMouseEntered(event -> vitesse1.setTranslateX(scene.getWidth() * (Constantes.POS_X_FLECHE1_VITESSE + 0.001)));
        vitesse1.setOnMouseExited(event -> vitesse1.setTranslateX(scene.getWidth() * Constantes.POS_X_FLECHE1_VITESSE));

        vitesse2 = new ImageView(new Image("file:image\\Vitesse2.png"));
        vitesse2.setOnMouseClicked(event -> Timeline.vitesse = 750);
        vitesse2.setOnMouseEntered(event -> vitesse2.setTranslateX(scene.getWidth() * (Constantes.POS_X_FLECHE2_VITESSE + 0.001)));
        vitesse2.setOnMouseExited(event -> vitesse2.setTranslateX(scene.getWidth() * Constantes.POS_X_FLECHE2_VITESSE));

        vitesse3 = new ImageView(new Image("file:image\\Vitesse3.png"));
        vitesse3.setOnMouseClicked(event -> Timeline.vitesse = 500);
        vitesse3.setOnMouseEntered(event -> vitesse3.setTranslateX(scene.getWidth() * (Constantes.POS_X_FLECHE3_VITESSE + 0.001)));
        vitesse3.setOnMouseExited(event -> vitesse3.setTranslateX(scene.getWidth() * Constantes.POS_X_FLECHE3_VITESSE));


        vitesse1.setVisible(false);
        vitesse2.setVisible(false);
        vitesse3.setVisible(false);
        nbPoints.setVisible(false);
        root.getChildren().addAll(liste, texte, nbPoints, vitesse1,vitesse2,vitesse3);

        primaryStage.setTitle("Study Project Simulator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(true);
        primaryStage.show();
        scene.widthProperty().addListener((observableValue, oldSceneWidth, newSceneWidth) -> modele.redimensionner());
        scene.heightProperty().addListener((observableValue, oldSceneHeight, newSceneHeight) -> modele.redimensionner());
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

    public boolean getAffiche() { return affiche; }

    /**
     * Méthode qui affichePlateau le "plateau de jeu"
     * @param afficher Entier qui détermine l'action à effectuer. 0 pour afficher, 1 pour désafficher, 2 pour mettre à jour l'affichagePlateau
     *                 et 3 pour enlever uniquement les informations, pas les départements
     */
    public void  affichagePlateau(int afficher) {
        switch(afficher) {
            case 0:
                affiche = true;
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieDep.jpg"), 0, 0, 1, 1, true));
                for(Modele.Departement departement : modele.getDepartements()) {
                    departement.getVue().affichage(modele, 0);
                }
                affichagePlateau(3);
                modele.afficherAvancement(0);
                vitesse1.setVisible(true);
                vitesse2.setVisible(true);
                vitesse3.setVisible(true);
                liste.setVisible(true);
                texte.setVisible(true);
                nbPoints.setVisible(true);
                modele.getCoin().affichage(modele, 0);
                break;
            case 1:
                affiche = false;
                for(Modele.Departement departement : modele.getDepartements()) {
                    departement.getVue().affichage(modele, 1);
                    departement.getArbre().getVue().affichage(modele, 1);
                }
                vitesse1.setVisible(false);
                vitesse2.setVisible(false);
                vitesse3.setVisible(false);
                affichagePlateau(4);
                break;
            case 2:
                liste.setVisible(true);
                texte.setVisible(true);
                break;
            case 3:
                modele.getCoin().affichage(modele, 3);
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
                vitesse1.setTranslateX(scene.getWidth() * Constantes.POS_X_FLECHE1_VITESSE);
                vitesse1.setTranslateY(scene.getHeight() * Constantes.POS_Y_FLECHE_VITESSE);

                vitesse2.setTranslateX(scene.getWidth() * Constantes.POS_X_FLECHE2_VITESSE);
                vitesse2.setTranslateY(scene.getHeight() * Constantes.POS_Y_FLECHE_VITESSE);

                vitesse3.setTranslateX(scene.getWidth() * Constantes.POS_X_FLECHE3_VITESSE);
                vitesse3.setTranslateY(scene.getHeight() * Constantes.POS_Y_FLECHE_VITESSE);
                nbPoints.setX(scene.getWidth() * Constantes.POS_X_PTC);
                nbPoints.setY(scene.getHeight() * Constantes.POS_Y_PTC);
                nbPoints.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE));
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
                    for (int i = 0; i < popUps.size(); i++) {
                        popUps.get(i).getVue().affichage(modele, 2);
                        popUps.get(i).setDuree(modele);
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
                    modele.getEvenements().get(0).diminuerTemps(modele);
                }
        }
    }

    public void affichagePtsDeCompetence(int affichage) {

        Platform.runLater(() -> {
            switch (affichage) {
                case 1:
                    nbPoints.setVisible(false);
                    affichagePlateau(1);
                    break;
                default:
                    nbPoints.setText("Nombre de points : " + modele.getPtsCompetence());
                    break;
            }
        });
    }

    public void victoire() {
        Platform.runLater(() -> {
            System.out.println("VICTOIRE");
            scene.setFill(new ImagePattern(new Image("file:image\\PandemieVictory.jpg"), 0, 0, 1, 1, true));
        });
    }

    public void gameOver() {
        Platform.runLater(() -> {
            System.out.println("DEFAITE");
            scene.setFill(new ImagePattern(new Image("file:image\\PandemieGameOver1.jpg"), 0, 0, 1, 1, true));
        });
    }
}
