package Vue;

import Constantes.Constantes;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.scene.text.Font;

/**
 *
 */
public class Competence implements java.io.Serializable {

    // Champs
    protected Modele.Competence modele;
    protected ArbreDeCompetence vueArbre;
    protected Circle compet;
    protected Group g;
    protected int colonne;
    protected int ligne;
    protected Text nomR;


    // Constructeur
    Competence(Modele.Competence c, ArbreDeCompetence arbreDeCompetenceVue,Modele.Jeu jeu) {
        vueArbre = arbreDeCompetenceVue;
        modele = c;
        ligne = c.getLigne();
        colonne = c.getColonne();
        nomR = new Text(modele.getNom() + "\n \n" + modele.getDescription());
        nomR.setText(nomR.getText() + "\ncout : " + modele.getCout());
        if (modele.getEffet()[0] != 0) nomR.setText(nomR.getText() + "\nefficacité :+" + modele.getEffet()[0]);
        if (modele.getEffet()[1] != 0) nomR.setText(nomR.getText() + "\nmoral :+" + modele.getEffet()[1]);
        if (modele.getEffet()[2] != 0) nomR.setText(nomR.getText() + "\ntemps :+" + modele.getEffet()[2]);
        compet = new Circle();
        compet.setVisible(false);
        nomR.setVisible(false);
        jeu.getVue().getRoot().getChildren().addAll(compet,nomR);
    }

    // Méthodes

    /**
     * @param jeu
     * @param afficher
     */
    void affichage(Modele.Jeu jeu, int afficher) {
        Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
        switch (afficher) {
            case 0:
                compet.setVisible(true);

                double coefx = (double) 1 / modele.getNbLignes() * (ligne - 1);
                double coefy = (double) 1 / modele.getNbColonnes() * (colonne - 1);
                compet.setCenterX((scene.getWidth() * 15) / 100 + coefx * (scene.getWidth() * 60 / 100));
                compet.setCenterY((scene.getHeight() * 90) / 100 - coefy * (scene.getHeight() * 65 / 100));
                compet.setRadius(24);

                if (modele.getAchete())
                    compet.setFill(new ImagePattern(new Image("file:image\\CompetenceAchete.png"), 0, 0, 1, 1, true));
                else if (modele.getDebloque())
                    compet.setFill(new ImagePattern(new Image("file:image\\CompetenceDebloque.png"), 0, 0, 1, 1, true));
                else
                    compet.setFill(new ImagePattern(new Image("file:image\\CompetenceBloque.png"), 0, 0, 1, 1, true));

                // Quand la souris entre dans la zone du cercle, la fenêtre de description est affichée
                compet.setOnMouseEntered(mouseEvent -> {
                    if (modele.getDebloque()) compet.setRadius(26);

                    vueArbre.setNoPoint(false);
                    nomR.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE_COMPETENCE));
                    nomR.setX((scene.getWidth() * 83.5) / 100);
                    nomR.setY((scene.getHeight() * 45) / 100);
                    nomR.setWrappingWidth((scene.getWidth() * 14) / 100);
                    nomR.setVisible(true);
                });

                // Quand la souris sort de la zone du cercle, la fenêtre de description est enlevée
                compet.setOnMouseExited(mouseEvent -> {
                    compet.setRadius(24);
                    nomR.setVisible(false);
                });


                compet.setOnMouseClicked(mouseEvent -> {
                    eventAchat(jeu); });
                break;
            case 2:

                double coeffx = (double) 1 / modele.getNbLignes() * (ligne - 1);
                double coeffy = (double) 1 / modele.getNbColonnes() * (colonne - 1);
                compet.setCenterX((scene.getWidth() * 15) / 100 + coeffx * (scene.getWidth() * 60 / 100));
                compet.setCenterY((scene.getHeight() * 90) / 100 - coeffy * (scene.getHeight() * 65 / 100));
                nomR.setFont(Font.loadFont("file:Font.ttf", scene.getHeight() * Constantes.TAILLE_POLICE_COMPETENCE));

                break;
            default:
                compet.setVisible(false);
        }
    }

    /**
     * @return
     */
    public Circle getCompet() {
        return compet;
    }

    /**
     * @param group
     */
    public void setGroup(Group group) {
        g = group;
    }


    // Achète la compétence lors d'un double clic sur le cercle si elle est déblocable et que le joueur dispose d'assez de points de compétence
    public void eventAchat(Modele.Jeu jeu){

        if (vueArbre.getACliquer().equals(ligne + "," + colonne)) {
            if (modele.getDebloque() && !modele.getAchete() && jeu.getPtsCompetence() >= modele.getCout()) {
                jeu.setPtsCompetence(-modele.getCout());
                modele.getArbreDeCompetence().debloquerCompetence(ligne, colonne);
                compet.setFill(new ImagePattern(new Image("file:image\\CompetenceAchete.png"), 0, 0, 1, 1, true));
                vueArbre.changementAffichage(ligne);
            }
            else if(!modele.getDebloque()){
                nomR.setVisible(false);
                vueArbre.erreurAchat(jeu, "La compétence est bloquée");
            }
            else if(!modele.getAchete()) {
                nomR.setVisible(false);
                vueArbre.erreurAchat(jeu, "Vous n'avez pas assez de points");
            }
        } else vueArbre.setACliquer(ligne + "," + colonne);

    }
}
