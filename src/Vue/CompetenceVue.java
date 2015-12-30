package Vue;

import Modele.CompetenceModele;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Font;

import java.awt.*;

/**
 * Created by Anthony on 15/12/2015.
 */
public class CompetenceVue {

    CompetenceModele compM;
    ArbreDeCompetenceVue vueArbre;
    Circle compet;
    Group g;
    int colonne;
    int ligne;
    Rectangle r;

    CompetenceVue(CompetenceModele c, ArbreDeCompetenceVue arbreDeCompetenceVue){

        vueArbre = arbreDeCompetenceVue;
        compM = c;
        ligne = c.getLigne();
        colonne = c.getColonne();

    }

    void affichage(){

        double coefx = (double) 1/compM.getNbLignes()*(ligne-1);
        double coefy = (double) 1/compM.getNbColonnes()*(colonne-1);

        compet = new Circle((Jeu.scene.getWidth()*15)/100+coefx*(Jeu.scene.getWidth()*60/100),
                (Jeu.scene.getHeight()*90)/100-coefy*(Jeu.scene.getHeight()*65/100),20);

        if(compM.getDebloque())
            compet.setFill(new ImagePattern(new Image("file:CompetenceDebloque.png"), 0, 0, 1, 1, true));
        else
            compet.setFill(new ImagePattern(new Image("file:CompetenceBloque.png"), 0, 0, 1, 1, true));

        //quand la souris entre dans la zone du cercle ajoute la fenetre de description
        compet.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                compet.setRadius(22);

                Text nomR = new Text(compM.getNom() + "\n \n" + compM.getDescription());
                if(compM.getEffet()[0] != 0) nomR.setText(nomR.getText()+"\nmoral :+"+compM.getEffet()[0]);
                if(compM.getEffet()[1] != 0) nomR.setText(nomR.getText()+"\nefficacité :+"+compM.getEffet()[1]);
                if(compM.getEffet()[2] != 0) nomR.setText(nomR.getText()+"\ntemps :+"+compM.getEffet()[2]);

                nomR.setFont(Font.loadFont("file:Font.ttf", 24));
                nomR.setX((Jeu.scene.getWidth() * 83.5) / 100);
                nomR.setY((Jeu.scene.getHeight() * 45) / 100);
                nomR.setWrappingWidth((Jeu.scene.getWidth() * 14) / 100);

                Jeu.root.getChildren().add(nomR);


            }

        });

        //quand la souris sors de la zone du cercle retire la fenetre de description
        compet.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                compet.setRadius(20);
                Jeu.root.getChildren().remove(Jeu.root.getChildren().size()-1);


            }

        });

        /*Lors du clique sur le cercle de la competence permet de figer le descriptif
          de la competence
         */
        compet.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                if(ArbreDeCompetenceVue.aCliquer.equals(ligne+","+colonne)) {

                    if (compM.getDebloque()) {
                        compM.getArbreDeCompetence().debloquerCompetence(ligne, colonne);
                        compet.setFill(new ImagePattern(new Image("file:CompetenceAchete.png"), 0, 0, 1, 1, true));
                        vueArbre.changementAffichage(ligne);
                    }

                }
                else ArbreDeCompetenceVue.aCliquer = ligne+","+colonne;
            }

        });

        Jeu.root.getChildren().add(compet);

    }

    public Circle getCompet(){ return compet;}

    public void setGroup(Group group){ g = group; }
}
