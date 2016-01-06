package Vue;

import Constantes.Constantes;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EvenementArticle implements java.io.Serializable {
	
	private Modele.EvenementArticle eam;
	private boolean affiche = false;
    private Text nom;
    private Text description;
    private Text departement;
	public EvenementArticle(Modele.EvenementArticle eam, Group root) {
        this.eam = eam;
        nom = new Text(eam.getNom());
        nom.setFont(Font.loadFont("file:Font.ttf", 20));
        description = new Text(eam.getDescription());
        description.setFont(Font.loadFont("file:Font.ttf", 18));
        String effets = "Effets sur "+eam.getDepartement()+" : ";
        effets += "moral => "+eam.getMoral()+" / ";
        effets += "efficacite => "+eam.getEfficacite()+" / ";
        effets += "temps => "+eam.getTemps();
        departement = new Text(effets);
        departement.setFont(Font.loadFont("file:Font.ttf", 18));
        departement.setFill(Color.WHITE);
        nom.setVisible(false);
        description.setVisible(false);
        departement.setVisible(false);
        root.getChildren().addAll(nom, description, departement);
	}
	
    public void affichage(Modele.Jeu jeu, int afficher) {
    	Scene scene = jeu.getVue().getScene();
    	switch(afficher) {
            case 0:
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieCompetenceJournal.jpg"), 0, 0, 1, 1, true));
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
            default:
                nom.setVisible(false);
                description.setVisible(false);
                departement.setVisible(false);
        }
    }
}
