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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Enumerations.CompteurType;


public class Tache implements java.io.Serializable {
	
	// Attributs
	
	private String nom;
	private String description;
    private List<Compteur> compteurs; // de temps (avancement) et d'infectés
    private boolean termine;
    
    // Méthodes
    
    public Tache(String nom, String description, Compteur temps, Compteur infectes) {
		this.nom = nom;
		this.description = description;
		this.compteurs = new ArrayList<>();
		this.compteurs.add(temps);
		this.compteurs.add(infectes);
		termine = false;
	}
    
    public Tache(String departement, String difficulte, int index) {
    	
    	termine = false;
    	compteurs = new ArrayList<>();
    	
    	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	
        try {
        	
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final Document doc = builder.parse(new File(Constantes.PATH_TACHES));
            Element racine = doc.getDocumentElement();
            NodeList racineNoeuds = racine.getChildNodes();
            boolean initialiser = false;
            
            for (int i = 0; i < racineNoeuds.getLength() && !initialiser; i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE && racineNoeuds.item(i).getNodeName().equals(departement)) {
	                    NodeList deparNoeuds = racineNoeuds.item(i).getChildNodes();
	                    
	                    for (int j = 0; j < deparNoeuds.getLength(); j++) {

	                    	if(deparNoeuds.item(j).getNodeType() == Node.ELEMENT_NODE && deparNoeuds.item(j).getNodeName().equals(difficulte)) {
	                    		Element elementTache = (Element) deparNoeuds.item(j);
	                    		
	    	                    nom = elementTache.getElementsByTagName("nom").item(0).getTextContent();
	    	                    description = elementTache.getElementsByTagName("description").item(0).getTextContent();
	    	                    
	    	                    Compteur c = new Compteur(Integer.parseInt(elementTache.getElementsByTagName("temps").item(0).getTextContent()), CompteurType.Temps);
	    	                    compteurs.add(c);
	    	                    c = new Compteur(Integer.parseInt(elementTache.getElementsByTagName("infecte").item(0).getTextContent()), CompteurType.Infectes);
	    	                    compteurs.add(c);
	    	                    
	    	                    initialiser = true;
	                    	}
	                    }     
            		}
                }
            } catch (final ParserConfigurationException | SAXException | IOException e) {
            	e.printStackTrace();
            }
	}
    
    public void affichage() {
    	System.out.println("nom : "+nom);
    	System.out.println("description : "+description);
    	System.out.println("termine : "+termine);
    	System.out.println("temps : "+compteurs.get(0).getCompte());
    	System.out.println("infectes : "+compteurs.get(1).getCompte());
    }
    
    public void setAvancement(int incr){
    	compteurs.get(0).modifCompte(incr);
    }
    public void setInfectes(int incr){
    	compteurs.get(1).modifCompte(incr);
    }
    
    public List<Compteur> getCompteurs() { return compteurs; }
    
    public boolean getTermine() { return termine; }
    
    public void setTermine(boolean termine) { this.termine = termine; }
    
    public static void main(String[] args) {
    	Tache t = new Tache("Informatique", "Facile", 0);
    	t.affichage();
    }
}