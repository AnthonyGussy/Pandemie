package Vue;

import Enumerations.BoutonType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;
import Constantes.Constantes;

/**
 * Cette classe sert à instancier un menu avec différents boutons
 */
public class Menu {

    //Champs
    private boolean affiche = false;
    private List<Rectangle> boutons;
    private Group boutonsGroup;

    // Constructeur
    public Menu (BoutonType[] boutonTypes, Modele.Jeu jeu){
        boutons = new ArrayList<>();
        boutonsGroup = new Group();
        for(BoutonType type : boutonTypes) {
            Rectangle b = new Rectangle();
            ImagePattern imagePattern = new ImagePattern(new Image("file:image\\Pandemie" + type + ".jpg"), 0, 0, 1, 1, true);

            if(!imagePattern.getImage().isError()) {
                b.setFill(imagePattern);
            }
            else {
                System.out.println("Pas d'image pour le bouton " + type);
                b.setFill(new ImagePattern(new Image("file:image\\PandemieDefault.png"), 0, 0, 1, 1, true));
            }

            b.setOnMouseClicked(e -> {
                switch (type) {
                    case Quitter:
                        System.exit(0);
                        break;
                    case Sauvegarder:
                        jeu.sauvegarder();
                        break;
                    case Charger:
                        jeu.charger();
                        break;
                    case Jouer:
                        affichage(jeu, 1);
                        jeu.commencerPartie();
                        break;
                    case Regles:
                        break;
                    case Retour_Jeu:
                        affichage(jeu, 1);
                        jeu.getVue().affichagePlateau(0);
                        break;
                }
            });
            boutons.add(b);
            boutonsGroup.setVisible(false);

        }
        jeu.getVue().getRoot().getChildren().add(boutonsGroup);
    }

    public void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        switch(afficher){
            case 0:
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieAccueil.jpg"), 0, 0, 1, 1, true));
                int i = 1;
                boutonsGroup.getChildren().clear();
                for (Rectangle b : boutons) {
                    b.setOnMouseEntered(event -> {
                        b.setWidth(scene.getWidth() * Constantes.LARGEUR_BOUTON_SURVOL);
                        b.setHeight(scene.getHeight() * Constantes.HAUTEUR_BOUTON_SURVOL);
                    });
                    b.setOnMouseExited(event -> {
                        b.setWidth(scene.getWidth() * Constantes.LARGEUR_BOUTON);
                        b.setHeight(scene.getHeight() * Constantes.HAUTEUR_BOUTON);
                    });
                    b.setX(scene.getWidth() * i * Constantes.POS_X_BOUTON);
                    b.setY(scene.getHeight() * Constantes.POS_Y_BOUTON);
                    b.setWidth(scene.getWidth() * Constantes.LARGEUR_BOUTON);
                    b.setHeight(scene.getHeight() * Constantes.HAUTEUR_BOUTON);
                    boutonsGroup.getChildren().add(b);
                    i++;
                }
                boutonsGroup.setVisible(true);
                break;
            case 1:
                boutonsGroup.setVisible(false);
            default:
                int j = 1;
                for (Rectangle b : boutons) {
                    b.setX(scene.getWidth() * j * Constantes.POS_X_BOUTON);
                    b.setY(scene.getHeight() * Constantes.POS_Y_BOUTON);
                    b.setWidth(scene.getWidth() * Constantes.LARGEUR_BOUTON);
                    b.setHeight(scene.getHeight() * Constantes.HAUTEUR_BOUTON);
                    j++;
                }
                break;
        }
    }
}
