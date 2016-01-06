package Vue;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import Constantes.Constantes;
import Modele.TacheList;
import Modele.Jeu;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tache implements java.io.Serializable {
	
	private TacheList tacheList;
	private Text nom;
    private Text description;
	private Modele.Tache modele;

	public Tache(Modele.Tache modele, Modele.Jeu jeu) {
		this.modele = modele;
		nom = new Text(modele.getNom());
		description = new Text(modele.getDescription());
		nom.setFill(Color.BLACK);
		description.setFill(Color.BLACK);
		nom.setVisible(false);
		description.setVisible(false);
	}
	
	public void affichage(Scene scene, int afficher) {
		switch(afficher) {
			case 0:
				affichage(scene, 2);
				nom.setVisible(true);
				//description.setVisible(true);
			case 1:
				nom.setVisible(false);
				//description.setVisible(false);
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
