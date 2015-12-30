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
import javafx.scene.text.Text;

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

    public static boolean cliquer = false;

    CompetenceVue(CompetenceModele c, ArbreDeCompetenceVue arbreDeCompetenceVue){

        vueArbre = arbreDeCompetenceVue;
        compM = c;
        ligne = c.getLigne();
        colonne = c.getColonne();

    }

    void affichage(){

        double coefx = (double) 1/compM.getNbColonnes()*(colonne-1);
        double coefy = (double) 1/compM.getNbLignes()*(ligne-1);

        compet = new Circle((Jeu.scene.getWidth()*20)/100+coefx*(Jeu.scene.getWidth()*50/100),
                (Jeu.scene.getHeight()*85)/100-coefy*(Jeu.scene.getHeight()*70/100),20);

        if(compM.getDebloque())
            compet.setFill(new ImagePattern(new Image("file:CompetenceDebloque.png"), 0, 0, 1, 1, true));
        else
            compet.setFill(new ImagePattern(new Image("file:CompetenceBloque.png"), 0, 0, 1, 1, true));

        //quand la souris entre dans la zone du cercle ajoute la fenetre de description
        compet.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                if(CompetenceVue.cliquer) {
                    Jeu.root.getChildren().remove(Jeu.root.getChildren().size() - 1, Jeu.root.getChildren().size());
                    CompetenceVue.cliquer = false;
                }
                compet.setRadius(22);
                Group g = new Group();
                Rectangle r = new Rectangle();
                r.setX(800);
                r.setY(500);
                r.setWidth(200);
                r.setHeight(400);
                Text nomR = new Text(compM.getNom() + "\n" + compM.getDescription());
                nomR.setFill(Color.AQUA);
                nomR.setX(ligne + 210);
                nomR.setY(colonne + 250);
                Button b = new Button("Achat");
                b.setLayoutX(ligne + 210);
                b.setLayoutY(colonne + 280);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if (compM.getDebloque() && !compM.getAchete()) {
                            compM.getArbreDeCompetence().debloquerCompetence(ligne, colonne);
                            compet.setFill(new ImagePattern(new Image("file:CompetenceAchete.png"), 0, 0, 1, 1, true));
                            vueArbre.changementAffichage(ligne);
                        }
                    }
                });
                g.getChildren().add(r);
                g.getChildren().add(nomR);
                g.getChildren().add(b);
                setGroup(g);
                Jeu.root.getChildren().add(g);


            }

        });

        //quand la souris sors de la zone du cercle retire la fenetre de description
        compet.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                compet.setRadius(20);
                Jeu.root.getChildren().remove(Jeu.root.getChildren().size() - 1, Jeu.root.getChildren().size());


            }

        });

        /*Lors du clique sur le cercle de la competence permet de figer le descriptif
          de la competence
         */
        compet.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent mouseEvent) {

                Group g = new Group();
                Rectangle r = new Rectangle();
                r.setX(ligne + 200);
                r.setY(colonne + 200);
                r.setWidth(100);
                r.setHeight(100);
                Text nomR = new Text(compM.getNom() + "\n" + compM.getDescription());
                nomR.setFill(Color.AQUA);
                nomR.setX(ligne + 210);
                nomR.setY(colonne + 250);
                Button b = new Button("Achat");
                b.setLayoutX(ligne + 210);
                b.setLayoutY(colonne + 280);
                b.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        if (compM.getDebloque()) {
                            compM.getArbreDeCompetence().debloquerCompetence(ligne, colonne);
                            compet.setFill(new ImagePattern(new Image("file:CompetenceAchete.png"), 0, 0, 1, 1, true));
                            vueArbre.changementAffichage(ligne);
                        }
                    }
                });
                g.getChildren().add(r);
                g.getChildren().add(nomR);
                g.getChildren().add(b);
                setGroup(g);
                Jeu.root.getChildren().add(g);
                CompetenceVue.cliquer = true;


            }

        });
        Jeu.root.getChildren().add(compet);

    }

    public Circle getCompet(){ return compet;}

    public void setGroup(Group group){ g = group; }
}
