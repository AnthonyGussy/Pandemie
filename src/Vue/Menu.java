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
    Group g;
    List<Button> boutons;

    Menu (String[] boutonTypes){
        boutons = new ArrayList<Button>();
        g = new Group();
        int i = 1;
        for(String type : boutonTypes) {
            Button b = new Button(type);
            b.setLayoutX((Jeu.scene.getWidth() - largeur) / 2);
            b.setLayoutY((Jeu.scene.getHeight() / (boutonTypes.length * 2)) * i - hauteur / 2);
            b.setMinSize(largeur, hauteur);
            b.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    switch(type){
                        case "Quitter":
                            System.exit(0);
                            break;
                        case "Commencer":
                            break;
                        default:
                            break;
                    }
                }
            });
            boutons.add(b);
            ++i;
        }
    }

    void affichage(){
        for(Button b : boutons) {
            Jeu.root.getChildren().add(b);
        }
    }
}
