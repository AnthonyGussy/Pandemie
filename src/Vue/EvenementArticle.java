package Vue;

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
	public EvenementArticle(Modele.EvenementArticle eam) {
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
	}
	
    public void affichage(Modele.Jeu jeu, int afficher) {
    	Scene scene = jeu.getVue().getScene();
        Group root = jeu.getVue().getRoot();
    	switch(afficher) {
            case 0:
                affiche = true;
                scene.setFill(new ImagePattern(new Image("file:image\\PandemieCompetenceJournal.jpg"), 0, 0, 1, 1, true));

                nom.setX((int)(scene.getWidth() * 30 / 100));
                nom.setY((int)(scene.getHeight() * 7 / 100));

                description.setX((int)(scene.getWidth() * 28 / 100));
                description.setY((int)(scene.getHeight() * 10 / 100));

                departement.setX((int)(scene.getWidth() * 28 / 100));
                departement.setY((int)(scene.getHeight() * 20 / 100));

                root.getChildren().remove(nom);
                root.getChildren().remove(description);
                root.getChildren().remove(departement);
                root.getChildren().add(nom);
                root.getChildren().add(description);
                root.getChildren().add(departement);
                break;
            case 1:
                if(affiche) {
                    affiche = false;
                    root.getChildren().remove(nom);
                    root.getChildren().remove(description);
                    root.getChildren().remove(departement);
                }
                break;
            default:
                if(affiche) {
                    affichage(jeu, 0);
                }
        }
    }
    
    public void changerAffichage() { 
    	// Changer affichage au bout d'un certain temps ou bouton ok
    }
}
