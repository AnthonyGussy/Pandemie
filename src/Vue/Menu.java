package Vue;

import Enumerations.BoutonType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;

/**
 * Cette classe sert à instancier un menu avec différents boutons
 */
public class Menu {

    //Champs
    private boolean affiche = false;
    private List<Rectangle> boutons;
    private Modele.Jeu jeu;

    // Constructeur
    public Menu (BoutonType[] boutonTypes, Modele.Jeu jeu){
        this.jeu = jeu;
        boutons = new ArrayList<>();
        for(BoutonType type : boutonTypes) {
            Rectangle b = new Rectangle();

            b.setOnMouseEntered(event -> {
                b.setHeight(79);
                b.setWidth(154);
            });

            b.setOnMouseExited(event -> {
                b.setHeight(77);
                b.setWidth(152);
            });

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
                        affichage(1);
                        jeu.commencerPartie();
                        break;
                    default:
                        break;
                }
            });

            boutons.add(b);
        }
    }

    public void affichage(int afficher){
        switch(afficher){
            case 0:
                affiche = true;
                jeu.getVue().getScene().setFill(new ImagePattern(new Image("file:image\\PandemieAccueil.jpg"), 0, 0, 1, 1, true));
                int i = 1;
                for (Rectangle b : boutons) {
                    jeu.getVue().getRoot().getChildren().remove(b);
                    b.setX(((jeu.getVue().getScene().getWidth() * 21 *i) / 100 ));
                    b.setY((jeu.getVue().getScene().getHeight() * 50) / 100);
                    b.setHeight(77);
                    b.setWidth(152);
                    jeu.getVue().getRoot().getChildren().add(b);
                    ++i;
                }
                break;
            case 1:
                if(affiche) {
                    affiche = false;
                    jeu.getVue().getRoot().getChildren().clear();
                }
                break;
            default:
                if(affiche) {
                    affichage(0);
                }
        }
    }
}
