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
		nom = new Text("- " + modele.getNom());
		nom.setFill(Color.BLACK);
		nom.setVisible(false);
		jeu.getVue().getRoot().getChildren().add(nom);
	}
	
	public void affichage(Scene scene, int afficher, int index) {
		switch(afficher) {
			case 0:
				affichage(scene, 2, index);
				nom.setVisible(true);
				break;
			case 1:
				nom.setVisible(false);
				break;
			default:
				nom.setFont(Font.loadFont("file:Font.ttf", Constantes.TAILLE_POLICE_TACHE * scene.getHeight()));
				nom.setX(scene.getWidth() * Constantes.POS_X_TACHE_NOM);
				nom.setY(scene.getHeight() * Constantes.POS_Y_TACHE_NOM + index * scene.getHeight() * Constantes.ESPACEMENT_LIGNES);
				nom.setWrappingWidth(scene.getWidth() * Constantes.LARGEUR_POST_IT);
		}
	}
	
	

}
