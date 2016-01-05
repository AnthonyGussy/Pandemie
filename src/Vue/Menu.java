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
                    default:
                        break;
                }
            });

            boutons.add(b);
        }
    }

    public void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        switch(afficher){
            case 0:
                affiche = true;
                jeu.getVue().getScene().setFill(new ImagePattern(new Image("file:image\\PandemieAccueil.jpg"), 0, 0, 1, 1, true));
                int i = 1;
                for (Rectangle b : boutons) {
                    root.getChildren().remove(b);
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
                    if(boutonsGroup.getChildren().contains(b)) boutonsGroup.getChildren().remove(b);
                    boutonsGroup.getChildren().add(b);
                    ++i;
                }
                if(root.getChildren().contains(boutonsGroup)) root.getChildren().remove(boutonsGroup);
                root.getChildren().add(boutonsGroup);
                break;
            case 1:
                if(affiche) {
                    affiche = false;
                    if(root.getChildren().contains(boutonsGroup)) root.getChildren().remove(boutonsGroup);
                }
                break;
            default:
                if(affiche) {
                    affichage(jeu, 0);
                }
        }
    }
}
