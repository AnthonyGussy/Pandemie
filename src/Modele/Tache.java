package Modele;

import Vue.Compteur;
import Constantes.Constantes;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javafx.application.Platform;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Enumerations.CompteurType;


public class Tache implements java.io.Serializable {

	// Attributs

	private final String description;
	private String nom;
	private List<Compteur> compteurs; // de temps (avancement) et d'infectés
	private int tempsInitial;
	private Modele.EvenementAccomplissement event;
	private Modele.Departement departement;
	private Vue.Tache vue;
	private boolean termine;

	// Méthodes

	public Tache(Modele.Departement depart, String nom, String description, Compteur temps, Compteur infectes, Modele.Jeu jeu) {
		departement = depart;
		this.nom = nom;
		this.description = description;
		this.compteurs = new ArrayList<>();
		this.compteurs.add(temps);
		this.compteurs.add(infectes);
		tempsInitial = temps.getCompte();
		termine = false;
		vue = new Vue.Tache(this, jeu);
	}

	public Vue.Tache getVue() { return vue; }

	public void setAvancement(int incr) {
		compteurs.get(0).modifCompte(incr);
		if(this.getAvancement() == 0) termine = true;
	}

	public void setAvancement() {
		compteurs.get(0).modifCompte(-departement.getEfficacite() / 5);
		if(this.getAvancement() == 0) termine = true;
	}

	public void setInfectes(int incr) {
		compteurs.get(1).modifCompte(incr);
	}
	
    public String getNom() { return nom; }
	    
	public String getDescription() { return description; }

	public int getAvancement() { return compteurs.get(0).getCompte(); }

	public int getInfectes() { return compteurs.get(1).getCompte(); }

	public int getTempsInitial() {
		return tempsInitial;
	}

	public boolean getTermine() { return termine; }

	public Modele.EvenementAccomplissement getEvent() { return event;}
}