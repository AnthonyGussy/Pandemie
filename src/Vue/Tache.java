package Vue;


import Constantes.Constantes;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tache implements java.io.Serializable {

	private Text nom;
    private Text description;

	public Tache(Modele.Tache modele, Modele.Jeu jeu) {
		nom = new Text(modele.getNom());
		description = new Text(modele.getDescription());
		nom.setFill(Color.BLACK);
		description.setFill(Color.BLACK);
		nom.setVisible(false);
		description.setVisible(false);
		jeu.getVue().getRoot().getChildren().addAll(nom,description);
	}
	
	public void affichage(Scene scene, int afficher) {
		switch(afficher) {
			case 0:
				affichage(scene, 2);
				System.out.print("ici");
				nom.setVisible(true);
				description.setVisible(true);
			case 1:
				nom.setVisible(false);
				description.setVisible(false);
				break;
			default:
				nom.setFont(Font.loadFont("file:Font.ttf", Constantes.TAILLE_POLICE * scene.getHeight()));
				description.setFont(Font.loadFont("file:Font.ttf", Constantes.TAILLE_POLICE * scene.getHeight()));
				nom.setX(Constantes.POS_X_TACHE_NOM);
				nom.setY(Constantes.POS_Y_TACHE_NOM);
				description.setX(Constantes.POS_X_TACHE_DESCRIPTION);
				description.setY(Constantes.POS_Y_TACHE_DESCRIPTION);
		}
	}
	
	

}
