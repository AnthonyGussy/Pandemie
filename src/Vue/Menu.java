package Vue;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;

public class Menu {
    final int positionY = 20;
    final int espacement = 40;
    final int largeur = 160;
    final int hauteur = 40;
    List<Button> boutons;

    Menu (String[] boutonTypes){
        boutons = new ArrayList<Button>();
        for(String type : boutonTypes) {
            Button b = new Button(type);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    switch(type){
                        case "Quitter":
                            System.exit(0);
                            break;
                        case "Commencer":
                            Jeu.commencerPartie();
                            affichage(false);
                            break;
                        default:
                            break;
                    }
                }
            });
            boutons.add(b);
        }
    }

    void affichage(boolean afficher){
        if(afficher) {
            int i = 1;
            for (Button b : boutons) {
                Jeu.root.getChildren().remove(b);
                b.setLayoutX((Jeu.scene.getWidth() - largeur) / 2);
                b.setLayoutY((Jeu.scene.getHeight() / (boutons.size() + 1)) * i - hauteur / 2);
                b.setMinSize(largeur, hauteur);
                Jeu.root.getChildren().add(b);
                ++i;
            }
        }
        else {
            for (Button b : boutons) {
                Jeu.root.getChildren().remove(b);
            }
        }
    }
}
