package Vue;

import Constantes.Constantes;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EvenementArticle implements java.io.Serializable {

    private Text nom;
    private Text description;
    private Text departement;

    public EvenementArticle(Modele.Evenement event, Modele.Jeu jeu) {

        nom = new Text(event.getNom());
        nom.setFont(Font.loadFont("file:Font.ttf", jeu.getVue().getScene().getHeight() * Constantes.TAILLE_POLICE_TITRE));
        nom.setFill(Color.WHITE);
        description = new Text(event.getDescription());
        description.setFont(Font.loadFont("file:Font.ttf", jeu.getVue().getScene().getHeight() * Constantes.TAILLE_POLICE));
        description.setFill(Color.WHITE);
        String texte;
        if(event.isAccomplissement())
             texte = "Le projet final avance petit a petit.";
        else
            texte = "";
        departement = new Text(texte);
        departement.setFont(Font.loadFont("file:Font.ttf", jeu.getVue().getScene().getHeight() * Constantes.TAILLE_POLICE));
        departement.setFill(Color.WHITE);
        nom.setVisible(false);
        description.setVisible(false);
        departement.setVisible(false);
        jeu.getVue().getRoot().getChildren().addAll(nom, description, departement);

    }

	public EvenementArticle(Modele.EvenementArticle eam, Modele.Jeu jeu) {
        nom = new Text(eam.getNom());
        nom.setFont(Font.loadFont("file:Font.ttf", jeu.getVue().getScene().getHeight() * Constantes.TAILLE_POLICE_TITRE));
        nom.setFill(Color.WHITE);
        description = new Text(eam.getDescription());
        description.setFont(Font.loadFont("file:Font.ttf", jeu.getVue().getScene().getHeight() * Constantes.TAILLE_POLICE));
        description.setFill(Color.WHITE);
        String effets = "Effets sur les départements : ";
        effets += "moral => "+eam.getMoral()+" / ";
        effets += "efficacite => "+eam.getEfficacite()+" / ";
        effets += "temps => "+eam.getTemps();
        departement = new Text(effets);
        departement.setFont(Font.loadFont("file:Font.ttf", jeu.getVue().getScene().getHeight() * Constantes.TAILLE_POLICE));
        departement.setFill(Color.WHITE);
        nom.setVisible(false);
        description.setVisible(false);
        departement.setVisible(false);
        jeu.getVue().getRoot().getChildren().addAll(nom, description, departement);
	}
	
    public void affichage(Modele.Jeu jeu, int afficher) {
    	Scene scene = jeu.getVue().getScene();
    	switch(afficher) {
            case 0:
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieCompetence.jpg"), 0, 0, 1, 1, true));
                nom.setX((int)(scene.getWidth() * Constantes.POS_X_EV_ART_NOM));
                nom.setY((int)(scene.getHeight() * Constantes.POS_Y_EV_ART_NOM));
                description.setX((int)(scene.getWidth() * Constantes.POS_X_EV_ART_DESC));
                description.setY((int)(scene.getHeight() * Constantes.POS_Y_EV_ART_DESC));
                departement.setX((int)(scene.getWidth() * Constantes.POS_X_EV_ART_DEP));
                departement.setY((int)(scene.getHeight() * Constantes.POS_Y_EV_ART_DEP));
                nom.setVisible(true);
                description.setVisible(true);
                departement.setVisible(true);
                break;
            case 1:
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieDep.jpg"), 0, 0, 1, 1, true));
                nom.setVisible(false);
                description.setVisible(false);
                departement.setVisible(false);
            default:
                nom.setX((int)(scene.getWidth() * Constantes.POS_X_EV_ART_NOM));
                nom.setY((int)(scene.getHeight() * Constantes.POS_Y_EV_ART_NOM));
                description.setX((int)(scene.getWidth() * Constantes.POS_X_EV_ART_DESC));
                description.setY((int) (scene.getHeight() * Constantes.POS_Y_EV_ART_DESC));
                departement.setX((int) (scene.getWidth() * Constantes.POS_X_EV_ART_DEP));
                departement.setY((int) (scene.getHeight() * Constantes.POS_Y_EV_ART_DEP));
        }
    }
}
