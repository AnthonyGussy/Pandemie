package Modele;

import Constantes.Constantes;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javafx.scene.Group;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import Enumerations.DepartementNom;


public class EvenementArticle extends Evenement implements java.io.Serializable {
	// Attributs
	protected int effets[] = new int[Constantes.TAILLE_EFFETS]; // Contient le moral, efficacité et temps


	// Constructeurs
	public EvenementArticle(String nom, String description, int effets[], Modele.Jeu jeu) {
		super(nom, description, jeu);
		this.effets = effets;
		event = new Vue.Evenement(this, jeu);
	}

	public EvenementArticle(Modele.EvenementArticle evenement, Modele.Jeu jeu) {
		super(evenement.nom, evenement.description, jeu);
		effets = evenement.effets;
		event = new Vue.Evenement(this, jeu);
	}

	// Méthodes
	public void appliquerEffet(){}

	public int[] getEffets() { return effets; }

	public int getMoral() { return effets[0]; }

	public void setMoral(int moral) { this.effets[0] = moral; }

	public int getEfficacite() { return effets[1]; }

	public void setEfficacite(int efficacite) { this.effets[1] = efficacite; }

	public int getTemps() { return effets[2]; }

	public void setTemps(int temps) { this.effets[2] = temps; }
}
