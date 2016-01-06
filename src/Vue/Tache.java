package Vue;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import Constantes.Constantes;
import Modele.TacheList;
import Modele.Jeu;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Tache implements java.io.Serializable {
	
	private TacheList tacheList;
	private Text titre;
    private List<Text> nom;
    private Text compteurTemps;
    private Text description;
    private Text compteurInfecte;

	public Tache(TacheList tacheList, javafx.scene.Group root) {
		this.tacheList = tacheList;
		titre = new Text("");
		nom = new ArrayList<>();
		description = new Text();
		compteurTemps = new Text();
		compteurInfecte = new Text();
		titre.setFont(Font.loadFont("file:Font.ttf", 24));
		description.setFont(Font.loadFont("file:Font.ttf", 20));
		compteurTemps.setFont(Font.loadFont("file:Font.ttf", 20));
		compteurInfecte.setFont(Font.loadFont("file:Font.ttf", 20));
		titre.setVisible(false);
		//root.getChildren().add(titre);
		for (int i = 0; i < tacheList.size(); i++) {
			nom.add(new Text(tacheList.getTache(i).getNom()));
			nom.get(i).setVisible(false);
			//root.getChildren().add(nom.get(i));
		}
	}
	
	public void affichage(int afficher, Scene scene) {
		switch(afficher) {
			case 1:
				if(tacheList != null && tacheList.size() > 0) {
					if(tacheList.size() > 1) { 
						titre.setText("Tâche");
					} else { 
						titre.setText("Tâches");
					}
					titre.setX(scene.getWidth() * Constantes.POS_X_TACHE);
					titre.setY(scene.getHeight() * Constantes.POS_Y_TACHE);
					titre.setVisible(true);
					int i = 1;
					for (Text elementTache : nom) {
						elementTache.setX(scene.getWidth() * Constantes.POS_X_TACHE + 9);
						elementTache.setY(scene.getHeight() * Constantes.POS_Y_TACHE + 5+i*10);
						i++;
						elementTache.setVisible(true);
						System.out.println("On rentre bien dans la boucle !");
					}	
				}
			default:
				for (Text elementTache : nom) {
					titre.setVisible(false);
					elementTache.setVisible(false);
				}	
				
		}
	}
	
	

}
