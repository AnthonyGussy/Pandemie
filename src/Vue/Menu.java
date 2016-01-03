package Vue;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;

public class Menu {
    final int positionY = 20;
    final int espacement = 40;
    final int largeur = 160;
    final int hauteur = 40;
    boolean affiche = false;
    List<Rectangle> boutons;
    Jeu jeu;

    Menu (String[] boutonTypes, Jeu j){

        jeu = j;
        boutons = new ArrayList<>();
        for(String type : boutonTypes) {
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
                    case "Quitter":
                        System.exit(0);
                        break;
                    case "Sauvegarder":
                        jeu.sauvegarder();
                        break;
                    case "Charger":
                        jeu.charger();
                        break;
                    case "Jouer":
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

    void affichage(int afficher){
        switch(afficher){
            case 0:
                affiche = true;
                Jeu.scene.setFill(new ImagePattern(new Image("file:image\\PandemieAccueil.jpg"), 0, 0, 1, 1, true));
                int i = 1;
                for (Rectangle b : boutons) {
                    Jeu.root.getChildren().remove(b);
                    b.setX(((Jeu.scene.getWidth() * 21 *i) / 100 ));
                    b.setY((Jeu.scene.getHeight() * 50) / 100);
                    b.setHeight(77);
                    b.setWidth(152);
                    Jeu.root.getChildren().add(b);
                    ++i;
                }
                break;
            case 1:
                affiche = false;
                for (Rectangle b : boutons) {
                    Jeu.root.getChildren().remove(b);
                }
                break;
            default:
                if(affiche) {
                    affichage(0);
                }
        }
    }
}
