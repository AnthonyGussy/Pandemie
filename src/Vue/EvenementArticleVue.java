package Vue;

import Modele.EvenementArticleModele;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EvenementArticleVue implements java.io.Serializable {
	
	private EvenementArticleModele eam;
	private boolean affiche = false;
	public EvenementArticleVue(EvenementArticleModele eam) {
		this.eam = eam;
	}
	
    public void affichage(int afficher) {
    	// (316, 53) -> (748, 162)
    	switch(afficher) {
            case 0:
                affiche = true;
                Jeu.scene.setFill(new ImagePattern(new Image("file:image\\PandemieCompetenceJournal.jpg"), 0, 0, 1, 1, true));
                Text nom = new Text(eam.getNom());
                nom.setFont(Font.loadFont("file:Font.ttf", 20));
                nom.setX((int)(Jeu.scene.getWidth() * 30 / 100));
                nom.setY((int)(Jeu.scene.getHeight() * 7 / 100));

                Text description = new Text(eam.getDescription());
                description.setFont(Font.loadFont("file:Font.ttf", 18));
                description.setX(285);
                description.setY(80);

                String effets = "Effets sur "+eam.getDepartement()+" : ";
                effets += "moral => "+eam.getMoral()+" / ";
                effets += "efficacite => "+eam.getEfficacite()+" / ";
                effets += "temps => "+eam.getTemps();
                Text departement = new Text(effets);
                departement.setFont(Font.loadFont("file:Font.ttf", 18));
                departement.setFill(Color.WHITE);
                departement.setX(285);
                departement.setY(160);

                Jeu.root.getChildren().add(nom);
                Jeu.root.getChildren().add(description);
                Jeu.root.getChildren().add(departement);
                break;
            case 1:
                if(affiche) {
                    affiche = false;
                    Jeu.root.getChildren().clear();
                }
                break;
            default:
                if(affiche) {
                    affichage(0);
                }
        }
    }
    
    public void changerAffichage() { 
    	// Changer affichage au bout d'un certain temps ou bouton ok
    }
}
