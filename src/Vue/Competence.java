package Vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.scene.text.Font;

/**
 *
 */
public class Competence implements java.io.Serializable {

    // Champs
    private Modele.Competence compM;
    private ArbreDeCompetence vueArbre;
    private Circle compet;
    private Group g;
    private int colonne;
    private int ligne;
    private boolean affiche = false;
    private Text nomR;

    // Constructeur
    Competence(Modele.Competence c, ArbreDeCompetence arbreDeCompetenceVue){

        vueArbre = arbreDeCompetenceVue;
        compM = c;
        ligne = c.getLigne();
        colonne = c.getColonne();
        nomR = new Text(compM.getNom() + "\n \n" + compM.getDescription());
    }

    // Méthodes
    /**
     *
     * @param jeu
     * @param afficher
     */
    void affichage(Modele.Jeu jeu, int afficher) {
        switch(afficher) {
            case 0:
                affiche = true;
                Scene scene = jeu.getVue().getScene();
                Group root = jeu.getVue().getRoot();
                double coefx = (double) 1 / compM.getNbLignes() * (ligne - 1);
                double coefy = (double) 1 / compM.getNbColonnes() * (colonne - 1);
                compet = new Circle((scene.getWidth() * 15) / 100 + coefx * (scene.getWidth() * 60 / 100),
                        (scene.getHeight() * 90) / 100 - coefy * (scene.getHeight() * 65 / 100), 24);
                if (compM.getAchete())
                    compet.setFill(new ImagePattern(new Image("file:image\\CompetenceAchete.png"), 0, 0, 1, 1, true));
                else if (compM.getDebloque())
                    compet.setFill(new ImagePattern(new Image("file:image\\CompetenceDebloque.png"), 0, 0, 1, 1, true));
                else
                    compet.setFill(new ImagePattern(new Image("file:image\\CompetenceBloque.png"), 0, 0, 1, 1, true));

                // Quand la souris entre dans la zone du cercle, la fenêtre de description est affichée
                compet.setOnMouseEntered(mouseEvent -> {
                    if (compM.getDebloque()) compet.setRadius(26);
                    if (compM.getEffet()[0] != 0) nomR.setText(nomR.getText() + "\nefficacité :+" + compM.getEffet()[0]);
                    if (compM.getEffet()[1] != 0) nomR.setText(nomR.getText() + "\nmoral :+" + compM.getEffet()[1]);
                    if (compM.getEffet()[2] != 0) nomR.setText(nomR.getText() + "\ntemps :+" + compM.getEffet()[2]);

                    nomR.setFont(Font.loadFont("file:Font.ttf", 24));
                    nomR.setX((scene.getWidth() * 83.5) / 100);
                    nomR.setY((scene.getHeight() * 45) / 100);
                    //nomR.setWrappingWidth((scene.getWidth() * 14) / 100);

                    root.getChildren().add(nomR);
                });

                // Quand la souris sort de la zone du cercle, la fenêtre de description est enlevée
                compet.setOnMouseExited(mouseEvent -> {
                    compet.setRadius(24);
                    root.getChildren().remove(root.getChildren().size() - 1);
                });

                // Achète la compétence lors d'un double clic sur le cercle si elle est déblocable et que le joueur dispose d'assez de points de compétence
                compet.setOnMouseClicked(mouseEvent -> {
                    if (vueArbre.getACliquer().equals(ligne + "," + colonne)) {
                        if (compM.getDebloque() && !compM.getAchete() && jeu.getPtsCompetence()-compM.getCout() >= 0) {
                            jeu.setPtsCompetence(-compM.getCout());
                            compM.getArbreDeCompetence().debloquerCompetence(ligne, colonne);
                            compet.setFill(new ImagePattern(new Image("file:image\\CompetenceAchete.png"), 0, 0, 1, 1, true));
                            vueArbre.changementAffichage(ligne);
                        }
                    } else vueArbre.setACliquer(ligne + "," + colonne);
                });
                root.getChildren().add(compet);
                break;
            default:

        }
    }

    /**
     *
     * @return
     */
    public Circle getCompet(){ return compet;}

    /**
     *
     * @param group
     */
    public void setGroup(Group group){ g = group; }
}
