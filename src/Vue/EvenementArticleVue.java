package Vue;

import Modele.EvenementArticleModele;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EvenementArticleVue {
	
	private EvenementArticleModele eam;
	
	public EvenementArticleVue(EvenementArticleModele eam) {
		this.eam = eam;
	}
	
    public void affichage() {
    	// (316, 53) -> (748, 162)
    	
        Jeu.scene.setFill(new ImagePattern(new Image("file:image\\PandemieCompetenceJournal.jpg"), 0, 0, 1, 1, true));

        Text nom = new Text(eam.getNom());
        nom.setFont(Font.loadFont("file:Font.ttf", 20));
        nom.setX(310);
        nom.setY(55);
        
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
        departement.setX(285);
        departement.setY(160);
        
        Jeu.root.getChildren().add(nom);
        Jeu.root.getChildren().add(description);
        Jeu.root.getChildren().add(departement);
    }
    
    public void changerAffichage() { 
    	// Changer affichage au bout d'un certain temps ou bouton ok
    }
}
